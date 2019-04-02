/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pheiot.bamboo.common.utils.mapper.BeanMapper;
import com.pheiot.phecloud.pd.dao.DeviceDao;
import com.pheiot.phecloud.pd.dao.ProductDao;
import com.pheiot.phecloud.pd.dto.DeviceConditionDto;
import com.pheiot.phecloud.pd.dto.DeviceDto;
import com.pheiot.phecloud.pd.entity.Device;
import com.pheiot.phecloud.pd.entity.Product;
import com.pheiot.phecloud.pd.service.DeviceService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import com.pheiot.phecloud.pd.utils.ExceptionCode;
import com.pheiot.phecloud.pd.utils.KeyGenerator;
import com.pheiot.phecloud.pd.utils.OffsetBasedPageRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    protected DeviceDao deviceDao;

    @Autowired
    protected ProductDao productDao;

    @Override
    public DeviceDto findByKey(String key) {
        if (StringUtils.isBlank(key)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        Device device = deviceDao.findByDkey(key);

        if (device == null || StringUtils.isBlank(device.getPkey())) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        Product product = productDao.findByPkey(device.getPkey());

        if (product == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }


        DeviceDto dto = BeanMapper.map(device, DeviceDto.class);
        dto.setNodeType(product.getNodeType());

        return dto;
    }

    @Override
    public DeviceDto findByProductKey(String pkey) {
        return null;
    }

    @Override
    public List<DeviceDto> findByProductKeyPageable(String uid, String pkey, DeviceConditionDto conditionDto) {
        if (StringUtils.isBlank(uid) || StringUtils.isBlank(pkey)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        Product product = productDao.findByPkey(pkey);

        if (product == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        Sort sort = new Sort(Sort.Direction.DESC, "createAt");
        Boolean showDisabled = conditionDto.getShowDisabled() ? null : true;
        Pageable page = new OffsetBasedPageRequest(conditionDto.getOffset(), conditionDto.getLimit(), sort);
        List<DeviceDto> dtoList = Lists.newArrayList();

        if (page != null) {
            Page<Device> list = deviceDao.findByPkeyPageable(pkey, showDisabled, page);
            List<Device> entityList = list.getContent();
            for (Device entity : entityList) {
                DeviceDto dto = BeanMapper.map(entity, DeviceDto.class);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    @Override
    public DeviceDto binding(String uid, DeviceDto deviceDto) {
        if (deviceDto == null || StringUtils.isBlank(deviceDto.getPkey())) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }
        //查找用户持有的产品
        Product product = productDao.findByUidAndPkey(uid, deviceDto.getPkey());

        if (product == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        Device device = BeanMapper.map(deviceDto, Device.class);
        device.setDkey(KeyGenerator.generateKey());
        device.setSecret(KeyGenerator.generateSecret());

        //使用MD5生成token字符
        String tokenSeed = product.getPkey() + product.getSecret() + device.getDisplayName();
        device.setToken(DigestUtils.md5Hex(tokenSeed));
        device.setIsOnline(false);

        deviceDao.save(device);

        DeviceDto dto = BeanMapper.map(device, DeviceDto.class);
        dto.setNodeType(product.getNodeType());

        logger.info("Binding device: {}", dto.getDisplayName());

        return dto;
    }

    @Override
    public DeviceDto update(String uid, DeviceDto deviceDto) {
        if (deviceDto == null || StringUtils.isBlank(deviceDto.getDkey())) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        Device device = deviceDao.findByDkey(deviceDto.getDkey());

        if (device == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        boolean existProduct = productDao.existsProductByUidAndPkey(uid, device.getPkey());

        if (existProduct == false) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        if(StringUtils.isNotBlank(deviceDto.getDisplayName())){
            device.setDisplayName(deviceDto.getDisplayName());
        }
        if(StringUtils.isNotBlank(deviceDto.getRemark())){
            device.setRemark(deviceDto.getRemark());
        }

        deviceDao.save(device);

        DeviceDto dto = BeanMapper.map(device, DeviceDto.class);
        logger.info("Update device: {}", dto.getDisplayName());

        return dto;
    }

    @Override
    public DeviceDto changeEnabledTo(String uid, String deviceKey, boolean isEnabled) {
        if (StringUtils.isBlank(deviceKey) || StringUtils.isBlank(uid)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        Device device = deviceDao.findByDkey(deviceKey);

        if (device == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        boolean existProduct = productDao.existsProductByUidAndPkey(uid,device.getPkey());
        if (existProduct == false) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        device.setIsEnabled(isEnabled);
        deviceDao.save(device);

        DeviceDto dto = BeanMapper.map(device, DeviceDto.class);
        logger.info("Change enabled to {} for device:{}", isEnabled, device.getDisplayName());

        return dto;
    }

    @Override
    public Map<String, List<String>> unbinding(String productKey, List<String> keys) {
        if (keys == null || keys.isEmpty() || StringUtils.isBlank(productKey)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        List<String> success = Lists.newArrayList();
        List<String> failed = Lists.newArrayList();
        for (String key : keys) {
            Device entity = deviceDao.findByPkeyAndDkey(productKey, key);
            if (entity == null) {
                failed.add(key);
                continue;
            }
            try {
                deviceDao.deleteByDkey(key);
                success.add(key);
                logger.debug("Delete device success: {}", key);
            } catch (Exception ex) {
                failed.add(key);
                ex.printStackTrace();
                logger.debug("Delete device failed: {}", key);
            }
        }

        Map<String, List<String>> res = Maps.newHashMap();
        res.put("success", success);
        res.put("failed", failed);

        logger.info("Delete device done.");
        return res;
    }
}

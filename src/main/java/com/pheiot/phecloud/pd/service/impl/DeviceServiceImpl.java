/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pheiot.bamboo.common.utils.mapper.BeanMapper;
import com.pheiot.phecloud.pd.dao.DeviceDao;
import com.pheiot.phecloud.pd.dao.ProductDao;
import com.pheiot.phecloud.pd.dto.DeviceDto;
import com.pheiot.phecloud.pd.entity.Device;
import com.pheiot.phecloud.pd.entity.Product;
import com.pheiot.phecloud.pd.service.DeviceService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import com.pheiot.phecloud.pd.utils.ExceptionCode;
import com.pheiot.phecloud.pd.utils.KeyGenerator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    protected DeviceDao deviceDao;

    protected ProductDao productDao;

    @Override
    public DeviceDto findByKey(String key) {
        if (StringUtils.isBlank(key)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        Device device = deviceDao.findByDkey(key);

        if (device == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        DeviceDto dto = BeanMapper.map(device, DeviceDto.class);

        return dto;
    }

    @Override
    public DeviceDto findByProductKey(String pkey) {
        return null;
    }

    @Override
    public DeviceDto binding(DeviceDto deviceDto) {
        if (deviceDto == null || StringUtils.isBlank(deviceDto.getPkey())) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        Product product = productDao.findByPkey(deviceDto.getPkey());

        if (product == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        Device device = BeanMapper.map(deviceDto, Device.class);
        device.setDkey(KeyGenerator.generateKey());
        device.setSecret(KeyGenerator.generateSecret());
        String tokenSeed = product.getPkey() + product.getSecret() + device.getDisplayName();
        device.setToken(DigestUtils.md5Hex(tokenSeed));

        deviceDao.save(device);

        DeviceDto dto = BeanMapper.map(device, DeviceDto.class);
        logger.info("Save product:{}", dto.getDisplayName());

        return dto;
    }

    @Override
    public void update(DeviceDto deviceDto) {
        if (deviceDto == null || StringUtils.isBlank(deviceDto.getDkey())) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }
        Device device = deviceDao.findByDkey(deviceDto.getDkey());

        if (device == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        device = BeanMapper.map(deviceDto, BeanMapper.getType(DeviceDto.class), BeanMapper.getType(Device.class));

        deviceDao.save(device);

        logger.info("Update product:{}", deviceDto.getDisplayName());
    }

    @Override
    public void changeEnabledTo(String key, boolean isEnabled) {
        if (StringUtils.isBlank(key)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        Device device = deviceDao.findByDkey(key);

        if (device == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        device.setIsEnabled(isEnabled);
        deviceDao.save(device);

        logger.info("Change enabled to {} for product:{}", isEnabled, device.getDisplayName());
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

    private String generateToken(String seed) {
        return DigestUtils.md5Hex(seed);
    }
}

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service.impl;

import com.google.common.collect.Lists;
import com.pheiot.bamboo.common.utils.mapper.BeanMapper;
import com.pheiot.phecloud.pd.dao.ProductPropertyDao;
import com.pheiot.phecloud.pd.dto.ProductPropertyDto;
import com.pheiot.phecloud.pd.entity.ProductProperty;
import com.pheiot.phecloud.pd.service.ProductPropertyService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import com.pheiot.phecloud.pd.utils.ExceptionCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService {

    private static Logger logger = LoggerFactory.getLogger(ProductPropertyService.class);

    @Autowired
    private ProductPropertyDao productPropertyDao;


    @Override
    public ProductPropertyDto findByCode(String productKey, String code) {
        if (StringUtils.isBlank(productKey) || StringUtils.isBlank(code)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        ProductProperty entity = productPropertyDao.findByProductAndCode(productKey, code);

        if (entity == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        ProductPropertyDto dto = BeanMapper.map(entity, ProductPropertyDto.class);

        return dto;
    }

    @Override
    public List<ProductPropertyDto> findByProductKey(String productKey) {
        if (StringUtils.isBlank(productKey)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        List<ProductProperty> entities = productPropertyDao.findByPkey(productKey);
        List<ProductPropertyDto> dtoList = Lists.newArrayList();

        for (ProductProperty entity : entities) {
            ProductPropertyDto dto = BeanMapper.map(entity, ProductPropertyDto.class);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public ProductPropertyDto save(ProductPropertyDto productPropertyDto) {
        if (productPropertyDto == null
                || StringUtils.isBlank(productPropertyDto.getCode())
                || StringUtils.isBlank(productPropertyDto.getDataType())
                || StringUtils.isBlank(productPropertyDto.getPkey())
                || StringUtils.isBlank(productPropertyDto.getFunctionType())) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        ProductProperty entity = BeanMapper.map(productPropertyDto, ProductProperty.class);
        productPropertyDao.save(entity);
        ProductPropertyDto dto = BeanMapper.map(entity, ProductPropertyDto.class);

        logger.info("Save product:{}", dto.getDisplayName());

        return dto;
    }

    @Override
    public void update(ProductPropertyDto productPropertyDto) {
        if (productPropertyDto == null
                || StringUtils.isBlank(productPropertyDto.getCode())
                || StringUtils.isBlank(productPropertyDto.getPkey())) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }
        ProductProperty entity = productPropertyDao.findByProductAndCode(productPropertyDto.getPkey(), productPropertyDto.getCode());

        if (entity == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        entity = BeanMapper.map(productPropertyDto, BeanMapper.getType(ProductPropertyDto.class), BeanMapper.getType(ProductProperty.class));

        productPropertyDao.save(entity);

        logger.info("Update product property:{}", entity.getDisplayName());
    }

    @Override
    public void deleteByCode(String productKey, String code) {
        if (StringUtils.isBlank(code)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        ProductProperty entity = productPropertyDao.findByProductAndCode(productKey, code);

        if (entity == null) {
            throw new ApplicationException(ExceptionCode.OBJECT_NOT_FOUND);
        }

        productPropertyDao.deleteById(entity.getId());

        logger.info("Delete product property:{}", entity.getDisplayName());
    }

    @Override
    public Boolean isExist(String productKey, String code) {
        if (StringUtils.isBlank(productKey) || StringUtils.isBlank(code)) {
            throw new ApplicationException(ExceptionCode.PARAMTER_ERROR);
        }

        return productPropertyDao.existsByPkeyAndCode(productKey, code);
    }
}

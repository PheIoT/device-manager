/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service;

import com.pheiot.phecloud.pd.dto.ProductPropertyDto;
import com.pheiot.phecloud.pd.dto.ProductPropertyFullDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductPropertyService {

    /**
     * 根据编码code查找数据点
     *
     * @param productKey productKey
     * @param code       code
     * @return ProductDto
     */
    ProductPropertyDto findByCode(String productKey, String code);


    /**
     * 根据产品key查找数据点
     *
     * @param productKey productKey
     * @return ProductDto
     */
    List<ProductPropertyDto> findByProductKey(String productKey);

    /**
     * 根据产品key查找数据点
     *
     * @param productKey productKey
     * @return ProductDto
     */
    ProductPropertyFullDto findFullByProductKey(String productKey);

    /**
     * 增加产品数据点
     * <p>
     * 必要字段：pkey，dataType，functionType
     *
     * @param productPropertyDto productPropertyDto
     */
    @Transactional(rollbackFor = Exception.class)
    ProductPropertyDto save(ProductPropertyDto productPropertyDto);

    /**
     * 修改产品数据点
     * <p>
     * 必要字段：pkey，code
     *
     * @param productPropertyDto productPropertyDto
     */
    @Transactional(rollbackFor = Exception.class)
    void update(ProductPropertyDto productPropertyDto);

    /**
     * 删除产品数据点
     *
     * @param productKey productKey
     * @param code code
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByCode(String productKey, String code);

    /**
     * 判断code在产品中是否存在
     *
     * @param productKey productKey
     * @param code       code
     * @return boolean
     */
    Boolean isExist(String productKey, String code);


}

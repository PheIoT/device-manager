/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service;

import com.pheiot.phecloud.pd.dto.ProductConditionDto;
import com.pheiot.phecloud.pd.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApiProductService {

    /**
     * 增加产品
     *
     * @param productDto productDto
     */
    @Transactional(rollbackFor = Exception.class)
    ProductDto save(String uid, ProductDto productDto);

    /**
     * 根据产品key查找产品
     *
     * @param key key
     * @return ProductDto
     */
    ProductDto findProductByUidAndProductKey(String uid, String key);

    /**
     * 查询用户所有的产品,提供翻页支持。
     *
     * @param uid userKey
     * @return
     */
    List<ProductDto> findByUidPageable(String uid, ProductConditionDto pageable);

    /**
     * 修改产品
     *
     * @param isEnabled isEnabled
     */
    @Transactional(rollbackFor = Exception.class)
    ProductDto changeEnabledTo(String uid, String productKey, boolean isEnabled);
}

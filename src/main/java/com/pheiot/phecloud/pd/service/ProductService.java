package com.pheiot.phecloud.pd.service;

import com.pheiot.phecloud.pd.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    /**
     * 根据产品Id查找产品
     *
     * @param id id
     * @return ProductDto
     */
    ProductDto findProductById(Long id);

    /**
     * 根据产品名称查找产品
     *
     * @param name name
     * @return ProductDto
     */
    ProductDto findProductByName(String name);

    /**
     * 查询所有产品
     *
     * @return List
     */
    List<ProductDto> findAll(String userKey);

    /**
     * 增加产品
     *
     * @param productDto productDto
     */
    @Transactional(rollbackFor = Exception.class)
    ProductDto save(ProductDto productDto);

    /**
     * 修改产品
     *
     * @param productDto productDto
     */
    @Transactional(rollbackFor = Exception.class)
    void update(ProductDto productDto);

    /**
     * 修改产品
     *
     * @param isEnabled isEnabled
     */
    @Transactional(rollbackFor = Exception.class)
    void changeEnabledTo(String key, boolean isEnabled);


    /**
     * 删除产品
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteById(Long id);

    /**
     * 查找所有产品名称列表
     *
     * @return List<String>
     */
    List<String> findProductNames(String userkey);
}

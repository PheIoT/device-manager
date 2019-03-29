/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.ProductProperty;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPropertyDao extends PagingAndSortingRepository<ProductProperty, Long>, JpaSpecificationExecutor<ProductProperty> {

    /**
     * 根据产品查询所有的数据点
     *
     * @return List
     */
    List<ProductProperty> findByPkey(String pkey);

    /**
     * 根据id查询数据点
     *
     * @param code code
     * @return ProductProperty
     */
    @Query(value = "SELECT p FROM ProductProperty p WHERE p.pkey = ?1 AND p.code = ?2")
    ProductProperty findByProductAndCode(String productKey, String code);

    /**
     * 根据dataType查询数据点
     *
     * @param dataType dataType
     * @return ProductProperty
     */
    ProductProperty findByDataType(String dataType);

    /**
     * 根据数据点名称查找
     *
     * @param displayName displayName
     * @return ProductProperty
     */
    ProductProperty findByDisplayName(String displayName);

    /**
     * 根据数据点code删除
     *
     * @return
     */
    void deleteByCode(String code);

    /**
     * 根据productkey 和code 判断是否存在
     *
     * @return
     */
    boolean existsByPkeyAndCode(String pkey, String code);
}

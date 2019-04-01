/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    /**
     * 查询用户所有的产品
     *
     * @param uid userKey
     * @return
     */
    @Query(value = "SELECT p FROM Product p WHERE p.uid = ?1 and (?2 is null or p.isEnabled = ?2)")
    List<Product> findByUid(String uid, Boolean isEnabled);

    /**
     * 查询用户所有的产品,提供翻页支持。
     *
     * @param uid userKey
     * @return
     */
    @Query(value = "SELECT p FROM Product p WHERE p.uid = ?1 and (?2 is null or p.isEnabled = ?2)")
    Page<Product> findByUidPageable(String uid, Boolean isEnabled, Pageable pageable);

    /**
     * 根据id查询产品
     *
     * @param dkey key
     * @return Product
     */
    Product findByPkey(String dkey);

    /**
     * 根据产品名称查找
     *
     * @param displayName displayName
     * @return Product
     */
    Product findByDisplayName(String displayName);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Modifying
    @Query("delete from Product p where p.id in (?1)")
    void deleteByIds(List<Long> ids);

    /**
     * 根据key删除产品
     *
     * @param pkey product key
     * @return Product
     */
    void deleteProductByPkey(String pkey);

    /**
     * 批量删除
     *
     * @param pkeys product keys
     */
    @Modifying
    @Query("delete from Product p where p.pkey in (?1)")
    void deleteByPkeys(List<String> pkeys);
}

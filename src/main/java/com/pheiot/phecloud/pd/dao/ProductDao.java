package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.Product;
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
     * @param userKey userKey
     * @return
     */
    List<Product> findByUserKey(String userKey);

    /**
     * 根据id查询产品
     *
     * @param kay key
     * @return Product
     */
    Product findByKay(String kay);

    /**
     * 根据产品名称查找
     *
     * @param name name
     * @return Product
     */
    Product findByName(String name);

    /**
     * 根据产品名称进行模糊查询
     *
     * @param name name
     * @return List
     */
    @Query(value = "select p from Product p where p.name like ?1% and p.userKey = ?2")
    List<Product> findByNameLike(String name, String userKey);

    /**
     * 查找所有产品名称
     *
     * @return List<String>
     */
    @Query("select product.name from Product product where product.userKey = ?1")
    List<String> findProductNames(String userKey);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Modifying
    @Query("delete from Product product where product.id in (?1)")
    void deleteByIds(List<Long> ids);

    /**
     * 根据key删除产品
     *
     * @param kay kay
     * @return Product
     */
    void deleteProductByKay(String kay);

    /**
     * 批量删除
     *
     * @param keys kay
     */
    @Modifying
    @Query("delete from Product product where product.kay in (?1)")
    void deleteByKeys(List<String> keys);
}

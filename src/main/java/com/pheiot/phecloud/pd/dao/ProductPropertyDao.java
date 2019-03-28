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
     * 查询所有的数据点
     *
     * @return
     */
    @Override
    List<ProductProperty> findAll();

    /**
     * 根据id查询数据点
     *
     * @param code code
     * @return ProductProperty
     */
    ProductProperty findByCode(String code);

    /**
     * 根据数据点名称查找
     *
     * @param displayName displayName
     * @return ProductProperty
     */
    ProductProperty findByDisplayName(String displayName);
}

package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.DataPoint;
import com.pheiot.phecloud.pd.entity.DataPoint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataPointDao extends PagingAndSortingRepository<DataPoint, Long>, JpaSpecificationExecutor<DataPoint> {

    /**
     * 查询所有的数据点
     *
     * @return
     */
    @Override
    List<DataPoint> findAll();

    /**
     * 根据id查询数据点
     *
     * @param id id
     * @return DataPoint
     */
    DataPoint findDataPointById(Long id);

    /**
     * 根据id查询数据点
     *
     * @param kay key
     * @return DataPoint
     */
    DataPoint findDataPointByKay(String kay);

    /**
     * 根据数据点名称查找
     *
     * @param name name
     * @return DataPoint
     */
    DataPoint findDataPointByName(String name);

    /**
     * 根据数据点名称进行模糊查询
     *
     * @param name name
     * @return List
     */
    @Query(value = "select p from DataPoint p where p.name like ?1%")
    List<DataPoint> findByDataPointLike(String name);


}

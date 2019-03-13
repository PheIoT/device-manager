package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.DeviceShadow;
import com.pheiot.phecloud.pd.entity.DeviceShadow;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceShadowDao extends PagingAndSortingRepository<DeviceShadow, Long>, JpaSpecificationExecutor<DeviceShadow> {

    /**
     * 查询所有的设备影子
     *
     * @return
     */
    @Override
    List<DeviceShadow> findAll();

    /**
     * 根据id查询设备影子
     *
     * @param id id
     * @return DeviceShadow
     */
    DeviceShadow findDeviceShadowById(Long id);

    /**
     * 根据设备key查询设备影子
     *
     * @param devKay devKay
     * @return DeviceShadow
     */
    DeviceShadow findDeviceShadowByDevKey(String devKay);

}

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao extends PagingAndSortingRepository<Device, Long>, JpaSpecificationExecutor<Device> {

    /**
     * 根据id查询设备
     *
     * @param dkey dkey
     * @return Device
     */
    Device findByDkey(String dkey);

    /**
     * 根据id查询设备
     *
     * @param dkey device key
     * @param pkey product key
     * @return Device
     */
    Device findByPkeyAndDkey(String pkey, String dkey);

    /**
     * 查询产品下所有的设备，提供翻页支持。
     *
     * @param pkey productKey
     * @return
     */
    @Query(value = "SELECT d FROM Device d WHERE d.pkey = ?1 and (?2 is null or d.isEnabled = ?2)")
    Page<Device> findByPkeyPageable(String pkey, Boolean isEnabled, Pageable pageable);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Modifying
    @Query("delete from Device device where device.id in (?1)")
    void deleteByIds(List<Long> ids);

    /**
     * 根据key删除设备
     *
     * @param dkey dkey
     */
    void deleteByDkey(String dkey);

    /**
     * 批量删除
     *
     * @param dkey dkey
     */
    @Modifying
    @Query("delete from Device d where d.dkey in (?1)")
    void deleteByDkeys(List<String> dkey);
}

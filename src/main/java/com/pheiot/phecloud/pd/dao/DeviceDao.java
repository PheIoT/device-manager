package com.pheiot.phecloud.pd.dao;

import com.pheiot.phecloud.pd.entity.Device;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao extends PagingAndSortingRepository<Device, Long>, JpaSpecificationExecutor<Device> {

    /**
     * 查询所有的设备
     *
     * @return
     */
    @Override
    List<Device> findAll();

    /**
     * 根据id查询设备
     *
     * @param id id
     * @return Device
     */
    Device findDeviceById(Long id);

    /**
     * 根据id查询设备
     *
     * @param kay key
     * @return Device
     */
    Device findDeviceByKay(String kay);

    /**
     * 根据设备名称查找
     *
     * @param name name
     * @return Device
     */
    Device findDeviceByName(String name);

    /**
     * 根据设备名称进行模糊查询
     *
     * @param name name
     * @return List
     */
    @Query(value = "select p from Device p where p.name like ?1%")
    List<Device> findByDeviceLike(String name);

    /**
     * 查找所有设备名称
     *
     * @return List<String>
     */
    @Query("select device.name from Device device")
    List<String> findDeviceNames();

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Modifying
    @Query("delete from Device device where device.id in (?1)")
    void deleteDeviceByIds(List<Long> ids);

    /**
     * 根据key删除设备
     *
     * @param kay kay
     */
    void deleteDeviceByKay(String kay);

    /**
     * 批量删除
     *
     * @param keys kay
     */
    @Modifying
    @Query("delete from Device device where device.kay in (?1)")
    void deleteDeviceByKeys(List<String> keys);
}

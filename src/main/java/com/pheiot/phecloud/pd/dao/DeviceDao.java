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
     * 根据id查询设备
     *
     * @param kay key
     * @return Device
     */
    Device findByKay(String kay);

    /**
     * 根据id查询设备
     *
     * @param kay key
     * @return Device
     */
    Device findByProductKeyAndKay(String productKey, String kay);

    /**
     * 根据设备名称查找
     *
     * @param name name
     * @return Device
     */
    Device findByName(String name);

    /**
     * 根据设备名称进行模糊查询
     *
     * @param name name
     * @return List
     */
    @Query(value = "select p from Device p where p.name like ?1%")
    List<Device> findByNameLike(String name);

    /**
     * 根据keys集合，查找相应的设备
     *
     * @param keys keys
     */
    @Modifying
    @Query("select device from Device device where device.kay in (?1)")
    List<Device>  findByKays(List<String> keys);

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
     * @param kay kay
     */
    void deleteByKay(String kay);

    /**
     * 批量删除
     *
     * @param keys kay
     */
    @Modifying
    @Query("delete from Device device where device.kay in (?1)")
    void deleteByKays(List<String> kays);
}

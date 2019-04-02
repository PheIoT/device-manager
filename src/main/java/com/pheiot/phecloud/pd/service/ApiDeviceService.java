/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service;

import com.pheiot.phecloud.pd.dto.DeviceConditionDto;
import com.pheiot.phecloud.pd.dto.DeviceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ApiDeviceService {

    /**
     * 查询所属用户中，某产品下所有的设备,提供翻页支持。
     *
     * @param uid userKey
     * @return
     */
    List<DeviceDto> findByProductKeyPageable(String uid, String pkey, DeviceConditionDto pageable);


    /**
     * 查询所属用户中，某产品下的设备
     *
     * @param uid        userKey
     * @param deviceKey deviceKey
     * @return
     */
    DeviceDto findByProductKeyAndDeviceKey(String uid, String deviceKey);

    /**
     * 绑定设备
     *
     * @param deviceDto deviceDto
     */
    @Transactional(rollbackFor = Exception.class)
    DeviceDto binding(String uid, DeviceDto deviceDto);

    /**
     * 解除绑定 设备
     *
     * @param uid   user ID
     * @param pkey  product key
     * @param dkeys device keys
     */
    @Transactional(rollbackFor = Exception.class)
    Map<String, List<String>> unbinding(String uid, String pkey, List<String> dkeys);

    /**
     * 修改设备
     *
     * @param deviceDto deviceDto
     */
    @Transactional(rollbackFor = Exception.class)
    DeviceDto update(String uid, DeviceDto deviceDto);

    /**
     * 修改设备状态
     *
     * @param dkey      device key
     * @param isEnabled isEnabled
     */
    @Transactional(rollbackFor = Exception.class)
    DeviceDto changeEnabledTo(String uid, String dkey, boolean isEnabled);
}

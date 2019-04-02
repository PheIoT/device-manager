/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.service;

import com.pheiot.phecloud.pd.dto.DeviceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface DeviceService {

    /**
     * 根据产品kay查找设备
     *
     * @param dkey device key
     * @return ProductDto
     */
    DeviceDto findByKey(String dkey);

    /**
     * 解除绑定 设备
     *
     * @param pkey  product key
     * @param dkeys device keys
     */
    @Transactional(rollbackFor = Exception.class)
    Map<String, List<String>> unbinding(String pkey, List<String> dkeys);
}

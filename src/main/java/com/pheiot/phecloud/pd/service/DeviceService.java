package com.pheiot.phecloud.pd.service;

import com.pheiot.phecloud.pd.dto.DeviceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface DeviceService {


    /**
     * 根据产品kay查找设备
     *
     * @param key key
     * @return ProductDto
     */
    DeviceDto findByKay(String key);

    /**
     * 绑定设备
     *
     * @param deviceDto deviceDto
     */
    @Transactional(rollbackFor = Exception.class)
    DeviceDto binding(DeviceDto deviceDto);

    /**
     * 修改设备
     *
     * @param deviceDto deviceDto
     */
    @Transactional(rollbackFor = Exception.class)
    void update(DeviceDto deviceDto);

    /**
     * 修改设备状态
     *
     * @param isEnabled isEnabled
     */
    @Transactional(rollbackFor = Exception.class)
    void changeEnabledTo(String key, boolean isEnabled);


    /**
     * 解除绑定 设备
     *
     * @param keys key
     */
    @Transactional(rollbackFor = Exception.class)
    Map<String, List<String>> unbinding(String productKey, List<String> keys);

}

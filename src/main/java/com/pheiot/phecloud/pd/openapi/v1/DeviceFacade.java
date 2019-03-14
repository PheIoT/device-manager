package com.pheiot.phecloud.pd.openapi.v1;

import com.google.common.collect.Maps;
import com.pheiot.phecloud.pd.dto.DeviceDto;
import com.pheiot.phecloud.pd.openapi.ResponseEntity;
import com.pheiot.phecloud.pd.openapi.v1.vo.DeviceVO;
import com.pheiot.phecloud.pd.service.DeviceService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Li
 */
@RestController
@RequestMapping("/app/v1/device")
public class DeviceFacade {

    private static Logger log = LoggerFactory.getLogger(DeviceFacade.class);

    @Resource
    private DeviceService deviceService;

    @PostMapping("/binding")
    public ResponseEntity binding(@RequestBody DeviceVO deviceVO) {
        DeviceDto dto = DeviceVO.vo2Dto(deviceVO);

        DeviceDto responseDto;
        DeviceVO responseVo = new DeviceVO();

        try {
            responseDto = deviceService.binding(dto);
            DeviceVO.dto2Vo(responseDto, responseVo);
        } catch (ApplicationException ex) {
            log.error("Binding device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Binding device error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
    }

    @PostMapping("/unbinding")
    public ResponseEntity unbinding(@RequestBody Map vo) {
        String productKey = vo.get("product_key").toString();
        List<String> deviceKeys = (List<String>) vo.get("devices");
        Map<String, List<String>> req;
        try {
            req = deviceService.unbinding(productKey, deviceKeys);
        } catch (ApplicationException ex) {
            log.error("Binding device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Binding device error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(req);
    }

    @PatchMapping("/{key}/enabled")
    public ResponseEntity changeEnabledTo(@PathVariable("key") String key, @RequestParam("is_enabled") boolean isEnabled) {
        try {
            deviceService.changeEnabledTo(key, isEnabled);
        } catch (ApplicationException ex) {
            log.error("Updating device status error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Updating device status error.");
        }

        Map response = Maps.newHashMap();
        response.put("dev_key", key);
        response.put("is_enabled", isEnabled);

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(response);
    }

    @GetMapping("/{key}")
    public ResponseEntity findProductByKey(@PathVariable("key") String key) {
        DeviceVO responseVo = new DeviceVO();

        try {
            DeviceDto dto = deviceService.findByKay(key);
            DeviceVO.dto2Vo(dto, responseVo);
        } catch (ApplicationException ex) {
            log.error("Find device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Find device status error.");
        }


        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
    }

    @PutMapping("/{key}")
    public ResponseEntity update(@RequestBody DeviceVO deviceVO) {
        DeviceVO responseVo = new DeviceVO();
        DeviceVO.vo2Dto(deviceVO);

        try {
            DeviceDto dto = DeviceVO.vo2Dto(deviceVO);
            deviceService.update(dto);
        } catch (ApplicationException ex) {
            log.error("Find device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Find device status error.");
        }

        Map response = Maps.newHashMap();
        response.put("dev_key", deviceVO.getDev_key());
        response.put("dev_nameBODY", deviceVO.getRemark());
        response.put("is_enabled", deviceVO.getIs_enabled());

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
    }

}

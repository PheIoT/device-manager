/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.v1;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pheiot.phecloud.pd.dto.DeviceConditionDto;
import com.pheiot.phecloud.pd.dto.DeviceDto;
import com.pheiot.phecloud.pd.openapi.ResponseEntity;
import com.pheiot.phecloud.pd.openapi.ResponsePageEntity;
import com.pheiot.phecloud.pd.openapi.SecurityPolice;
import com.pheiot.phecloud.pd.openapi.exception.BusinessException;
import com.pheiot.phecloud.pd.openapi.v1.vo.DeviceVO;
import com.pheiot.phecloud.pd.service.ApiDeviceService;
import com.pheiot.phecloud.pd.utils.ApplicationException;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/api/v1/device")
public class DeviceApi {

    private static Logger log = LoggerFactory.getLogger(DeviceApi.class);

    @Resource
    private ApiDeviceService deviceService;

    @PostMapping("/binding")
    public ResponseEntity binding(@RequestBody DeviceVO deviceVO,
                                  @RequestHeader(SecurityPolice.HTTP_HEADER_USER_TOKEN) String userToken) {
        SecurityPolice.checkUserToken(userToken);

        DeviceDto dto = DeviceVO.vo2Dto(deviceVO);

        DeviceDto responseDto;
        DeviceVO responseVo = new DeviceVO();

        try {
            //TODO: userToken的提取和校验，传入service的应为uid
            responseDto = deviceService.binding(userToken, dto);
            DeviceVO.dto2Vo(responseDto, responseVo);
        } catch (ApplicationException ex) {
            log.error("Binding device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Binding device error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
    }

    @PostMapping("/unbinding")
    public ResponseEntity unbinding(@RequestBody Map vo,
                                    @RequestHeader(SecurityPolice.HTTP_HEADER_USER_TOKEN) String userToken) {

        SecurityPolice.checkUserToken(userToken);

        String productKey = vo.get("product_key").toString();
        List<String> deviceKeys = (List<String>) vo.get("devices");
        Map<String, List<String>> req;
        try {
            req = deviceService.unbinding(userToken, productKey, deviceKeys);
        } catch (ApplicationException ex) {
            log.error("Binding device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Binding device error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(req);
    }

    @PatchMapping("/{key}/enabled")
    public ResponseEntity changeEnabledTo(@PathVariable("key") String deviceKey,
                                          @RequestBody Map body,
                                          @RequestHeader(SecurityPolice.HTTP_HEADER_USER_TOKEN) String userToken
    ) {
        SecurityPolice.checkUserToken(userToken);

        DeviceDto dto;
        try {
            if (body == null || body.get("is_enabled") == null || StringUtils.isBlank(body.get("is_enabled").toString())) {
                throw new BusinessException("Parameter error.");
            }
            dto = deviceService.changeEnabledTo(userToken, deviceKey, Boolean.valueOf(body.get("is_enabled").toString()));
        } catch (ApplicationException ex) {
            log.error("Updating device status error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Updating device status error.");
        }

        Map response = Maps.newHashMap();
        response.put("device_key", dto.getDkey());
        response.put("is_enabled", dto.getIsEnabled());

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(response);
    }

    @GetMapping("/{key}")
    public ResponseEntity findDeviceByKey(@PathVariable("key") String deviceKey,
                                           @RequestHeader(SecurityPolice.HTTP_HEADER_USER_TOKEN) String userToken) {
        SecurityPolice.checkUserToken(userToken);

        DeviceVO responseVo = new DeviceVO();
        try {
            DeviceDto dto = deviceService.findByProductKeyAndDeviceKey(userToken, deviceKey);
            DeviceVO.dto2Vo(dto, responseVo);
        } catch (ApplicationException ex) {
            log.error("Find device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Find device status error.");
        }

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(responseVo);
    }

    @PutMapping("/{key}")
    public ResponseEntity update(
            @PathVariable("key") String deviceKey,
            @RequestBody DeviceVO deviceVO,
            @RequestHeader(SecurityPolice.HTTP_HEADER_USER_TOKEN) String userToken) {
        SecurityPolice.checkUserToken(userToken);
        DeviceDto resDto;
        try {
            DeviceDto dto = DeviceVO.vo2Dto(deviceVO);
            dto.setDkey(deviceKey);

            //TODO: userToken的提取和校验，传入service的应为uid
            resDto = deviceService.update(userToken, dto);
        } catch (ApplicationException ex) {
            log.error("Find device error.{}", ex.getMessage());
            return ResponseEntity.ofFailed().data("Find device status error.");
        }

        Map response = Maps.newHashMap();
        response.put("device_key", resDto.getDkey());
        response.put("device_name", resDto.getDisplayName());
        response.put("remark", resDto.getRemark());

        return ResponseEntity.ofSuccess().status(HttpStatus.OK).data(response);
    }

    @GetMapping
    public ResponsePageEntity findAllProduct(@RequestParam(value = "limit", required = false) Integer limit,
                                             @RequestParam(value = "offset", required = false) Integer offset,
                                             @RequestParam(value = "show_disabled", required = false) Boolean showDisabled,
                                             @RequestParam(value = "product_key", required = true) String productKey,
                                             @RequestHeader("phe-application-user-token") String userToken) {

        DeviceConditionDto conditionDto = new DeviceConditionDto();
        conditionDto.setLimit(limit);
        conditionDto.setOffset(offset);
        conditionDto.setShowDisabled(showDisabled);

        List<DeviceVO> responseVoList = Lists.newArrayList();

        try {
            //TODO: userToken的提取和校验，传入service的应为uid

            List<DeviceDto> dtos = deviceService.findByProductKeyPageable(userToken, productKey, conditionDto);
            for (DeviceDto dto : dtos) {
                responseVoList.add(DeviceVO.dto2Vo(dto));
            }

        } catch (ApplicationException ex) {
            log.error("Find device error.{}", ex.getMessage());
            return ResponsePageEntity.ofFailed().data("Find device error.");
        }

        return ResponsePageEntity.ofSuccess().status(HttpStatus.OK).data(responseVoList).total(responseVoList.size());
    }

}

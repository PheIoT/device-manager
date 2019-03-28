package com.pheiot.phecloud.pd.openapi.v1.vo;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import com.pheiot.phecloud.pd.dto.DeviceDto;
import lombok.Data;

@Data
public class DeviceVO extends AbstractValueObject {
    //产品key
    private String product_key;
    //产品类型 normal/gateway
    private String product_type;
    //设备key
    private String dev_key;
    //设备名称
    private String dev_name;
    // 设备识别码，可以为mac地址，sn序列号等
    private String dev_sn;
    //是否启用
    private Boolean is_enabled;
    //是否在线
    private Boolean is_online;
    //备注
    private String remark;

    public static void dto2Vo(DeviceDto dto, DeviceVO vo) {
        convert2Vo(dto, vo);
    }

    public static DeviceVO dto2Vo(DeviceDto dto) {
        DeviceVO vo = new DeviceVO();
        convert2Vo(dto, vo);
        return vo;
    }

    public static void vo2Dto(DeviceVO vo, DeviceDto dto) {
        convert2Dto(vo, dto);
    }

    public static DeviceDto vo2Dto(DeviceVO vo) {
        DeviceDto dto = new DeviceDto();
        convert2Dto(vo, dto);
        return dto;
    }

    private static void convert2Vo(DeviceDto dto, DeviceVO vo) {
        vo.setProduct_key(dto.getPkey());
        vo.setProduct_type(dto.getNodeType());
        vo.setDev_key(dto.getDkey());
        vo.setDev_name(dto.getDisplayName());
        vo.setDev_sn(dto.getDsn());
        vo.setIs_enabled(dto.getIsEnabled());
        vo.setRemark(dto.getRemark());
    }

    private static void convert2Dto(DeviceVO vo, DeviceDto dto) {
        dto.setPkey(vo.getProduct_key());
        dto.setNodeType(vo.getProduct_type());
        dto.setDkey(vo.getProduct_key());
        dto.setDisplayName(vo.getDev_name());
        dto.setDsn(vo.getDev_sn());
        dto.setIsEnabled(vo.getIs_enabled());
        dto.setRemark(vo.getRemark());
    }

}

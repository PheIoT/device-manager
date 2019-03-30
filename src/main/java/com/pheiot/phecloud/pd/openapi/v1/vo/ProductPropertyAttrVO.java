/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.v1.vo;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import com.pheiot.phecloud.pd.dto.ProductPropertyDto;
import lombok.Data;

@Data
public class ProductPropertyAttrVO extends AbstractValueObject {
    private String code;
    private String name;
    // bool,steing,number,radio
    private String data_type;
    private String data_conf;
    //r,rw
    private String function_type;


    public static void dto2Vo(ProductPropertyDto dto, ProductPropertyAttrVO vo) {
        convert2Vo(dto, vo);
    }

    public static ProductPropertyAttrVO dto2Vo(ProductPropertyDto dto) {
        ProductPropertyAttrVO vo = new ProductPropertyAttrVO();
        convert2Vo(dto, vo);
        return vo;
    }

    public static void vo2Dto(ProductPropertyAttrVO vo, ProductPropertyDto dto) {
        convert2Dto(vo, dto);
    }

    public static ProductPropertyDto vo2Dto(ProductPropertyAttrVO vo) {
        ProductPropertyDto dto = new ProductPropertyDto();
        convert2Dto(vo, dto);
        return dto;
    }

    private static void convert2Vo(ProductPropertyDto dto, ProductPropertyAttrVO vo) {
        vo.setCode(dto.getCode());
        vo.setName(dto.getDisplayName());
        vo.setData_type(dto.getDataType());
        vo.setData_conf(dto.getDataTypeConf());
        vo.setFunction_type(dto.getFunctionType());
    }

    private static void convert2Dto(ProductPropertyAttrVO vo, ProductPropertyDto dto) {
        dto.setCode(vo.getCode());
        dto.setDisplayName(vo.getName());
        dto.setDataType(vo.getData_type());
        dto.setDataTypeConf(vo.getData_conf());
        dto.setFunctionType(vo.getFunction_type());
    }
}

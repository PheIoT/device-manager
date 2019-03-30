/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.v1.vo;

import com.google.common.collect.Lists;
import com.pheiot.bamboo.common.dto.AbstractValueObject;
import com.pheiot.phecloud.pd.dto.ProductPropertyDto;
import com.pheiot.phecloud.pd.dto.ProductPropertyFullDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductPropertyVO extends AbstractValueObject {
    private String prod_key;
    private String prod_name;
    private List<ProductPropertyAttrVO> attr;

    public static void dto2Vo(ProductPropertyFullDto dto, ProductPropertyVO vo) {
        convert2Vo(dto, vo);
    }

    public static ProductPropertyVO dto2Vo(ProductPropertyFullDto dto) {
        ProductPropertyVO vo = new ProductPropertyVO();
        convert2Vo(dto, vo);
        return vo;
    }

    public static void vo2Dto(ProductPropertyVO vo, ProductPropertyFullDto dto) {
        convert2Dto(vo, dto);
    }

    public static ProductPropertyFullDto vo2Dto(ProductPropertyVO vo) {
        ProductPropertyFullDto dto = new ProductPropertyFullDto();
        convert2Dto(vo, dto);
        return dto;
    }

    private static void convert2Vo(ProductPropertyFullDto dto, ProductPropertyVO vo) {
        vo.setProd_name(dto.getPDisplayName());
        vo.setProd_key(dto.getPkey());
        List<ProductPropertyAttrVO> itemList = Lists.newArrayList();
        for (ProductPropertyDto d : dto.getAttr()) {
            itemList.add(ProductPropertyAttrVO.dto2Vo(d));
        }
        vo.setAttr(itemList);
    }

    private static void convert2Dto(ProductPropertyVO vo, ProductPropertyFullDto dto) {
        dto.setPkey(vo.getProd_key());
        dto.setPDisplayName(vo.getProd_name());

        List<ProductPropertyDto> itemList = Lists.newArrayList();
        for (ProductPropertyAttrVO v : vo.getAttr()) {
            itemList.add(ProductPropertyAttrVO.vo2Dto(v));
        }
        dto.setAttr(itemList);
    }
}

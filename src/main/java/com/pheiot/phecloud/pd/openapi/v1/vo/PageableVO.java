/*
 * Copyright (c) 2018. For PheIot Group.
 */

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.v1.vo;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import com.pheiot.phecloud.pd.dto.ProductConditionDto;
import lombok.Data;

@Data
public class PageableVO extends AbstractValueObject {
    //返回记录的数量
    private Integer limit;
    //返回记录的开始位置 即跳过的条数，间接表示页数
    private Integer offset;
    //是否显示已禁用的对象，1:显示，0:不显示
    private Integer show_disabled;

    public static void dto2Vo(ProductConditionDto dto, PageableVO vo) {
        convert2Vo(dto, vo);
    }

    public static PageableVO dto2Vo(ProductConditionDto dto) {
        PageableVO vo = new PageableVO();
        convert2Vo(dto, vo);
        return vo;
    }

    public static void vo2Dto(PageableVO vo, ProductConditionDto dto) {
        convert2Dto(vo, dto);
    }

    public static ProductConditionDto vo2Dto(PageableVO vo) {
        ProductConditionDto dto = new ProductConditionDto();
        convert2Dto(vo, dto);
        return dto;
    }

    private static void convert2Vo(ProductConditionDto dto, PageableVO vo) {
        vo.setLimit(dto.getLimit());
        vo.setOffset(dto.getOffset());
        vo.setShow_disabled(dto.getShowDisabled() == true ? 1 : 0);
    }

    private static void convert2Dto(PageableVO vo, ProductConditionDto dto) {
        dto.setLimit(vo.getLimit());
        dto.setOffset(vo.getOffset());
        dto.setShowDisabled(vo.getShow_disabled() == 1 ? true : false);
    }

}

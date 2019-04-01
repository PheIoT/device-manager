/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dto;

import lombok.Data;

@Data
public class ProductConditionDto {
    private Integer limit;
    private Integer offset;
    private Boolean showDisabled;
}

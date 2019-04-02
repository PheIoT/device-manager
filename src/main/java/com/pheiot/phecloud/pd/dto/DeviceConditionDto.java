/*
 * Copyright (c) 2018. For PheIot Group.
 */

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dto;

import lombok.Data;

@Data
public class DeviceConditionDto extends PageableDto {
    private Boolean showDisabled;
    private String productKey;
}

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dto;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import lombok.Data;

@Data
public class PageableDto extends AbstractValueObject {
    private Integer limit;
    private Integer offset;
}

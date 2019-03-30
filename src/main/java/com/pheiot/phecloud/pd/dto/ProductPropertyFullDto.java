/*
 * Copyright (c) 2018. For PheIot Group.
 */

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dto;

import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductPropertyFullDto extends BaseEntity {
    private String pkey;
    private String pDisplayName;
    private List<ProductPropertyDto> attr;
}

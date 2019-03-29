/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dto;

import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;

@Data
public class ProductPropertyDto extends BaseEntity {
    private String code;
    private String displayName;
    private String dataType;
    private String dataTypeConf;
    private String functionType;
    private String pkey;
}

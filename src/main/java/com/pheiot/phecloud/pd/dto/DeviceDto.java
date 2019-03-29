/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.dto;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import lombok.Data;

import java.sql.Timestamp;


/**
 * @author Peter Li @Data 2019/3/12
 */
@Data
public class DeviceDto extends AbstractValueObject {

    private String dkey;
    private String displayName;
    private String dsn;
    private String secret;
    private Boolean isOnline;
    private Boolean isEnabled;
    private String remark;

    private String pkey;
    private String nodeType;
    private Timestamp createAt;
    private Timestamp updateAt;
}

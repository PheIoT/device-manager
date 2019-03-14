package com.pheiot.phecloud.pd.dto;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import lombok.Data;

import java.sql.Timestamp;


/**
 * @author Peter Li @Data 2019/3/12
 */
@Data
public class DeviceDto extends AbstractValueObject {

    private String name;

    private String kay;

    private String mac;

    private String isOnline;

    private Boolean isEnabled;

    private String remark;

    private String productKey;

    private String productType;

    private Timestamp createAt;

    private Timestamp updateAt;
}

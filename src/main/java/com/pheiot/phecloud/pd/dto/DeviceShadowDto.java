package com.pheiot.phecloud.pd.dto;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import lombok.Data;

import java.sql.Timestamp;


/**
 * @author Peter Li @Data 2019/3/12
 */
@Data
public class DeviceShadowDto extends AbstractValueObject {
    private String productKey;
    private String devKey;
    private Timestamp updateAt;
    private String payload;
}

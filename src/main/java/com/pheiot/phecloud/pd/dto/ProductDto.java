package com.pheiot.phecloud.pd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pheiot.bamboo.common.dto.AbstractValueObject;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductDto extends AbstractValueObject {

    private Long id;
    private String name;
    private String kay;
    private String secret;
    private String type;
    private boolean isEnabled;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Timestamp createAt;
    private String remark;

    private String userKey;
}

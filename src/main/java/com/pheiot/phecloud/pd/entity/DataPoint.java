package com.pheiot.phecloud.pd.entity;

import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "t_datapoint")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class DataPoint extends BaseEntity {

    @Column(columnDefinition = "varchar(50) COMMENT '数据点Key'")
    private String kay;

    @Column(columnDefinition = "varchar(50) COMMENT '数据点显示名'")
    private String name;

    @Column(columnDefinition = "varchar(50) COMMENT '数据类型bool/string/radio/number'")
    private String dataType;

    @Column(columnDefinition = "varchar(50) COMMENT '产品Key'")
    private String productKey;

}

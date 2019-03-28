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
@Table(name = "t_productproperty")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class ProductProperty extends BaseEntity {

    @Column(columnDefinition = "varchar(32) COMMENT '数据点识别码'")
    private String code;

    @Column(columnDefinition = "varchar(255) COMMENT '数据点显示名'")
    private String displayName;

    @Column(columnDefinition = "varchar(10) COMMENT '数据类型bool/string/radio/number'")
    private String dataType;

    @Column(columnDefinition = "varchar(255) COMMENT '数据类型结构配置'")
    private String dataTypeConf;

    @Column(columnDefinition = "varchar(10) COMMENT '数据点功能类型，r/rw, 即读/读写'")
    private String functionType;

    @Column(columnDefinition = "varchar(32) COMMENT '产品Key'")
    private String pkey;

}

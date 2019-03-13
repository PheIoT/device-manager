package com.pheiot.phecloud.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_product")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {

    @Column(columnDefinition = "varchar(50) COMMENT '产品Key'")
    private String kay;

    @Column(columnDefinition = "varchar(100) COMMENT '产品名称'")
    private String name;

    @Column(columnDefinition = "varchar(50) COMMENT '产品秘钥'")
    private String secret;

    @Column(columnDefinition = "varchar(20) COMMENT '产品类型，Normal和Gateway'")
    private String type;

    @Column(columnDefinition = "varchar(20) COMMENT '产品状态，是否启用'")
    private Boolean isEnabled;

    @CreationTimestamp
    @Column(columnDefinition = " datetime COMMENT '创建时间' ", nullable = false, updatable = false)
    @JsonFormat(pattern = " yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createAt;

    @Column(columnDefinition = "varchar(255) COMMENT '产品描述'")
    private String remark;

    @Column(columnDefinition = "varchar(100) COMMENT '用户Key'")
    private String userKey;
}

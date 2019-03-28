package com.pheiot.phecloud.pd.entity;

import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "t_product")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {

    @Column(columnDefinition = "varchar(32) COMMENT '产品Key'")
    private String pkey;

    @Column(columnDefinition = "varchar(255) COMMENT '产品显示名称'")
    private String displayName;

    @Column(columnDefinition = "varchar(12) COMMENT '产品秘钥'")
    private String secret;

    @Column(columnDefinition = "varchar(20) COMMENT '产品节点类型，Normal和Gateway'")
    private String nodeType;

    @Column(columnDefinition = "varchar(20) COMMENT '产品通信类型，wifi/g345/ble'")
    private String commType;

    @Column(columnDefinition = "varchar(20) COMMENT '产品状态，是否启用'")
    private Boolean isEnabled;

    @CreationTimestamp
    @Column(columnDefinition = " datetime COMMENT '创建时间' ", nullable = false, updatable = false)
    private Timestamp createAt;

    @Column(columnDefinition = " datetime COMMENT '更新时间' ", nullable = true, insertable = false)
    @UpdateTimestamp
    private Timestamp updateAt;

    @Column(columnDefinition = "varchar(255) COMMENT '产品描述'")
    private String remark;

    @Column(columnDefinition = "varchar(32) COMMENT '用户key'")
    private String ukey;
}

/*
 * Copyright (c) 2018. For PheIot Group.
 */

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


/**
 * @author Peter Li @Data 2019/3/12
 */
@Entity
@Table(name = "t_device")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Device extends BaseEntity {

    @Column(columnDefinition = " varchar(32) COMMENT '设备key'")
    private String dkey;

    @Column(columnDefinition = " varchar(255) COMMENT '设备显示名称' ")
    private String displayName;

    @Column(columnDefinition = " varchar(32) COMMENT '设备编码，例如MAC地址或者SN等'")
    private String dsn;

    @Column(columnDefinition = "varchar(32) COMMENT '设备秘钥'")
    private String secret;

    @Column(columnDefinition = " bit COMMENT '设备在线状态'")
    private Boolean isOnline;

    @Column(columnDefinition = " bit COMMENT '设备启用状态'")
    private Boolean isEnabled;

    @Column(columnDefinition = " varchar(255) COMMENT '备注")
    private String remark;

    @Column(columnDefinition = " varchar(32) COMMENT '所属产品Key' ")
    private String pkey;

    @CreationTimestamp
    @Column(columnDefinition = " datetime COMMENT '创建时间' ", nullable = false, updatable = false)
    private Timestamp createAt;

    @Column(columnDefinition = " datetime COMMENT '更新时间' ", nullable = true, insertable = false)
    @UpdateTimestamp
    private Timestamp updateAt;
}

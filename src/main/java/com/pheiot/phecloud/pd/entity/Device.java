package com.pheiot.phecloud.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

    @Column(columnDefinition = " varchar(255) COMMENT '设备名称' ")
    private String name;

    @Column(columnDefinition = " varchar(50) COMMENT '设备key'")
    private String kay;

    @Column(columnDefinition = " varchar(36) COMMENT 'MAC地址'")
    private String mac;

    @Column(columnDefinition = " varchar(10) COMMENT '设备在线状态'")
    private String isOnline;

    @Column(columnDefinition = " varchar(10) COMMENT '设备启用状态'")
    private String isEnabled;

    @Column(columnDefinition = " varchar(255) COMMENT '备注")
    private String remark;

    @Column(columnDefinition = " varchar(50) COMMENT '产品Key' ")
    private String productKey;

    @CreationTimestamp
    @Column(columnDefinition = " datetime COMMENT '创建时间' ", nullable = false, updatable = false)
    @JsonFormat(pattern = " yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createAt;

    @Column(columnDefinition = " datetime COMMENT '更新时间' ", nullable = true, insertable = false)
    @JsonFormat(pattern = " yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    private Timestamp updateAt;
}

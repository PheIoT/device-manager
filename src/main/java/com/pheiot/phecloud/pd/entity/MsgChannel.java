package com.pheiot.phecloud.pd.entity;

import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "t_msgchannel")
@Data
@EqualsAndHashCode(callSuper = false)
public class MsgChannel extends BaseEntity {

    @Column(columnDefinition = " varchar(255) COMMENT '产品key'")
    private String product_key;

    @Column(columnDefinition = " varchar(255) COMMENT 'Topic路由:update/error/topic'")
    private String path;

    @Column(columnDefinition = " varchar(255) COMMENT 'Topic路由:sub/pub'")
    private String mod;

    @Column(columnDefinition = " int(10) COMMENT '消息等级'")
    private Integer qos;

}

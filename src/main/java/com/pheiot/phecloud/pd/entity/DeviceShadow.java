package com.pheiot.phecloud.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pheiot.bamboo.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "t_deviceshadow")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class DeviceShadow extends BaseEntity {

    @Column(columnDefinition = " varchar(50) COMMENT '产品Key' ")
    private String productKey;

    @Column(columnDefinition = "varchar(50) COMMENT '设备Key'")
    private String devKey;

    @Column(columnDefinition = "datetime COMMENT '更新时间' ", nullable = true, insertable = false)
    @JsonFormat(pattern = " yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    private Timestamp updateAt;

    @Column(columnDefinition = " text COMMENT '影子最后的值'")
    private String value;

}

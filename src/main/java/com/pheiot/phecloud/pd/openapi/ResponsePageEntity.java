/*
 * Copyright (c) 2018. For PheIot Group.
 */

/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponsePageEntity<T> extends AbstractValueObject {
    private int status;
    private boolean success = true;
    private Integer total;
    private T data;

    public ResponsePageEntity() {
    }

    public ResponsePageEntity(boolean success) {
        this.success = success;
    }

    public ResponsePageEntity(T data) {
        this.data = data;
    }

    public ResponsePageEntity(int status) {
        this.status = status;
    }

    public ResponsePageEntity(T data, HttpStatus status) {
        this.data = data;
        this.status = status.value();
    }

    public static ResponsePageEntity ofFailed() {
        return new ResponsePageEntity(false);
    }

    public static ResponsePageEntity ofSuccess() {
        return new ResponsePageEntity(true);
    }

    public ResponsePageEntity success(boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResponsePageEntity data(T data) {
        this.setData(data);
        return this;
    }

    public ResponsePageEntity status(HttpStatus status) {
        this.setStatus(status.value());
        return this;
    }

    public ResponsePageEntity total(Integer total) {
        this.setTotal(total);
        return this;
    }
}

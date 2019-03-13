package com.pheiot.phecloud.pd.openapi;

import com.pheiot.bamboo.common.dto.AbstractValueObject;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseEntity<T> extends AbstractValueObject {
    private int status;
    private String desc = "操作成功!";
    private boolean success = true;
    private T data;

    public ResponseEntity() {
    }

    public ResponseEntity(boolean success) {
        this.success = success;
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(int status) {
        this.status = status;
    }

    public ResponseEntity(T data, HttpStatus status) {
        this.data = data;
        this.status = status.value();
    }

    public static ResponseEntity ofFailed() {
        return new ResponseEntity(false);
    }

    public static ResponseEntity ofSuccess() {
        return new ResponseEntity(true);
    }

    public ResponseEntity success(boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResponseEntity data(T data) {
        this.setData(data);
        return this;
    }

    public ResponseEntity desc(String desc) {
        this.setDesc(desc);
        return this;
    }

    public ResponseEntity status(HttpStatus status) {
        this.setStatus(status.value());
        return this;
    }
}

package com.pheiot.phecloud.pd.openapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiError of(int code, String message) {
        return new ApiError(code, message);
    }
}

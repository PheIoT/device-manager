/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi.exception;

import com.pheiot.phecloud.pd.openapi.ApiError;
import com.pheiot.phecloud.pd.openapi.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("\\{spring.application.name:\\}")
    private String systemName;

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleBadRequestException(HttpServletRequest request, HttpServletResponse response, BadRequestException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ofFailed().status(HttpStatus.BAD_REQUEST).data(ApiError.of(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleBusinessException(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ofFailed().status(HttpStatus.INTERNAL_SERVER_ERROR).data(ApiError.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ofFailed().status(HttpStatus.INTERNAL_SERVER_ERROR).data(ApiError.of(10000, ex.getMessage()));
    }

}

package com.cube.product.exceptions;


import com.cube.product.dtos.internal.ExceptionCode;

public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException (ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());

        this.code = exceptionCode.name();
    }

    public String getCode() {
        return code;
    }
}

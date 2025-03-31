package com.cube.product.exceptions;


import com.cube.product.dtos.internal.ExceptionCode;

public class BadRequestException extends BusinessException {

    public BadRequestException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}

package com.cube.product.exceptions;

import com.cube.product.dtos.internal.ExceptionCode;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}

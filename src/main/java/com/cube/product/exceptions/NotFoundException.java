package com.cube.product.exceptions;

import com.cube.product.dtos.internal.ExceptionCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}

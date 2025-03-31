package com.cube.product.exceptions;

import com.cube.product.dtos.internal.ExceptionCode;

public class ConflitException extends BusinessException {

    public ConflitException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}

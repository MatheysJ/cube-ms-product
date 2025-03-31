package com.cube.product.exceptions;

import com.cube.product.dtos.internal.ExceptionCode;

public class InternalException extends BusinessException {

    public InternalException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}

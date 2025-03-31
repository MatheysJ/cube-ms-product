package com.cube.product.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {
    private String code;

    private String message;
}

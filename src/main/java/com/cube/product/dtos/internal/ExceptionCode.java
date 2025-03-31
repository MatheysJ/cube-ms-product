package com.cube.product.dtos.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    ID_DOESNT_EXIST("Produto com id informado não existe");

    private final String message;
}

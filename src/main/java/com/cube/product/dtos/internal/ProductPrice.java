package com.cube.product.dtos.internal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {

    @NotNull
    private Double sale;

    @NotNull
    private Double standard;
}

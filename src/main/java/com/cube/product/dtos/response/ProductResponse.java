package com.cube.product.dtos.response;

import com.cube.product.dtos.internal.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String id;

    private String name;

    private String summary;

    private String image;

    private String description;

    private ProductPrice price;
}

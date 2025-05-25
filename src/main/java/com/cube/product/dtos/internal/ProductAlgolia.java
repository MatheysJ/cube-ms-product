package com.cube.product.dtos.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAlgolia {

    private String objectID;

    private String id;

    private String name;

    private String summary;

    private String image;

    private String description;

    private ProductPrice price;

}

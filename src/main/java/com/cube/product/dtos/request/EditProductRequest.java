package com.cube.product.dtos.request;

import com.cube.product.dtos.internal.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProductRequest {

    private String name;

    private String summary;

    private String description;

    private String categoryId;

    private ProductPrice price;

}

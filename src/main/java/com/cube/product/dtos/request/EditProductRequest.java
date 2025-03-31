package com.cube.product.dtos.request;

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

    private Double price;

}

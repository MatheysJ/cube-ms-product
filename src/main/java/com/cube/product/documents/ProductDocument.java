package com.cube.product.documents;

import com.cube.product.dtos.internal.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument {

    @MongoId
    private String id;

    private String name;

    private String summary;

    private String description;

    @Indexed
    private String categoryId;

    private ProductPrice price;

}

package com.cube.product.mappers;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse documentToResponse(ProductDocument productDocument);

    ProductDocument requestToDocument(ProductRequest productRequest);
}

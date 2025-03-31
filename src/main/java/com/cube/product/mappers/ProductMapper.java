package com.cube.product.mappers;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse documentToResponse(ProductDocument productDocument);

    ProductDocument requestToDocument(ProductRequest productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductDocument updateDocumentFromRequest(EditProductRequest productRequest, @MappingTarget ProductDocument productDocument);
}

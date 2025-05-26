package com.cube.product.mappers;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.internal.ProductAlgolia;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse documentToResponse(ProductDocument productDocument);

    ProductDocument requestToDocument(ProductRequest productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductDocument updateDocumentFromRequest(EditProductRequest productRequest, @MappingTarget ProductDocument productDocument);

    @Mapping(target = "objectID", source = "id")
    ProductAlgolia responseToAlgolia(ProductResponse productResponse);
}

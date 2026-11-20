package com.cube.product.mappers;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.internal.ProductAlgolia;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductMapperTests {

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void shouldUseProductIdAsAlgoliaObjectId() {
        ProductAlgolia productAlgolia = productMapper.responseToAlgolia(ProductFactory.getMockOfProductResponse());

        Assertions.assertEquals(ProductFactory.getMockOfId(), productAlgolia.getObjectID());
        Assertions.assertEquals(ProductFactory.getMockOfId(), productAlgolia.getId());
    }

    @Test
    void shouldOnlyUpdateFieldsSentInEditRequest() {
        ProductDocument productDocument = ProductFactory.getMockOfProductDocument();
        EditProductRequest editRequest = EditProductRequest.builder().name("Cubo mágico 4x4").build();

        ProductDocument editedProduct = productMapper.updateDocumentFromRequest(editRequest, productDocument);

        Assertions.assertEquals("Cubo mágico 4x4", editedProduct.getName());
        Assertions.assertEquals(ProductFactory.getMockOfProductDocument().getSummary(), editedProduct.getSummary());
        Assertions.assertEquals(ProductFactory.getMockOfPrice(), editedProduct.getPrice());
    }
}

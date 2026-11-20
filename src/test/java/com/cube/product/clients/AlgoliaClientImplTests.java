package com.cube.product.clients;

import com.algolia.api.SearchClient;
import com.cube.product.dtos.internal.ProductAlgolia;
import com.cube.product.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlgoliaClientImplTests {

    @Mock
    private SearchClient searchClient;

    @InjectMocks
    private AlgoliaClientImpl algoliaClient;

    private final ProductAlgolia mockProductAlgolia = ProductFactory.getMockOfProductAlgolia();

    @Test
    void shouldSaveProductInProductsIndex() {
        algoliaClient.saveProduct(mockProductAlgolia);

        Mockito.verify(searchClient, Mockito.times(1)).saveObject("products_index", mockProductAlgolia);
    }

    @Test
    void shouldNotThrowWhenAlgoliaFailsToSaveProduct() {
        Mockito.when(searchClient.saveObject("products_index", mockProductAlgolia)).thenThrow(new RuntimeException("Algolia offline"));

        Assertions.assertDoesNotThrow(() -> algoliaClient.saveProduct(mockProductAlgolia));
    }

    @Test
    void shouldDeleteProductFromProductsIndex() {
        algoliaClient.deleteProduct(ProductFactory.getMockOfId());

        Mockito.verify(searchClient, Mockito.times(1)).deleteObject("products_index", ProductFactory.getMockOfId());
    }

    @Test
    void shouldNotThrowWhenAlgoliaFailsToDeleteProduct() {
        Mockito.when(searchClient.deleteObject("products_index", ProductFactory.getMockOfId())).thenThrow(new RuntimeException("Algolia offline"));

        Assertions.assertDoesNotThrow(() -> algoliaClient.deleteProduct(ProductFactory.getMockOfId()));
    }
}

package com.cube.product.services;

import com.cube.product.clients.AlgoliaClient;
import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.internal.ProductAlgolia;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
import com.cube.product.exceptions.BadRequestException;
import com.cube.product.factory.ProductFactory;
import com.cube.product.mappers.ProductMapper;
import com.cube.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AlgoliaClient algoliaClient;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private final ProductDocument mockProductDocument = ProductFactory.getMockOfProductDocument();
    private final ProductResponse mockProductResponse = ProductFactory.getMockOfProductResponse();
    private final ProductAlgolia mockProductAlgolia = ProductFactory.getMockOfProductAlgolia();
    private final ProductRequest mockProductRequest = ProductFactory.getMockOfProductRequest();
    private final EditProductRequest mockEditProductRequest = ProductFactory.getMockOfEditProductRequest();
    private final String mockId = ProductFactory.getMockOfId();

    @Test
    void shouldGetAllProductsAndMapThemToResponse() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(mockProductDocument));
        Mockito.when(productMapper.documentToResponse(mockProductDocument)).thenReturn(mockProductResponse);

        List<ProductResponse> response = productService.getAllProducts();

        Assertions.assertEquals(List.of(mockProductResponse), response);
    }

    @Test
    void shouldCreateProductAndReturnIt() {
        Mockito.when(productMapper.requestToDocument(mockProductRequest)).thenReturn(mockProductDocument);
        Mockito.when(productRepository.save(mockProductDocument)).thenReturn(mockProductDocument);
        Mockito.when(productMapper.documentToResponse(mockProductDocument)).thenReturn(mockProductResponse);
        Mockito.when(productMapper.responseToAlgolia(mockProductResponse)).thenReturn(mockProductAlgolia);

        ProductResponse response = productService.createProduct(mockProductRequest);

        Assertions.assertEquals(mockProductResponse, response);
    }

    @Test
    void shouldAddCreatedProductToAlgoliaIndex() {
        Mockito.when(productMapper.requestToDocument(mockProductRequest)).thenReturn(mockProductDocument);
        Mockito.when(productRepository.save(mockProductDocument)).thenReturn(mockProductDocument);
        Mockito.when(productMapper.documentToResponse(mockProductDocument)).thenReturn(mockProductResponse);
        Mockito.when(productMapper.responseToAlgolia(mockProductResponse)).thenReturn(mockProductAlgolia);

        productService.createProduct(mockProductRequest);

        Mockito.verify(algoliaClient, Mockito.times(1)).saveProduct(mockProductAlgolia);
    }

    @Test
    void shouldEditProductAndUpdateAlgoliaIndex() {
        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.of(mockProductDocument));
        Mockito.when(productMapper.updateDocumentFromRequest(mockEditProductRequest, mockProductDocument)).thenReturn(mockProductDocument);
        Mockito.when(productRepository.save(mockProductDocument)).thenReturn(mockProductDocument);
        Mockito.when(productMapper.documentToResponse(mockProductDocument)).thenReturn(mockProductResponse);
        Mockito.when(productMapper.responseToAlgolia(mockProductResponse)).thenReturn(mockProductAlgolia);

        ProductResponse response = productService.editProduct(mockId, mockEditProductRequest);

        Assertions.assertEquals(mockProductResponse, response);
        Mockito.verify(algoliaClient, Mockito.times(1)).saveProduct(mockProductAlgolia);
    }

    @Test
    void shouldThrowBadRequestWhenEditingUnknownProduct() {
        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class,
                () -> productService.editProduct(mockId, mockEditProductRequest));

        Mockito.verify(productRepository, Mockito.never()).save(Mockito.any(ProductDocument.class));
        Mockito.verifyNoInteractions(algoliaClient);
    }

    @Test
    void shouldDeleteProductFromDatabaseAndAlgoliaIndex() {
        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.of(mockProductDocument));

        productService.deleteProduct(mockId);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(mockId);
        Mockito.verify(algoliaClient, Mockito.times(1)).deleteProduct(mockId);
    }

    @Test
    void shouldThrowBadRequestWhenDeletingUnknownProduct() {
        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, () -> productService.deleteProduct(mockId));

        Mockito.verify(productRepository, Mockito.never()).deleteById(Mockito.anyString());
        Mockito.verifyNoInteractions(algoliaClient);
    }

    @Test
    void shouldSearchProductsByTheRequestedIds() {
        Mockito.when(productRepository.findByIdIn(List.of("product-1", "product-2"))).thenReturn(List.of(mockProductDocument));
        Mockito.when(productMapper.documentToResponse(mockProductDocument)).thenReturn(mockProductResponse);

        List<ProductResponse> response = productService.getProductsByIds(ProductFactory.getMockOfProductsListBody());

        Assertions.assertEquals(List.of(mockProductResponse), response);
    }
}

package com.cube.product.services;

import com.cube.product.clients.AlgoliaClient;
import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.internal.ExceptionCode;
import com.cube.product.dtos.internal.ProductAlgolia;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.request.ProductsListBody;
import com.cube.product.dtos.response.ProductResponse;
import com.cube.product.exceptions.BadRequestException;
import com.cube.product.mappers.ProductMapper;
import com.cube.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private final AlgoliaClient algoliaClient;

    private ProductMapper productMapper;

    public List<ProductResponse> getAllProducts () {
        log.info("Started getting all products");

        List<ProductDocument> productDocuments = productRepository.findAll();

        log.info("Started returning all products");
        return productDocuments.stream().map(productMapper::documentToResponse).collect(Collectors.toList());
    }

    public ProductResponse createProduct (ProductRequest productRequest) {
        log.info("Started creating a product");
        ProductDocument newProductDocument = productRepository.save(productMapper.requestToDocument(productRequest));
        ProductResponse product = productMapper.documentToResponse(newProductDocument);

        log.info("Started to add new product in Algolia index");
        ProductAlgolia productAlgolia = productMapper.responseToAlgolia(product);
        algoliaClient.saveProduct(productAlgolia);

        return product;
    }

    public ProductResponse editProduct (String id, EditProductRequest productRequest) {
        log.info("Started getting original product to edit with id {}", id);
        ProductDocument product = this.getProductById(id);

        log.info("Started editing product with id {}", id);
        ProductDocument editedProduct = productMapper.updateDocumentFromRequest(productRequest, product);
        ProductResponse productResponse = productMapper.documentToResponse(productRepository.save(editedProduct));

        log.info("Started to edit product in Algolia index");
        ProductAlgolia productAlgolia = productMapper.responseToAlgolia(productResponse);
        algoliaClient.saveProduct(productAlgolia);

        log.info("Successfully edited the product with id {}", id);
        return productResponse;
    }

    public void deleteProduct (String id) {
        log.info("Started getting original product to delete with id {}", id);
        this.getProductById(id);

        log.info("Started deleting the product with id {}", id);
        productRepository.deleteById(id);

        log.info("Started to delete product {} in Algolia index", id);
        algoliaClient.deleteProduct(id);

        log.info("Successfully deleted the product with id {}", id);
    }

    public List<ProductResponse> getProductsByIds (List<ProductsListBody> body) {
        log.info("Started getting list of products by ids");

        List<String> ids = body.stream().map((item) -> item.getId()).toList();
        List<ProductDocument> productDocuments = productRepository.findByIdIn(ids);

        log.info("Successfully edited the product with id");
        return productDocuments.stream().map(productMapper::documentToResponse).collect(Collectors.toList());
    }

    private ProductDocument getProductById (String id) {
        Optional<ProductDocument> productDocument = productRepository.findById(id);

        if (productDocument.isEmpty()) {
            throw new BadRequestException(ExceptionCode.ID_DOESNT_EXIST);
        }

        return productDocument.get();
    }

}

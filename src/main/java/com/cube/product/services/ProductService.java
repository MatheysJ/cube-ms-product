package com.cube.product.services;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
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

        log.info("Started returning the new product");
        return productMapper.documentToResponse(newProductDocument);
    }

    public ProductResponse editProduct (String id, EditProductRequest productRequest) {
        log.info("Started editing a product");

        Optional<ProductDocument> savedProduct = productRepository.findById(id);
        ProductDocument product = savedProduct.orElseThrow(RuntimeException::new);

        ProductDocument editedProduct = productMapper.updateDocumentFromRequest(productRequest, product);

        log.info("Started returning the edited product");
        return productMapper.documentToResponse(productRepository.save(editedProduct));
    }

    public void deleteProduct (String id) {
        log.info("Started deleting the product {}", id);
        Optional<ProductDocument> savedProduct = productRepository.findById(id);
        savedProduct.orElseThrow(RuntimeException::new);

        productRepository.deleteById(id);
        log.info("Successfully deleted the product {}", id);
    }

}

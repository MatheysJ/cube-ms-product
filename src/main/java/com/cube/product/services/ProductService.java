package com.cube.product.services;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
import com.cube.product.mappers.ProductMapper;
import com.cube.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    @GetMapping()
    public List<ProductResponse> getAllProducts () {
        log.info("Started getting all products");

        List<ProductDocument> productDocuments = productRepository.findAll();

        log.info("Started returning all products");
        return productDocuments.stream().map(productMapper::documentToResponse).collect(Collectors.toList());
    }

    @PostMapping()
    public ProductResponse createProduct (ProductRequest productRequest) {
        log.info("Started creating a product");

        ProductDocument newProductDocument = productRepository.save(productMapper.requestToDocument(productRequest));

        log.info("Started returning new product");
        return productMapper.documentToResponse(newProductDocument);
    }

}

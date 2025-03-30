package com.cube.product.controllers;

import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
import com.cube.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("Getting all products");
        List<ProductResponse> products = productService.getAllProducts();

        log.info("Successfully got all products");
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        log.info("Creating a product");
        ProductResponse product = productService.createProduct(productRequest);

        log.info("Successfully created a product");
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}

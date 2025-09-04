package com.cube.product.controllers;

import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.request.ProductsListBody;
import com.cube.product.dtos.response.ProductResponse;
import com.cube.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/list")
    public ResponseEntity<List<ProductResponse>> getProductsByIds(@RequestBody @Valid List<ProductsListBody> body) {
        log.info("Getting products by ids");
        List<ProductResponse> products = productService.getProductsByIds(body);

        log.info("Successfully got products by ids");
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        log.info("Creating a product");
        ProductResponse product = productService.createProduct(productRequest);

        log.info("Successfully created a product");
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable("id") String id, @RequestBody @Valid EditProductRequest productRequest) {
        log.info("Editing a product");
        ProductResponse product = productService.editProduct(id, productRequest);

        log.info("Successfully edited a product");
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable("id") String id) {
        log.info("Deleting a product");
        productService.deleteProduct(id);

        log.info("Successfully deleted a product");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}

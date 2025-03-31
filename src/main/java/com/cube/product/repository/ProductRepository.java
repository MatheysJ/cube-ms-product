package com.cube.product.repository;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.request.ProductRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {

    List<ProductDocument> findAll();

    ProductDocument save(ProductRequest productRequest);

}

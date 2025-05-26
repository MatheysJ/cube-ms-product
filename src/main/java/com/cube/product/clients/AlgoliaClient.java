package com.cube.product.clients;

import com.cube.product.dtos.internal.ProductAlgolia;

import java.util.List;

public interface AlgoliaClient {

    void saveProduct(ProductAlgolia products);

    void deleteProduct(String id);

}

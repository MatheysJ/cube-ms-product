package com.cube.product.clients;

import com.cube.product.dtos.internal.ProductAlgolia;


public interface AlgoliaClient {

    void saveProduct(ProductAlgolia products);

    void deleteProduct(String id);

}

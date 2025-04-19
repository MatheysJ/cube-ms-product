package com.cube.product.clients;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.response.ProductResponse;

import java.util.List;

public interface AlgoliaClient {

    void saveObjects(List<ProductResponse> products);

    void updateRecord(List<ProductDocument> products);

}

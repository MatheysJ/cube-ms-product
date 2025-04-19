package com.cube.product.clients;

import com.algolia.api.SearchClient;
import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AlgoliaClientImpl implements AlgoliaClient {

    private SearchClient searchClient;

    @Override
    public void saveObjects(List<ProductResponse> products) {
        try {
            log.info("Started to add products to Algolia index");
            searchClient.saveObjects("products_index", products);

            searchClient.close();
            log.info("Successfully added products to Algolia index");
        } catch (Exception exception) {
            log.error("Error adding products to Algolia index", exception);
        }
    }

    @Override
    public void updateRecord(List<ProductDocument> products) {

    }
}

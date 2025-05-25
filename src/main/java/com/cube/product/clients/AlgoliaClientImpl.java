package com.cube.product.clients;

import com.algolia.api.SearchClient;
import com.cube.product.dtos.internal.ProductAlgolia;
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
    public void saveProduct(ProductAlgolia products) {
        try {
            log.info("Started to add product to Algolia index");
            searchClient.saveObject("products_index", products);

            log.info("Successfully added product to Algolia index");
        } catch (Exception exception) {
            log.error("Error adding products to Algolia index", exception);
        }
    }

    @Override
    public void deleteProduct(String id) {
        try {
            log.info("Started to delete product from Algolia index");
            searchClient.deleteObject("products_index", id);

            log.info("Successfully deleted product from Algolia index");
        } catch (Exception exception) {
            log.error("Error deleting product from Algolia index", exception);
        }
    }

}

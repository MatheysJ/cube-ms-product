package com.cube.product.config;

import com.algolia.api.SearchClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AlgoliaConfig {

    @Value("${algolia.applicationId}")
    private String applicationId;

    @Value("${algolia.apiKey}")
    private String apiKey;

    @Bean
    public SearchClient searchClient() {
        return new SearchClient(applicationId, apiKey);
    }
}
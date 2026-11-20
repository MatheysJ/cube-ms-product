package com.cube.product.factory;

import com.cube.product.documents.ProductDocument;
import com.cube.product.dtos.internal.ProductAlgolia;
import com.cube.product.dtos.internal.ProductPrice;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.request.ProductsListBody;
import com.cube.product.dtos.response.ProductResponse;
import com.cube.product.utils.JsonUtils;

import java.util.List;

public class ProductFactory {
    static private final String idMock = "product-1";
    static private final String nameMock = "Cubo mágico 3x3";
    static private final String summaryMock = "Cubo clássico";
    static private final String descriptionMock = "Cubo mágico 3x3 com acabamento fosco";
    static private final String categoryIdMock = "category-1";
    static private final String imageMock = "https://images.example.com/cubo.png";

    static public String getMockOfId() {
        return idMock;
    }

    static public ProductPrice getMockOfPrice() {
        return new ProductPrice(80.0, 100.0);
    }

    static public ProductDocument getMockOfProductDocument() {
        return ProductDocument.builder()
                .id(idMock)
                .name(nameMock)
                .summary(summaryMock)
                .description(descriptionMock)
                .categoryId(categoryIdMock)
                .image(imageMock)
                .price(getMockOfPrice())
                .build();
    }

    static public ProductResponse getMockOfProductResponse() {
        return ProductResponse.builder()
                .id(idMock)
                .name(nameMock)
                .summary(summaryMock)
                .description(descriptionMock)
                .image(imageMock)
                .price(getMockOfPrice())
                .build();
    }

    static public ProductAlgolia getMockOfProductAlgolia() {
        return ProductAlgolia.builder()
                .objectID(idMock)
                .id(idMock)
                .name(nameMock)
                .summary(summaryMock)
                .description(descriptionMock)
                .image(imageMock)
                .price(getMockOfPrice())
                .build();
    }

    static public ProductRequest getMockOfProductRequest() {
        return ProductRequest.builder()
                .name(nameMock)
                .summary(summaryMock)
                .description(descriptionMock)
                .categoryId(categoryIdMock)
                .image(imageMock)
                .price(getMockOfPrice())
                .build();
    }

    static public String getMockOfProductRequestAsJson() {
        return JsonUtils.toJson(getMockOfProductRequest());
    }

    static public String getMockOfInvalidProductRequestAsJson() {
        ProductRequest productRequest = getMockOfProductRequest();
        productRequest.setName("");
        productRequest.setPrice(new ProductPrice(80.0, null));

        return JsonUtils.toJson(productRequest);
    }

    static public EditProductRequest getMockOfEditProductRequest() {
        return EditProductRequest.builder()
                .name("Cubo mágico 4x4")
                .build();
    }

    static public String getMockOfEditProductRequestAsJson() {
        return JsonUtils.toJson(getMockOfEditProductRequest());
    }

    static public List<ProductsListBody> getMockOfProductsListBody() {
        return List.of(
                ProductsListBody.builder().id(idMock).quantity(1).build(),
                ProductsListBody.builder().id("product-2").quantity(3).build()
        );
    }

    static public String getMockOfProductsListBodyAsJson() {
        return JsonUtils.toJson(getMockOfProductsListBody());
    }
}

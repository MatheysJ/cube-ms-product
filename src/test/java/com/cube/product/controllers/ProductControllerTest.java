package com.cube.product.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cube.product.dtos.internal.ExceptionCode;
import com.cube.product.dtos.request.EditProductRequest;
import com.cube.product.dtos.request.ProductRequest;
import com.cube.product.dtos.response.ProductResponse;
import com.cube.product.exceptions.BadRequestException;
import com.cube.product.factory.ProductFactory;
import com.cube.product.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private final ProductResponse mockProductResponse = ProductFactory.getMockOfProductResponse();
    private final String mockId = ProductFactory.getMockOfId();

    @Test
    void shouldReturnOkWithAllProducts() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn(List.of(mockProductResponse));

        mockMvc.perform(get("/v1/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(mockId));
    }

    @Test
    void shouldReturnOkWithProductsByIds() throws Exception {
        Mockito.when(productService.getProductsByIds(Mockito.anyList())).thenReturn(List.of(mockProductResponse));

        mockMvc.perform(
                post("/v1/product/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProductFactory.getMockOfProductsListBodyAsJson())
        ).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void shouldReturnCreatedWhenCreatingAProduct() throws Exception {
        Mockito.when(productService.createProduct(Mockito.any(ProductRequest.class))).thenReturn(mockProductResponse);

        mockMvc.perform(
                post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProductFactory.getMockOfProductRequestAsJson())
        ).andExpect(status().isCreated()).andExpect(jsonPath("$.name").value(mockProductResponse.getName()));
    }

    @Test
    void shouldReturnBadRequestWhenCreatingAProductWithInvalidFields() throws Exception {
        mockMvc.perform(
                post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProductFactory.getMockOfInvalidProductRequestAsJson())
        ).andExpect(status().isBadRequest());

        Mockito.verify(productService, Mockito.never()).createProduct(Mockito.any());
    }

    @Test
    void shouldReturnCreatedWhenEditingAProduct() throws Exception {
        Mockito.when(productService.editProduct(Mockito.eq(mockId), Mockito.any(EditProductRequest.class)))
                .thenReturn(mockProductResponse);

        mockMvc.perform(
                patch("/v1/product/" + mockId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProductFactory.getMockOfEditProductRequestAsJson())
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldReturnBadRequestWhenEditingUnknownProduct() throws Exception {
        Mockito.when(productService.editProduct(Mockito.eq(mockId), Mockito.any(EditProductRequest.class)))
                .thenThrow(new BadRequestException(ExceptionCode.ID_DOESNT_EXIST));

        mockMvc.perform(
                patch("/v1/product/" + mockId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProductFactory.getMockOfEditProductRequestAsJson())
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNoContentWhenDeletingAProduct() throws Exception {
        mockMvc.perform(delete("/v1/product/" + mockId)).andExpect(status().isNoContent());

        Mockito.verify(productService).deleteProduct(mockId);
    }
}

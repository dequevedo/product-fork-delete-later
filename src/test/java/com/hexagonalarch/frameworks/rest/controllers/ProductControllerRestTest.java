package com.hexagonalarch.frameworks.rest.controllers;

import com.hexagonalarch.adapters.controllers.ProductController;
import com.hexagonalarch.adapters.presenters.GenericConverter;
import com.hexagonalarch.adapters.presenters.ProductConverter;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.domain.ProductCategory;
import com.hexagonalarch.frameworks.rest.dto.request.CreateProductRequest;
import com.hexagonalarch.frameworks.rest.dto.response.CreateProductResponse;
import com.hexagonalarch.frameworks.rest.dto.response.GetProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductControllerRest.class)
class ProductControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductController productController;

    @MockBean
    private GenericConverter genericConverter;

    @MockBean
    private ProductConverter productConverter;

    @Test
    void shouldCreateProduct() throws Exception {
        CreateProductRequest request = new CreateProductRequest("Test", 10.0, "desc", "CATEGORY");
        Product product = new Product(1L, "Test", "desc", 10.0, new ProductCategory("LANCHE"));
        CreateProductResponse response = new CreateProductResponse(1L, "Test", 10.0,"desc",  "CATEGORY");

        when(productConverter.toDomain(any())).thenReturn(product);
        when(productController.createProduct(any())).thenReturn(product);
        when(genericConverter.toDto(product, CreateProductResponse.class)).thenReturn(response);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Test",
                                  "description": "desc",
                                  "price": 10.0,
                                  "category": "CATEGORY"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    void shouldReturnProductById() throws Exception {
        Product product = new Product(1L, "Test Product", "desc", 10.0, new ProductCategory("LANCHE"));
        GetProductResponse response = new GetProductResponse(1L, "Test Product", 10.0, "desc", "LANCHE");

        when(productController.getProductById(1L)).thenReturn(product);
        when(genericConverter.toDto(product, GetProductResponse.class)).thenReturn(response);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.price").value(10.0));
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        Product product1 = new Product(1L, "P1", "desc1", 10.0, new ProductCategory("LANCHE"));
        Product product2 = new Product(2L, "P2", "desc2", 20.0, new ProductCategory("BEBIDA"));

        GetProductResponse response1 = new GetProductResponse(1L, "P1", 10.0, "desc1", "LANCHE");
        GetProductResponse response2 = new GetProductResponse(2L, "P2", 20.0, "desc2", "BEBIDA");

        when(productController.getAllProducts()).thenReturn(List.of(product1, product2));
        when(genericConverter.toDto(product1, GetProductResponse.class)).thenReturn(response1);
        when(genericConverter.toDto(product2, GetProductResponse.class)).thenReturn(response2);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("P1"))
                .andExpect(jsonPath("$[1].name").value("P2"));
    }

}

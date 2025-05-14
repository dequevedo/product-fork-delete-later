package com.hexagonalarch.core.usecases.Product;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.usecases.AbstractTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAllProductsUseCaseTest extends AbstractTest {

    @Mock
    private ProductGatewayPort productGatewayPort;

    @Test
    void shouldReturnListOfProducts() {
        Product product1 = new Product(1L, "Product 1", "Description 1", 10.0, null);
        Product product2 = new Product(2L, "Product 2", "Description 2", 20.0, null);
        List<Product> products = Arrays.asList(product1, product2);

        when(productGatewayPort.findAll()).thenReturn(products);

        GetAllProductsUseCase useCase = new GetAllProductsUseCase(productGatewayPort);
        List<Product> result = useCase.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Product 2", result.get(1).getName());
    }

    @Test
    void shouldReturnEmptyListWhenNoProductsExist() {
        when(productGatewayPort.findAll()).thenReturn(List.of());

        GetAllProductsUseCase useCase = new GetAllProductsUseCase(productGatewayPort);
        List<Product> result = useCase.getAllProducts();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

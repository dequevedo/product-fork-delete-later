package com.hexagonalarch.core.usecases.Product;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.usecases.AbstractTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CreateProductUseCaseTest extends AbstractTest {

    @Mock
    private ProductGatewayPort productGatewayPort;

    @Test
    void shouldCreateProductSuccessfully() {
        Product product = new Product(1L, "Test Product", "Test Description", 100.0, null);
        when(productGatewayPort.save(product)).thenReturn(product);

        CreateProductUseCase createProductUseCase = new CreateProductUseCase(productGatewayPort);
        Product result = createProductUseCase.createProduct(product);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals(100.0, result.getPrice());
    }
}

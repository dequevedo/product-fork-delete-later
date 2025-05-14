package com.hexagonalarch.core.usecases.Product;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.usecases.AbstractTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetProductUseCaseTest extends AbstractTest {

    @Mock
    private ProductGatewayPort productGatewayPort;

    @Test
    void shouldReturnProductWhenExists() {
        Product product = new Product(1L, "Test Product", "Test Description", 100.0, null);
        when(productGatewayPort.findById(1L)).thenReturn(Optional.of(product));

        GetProductUseCase getProductUseCase = new GetProductUseCase(productGatewayPort);
        Product result = getProductUseCase.getProductById(1L);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals(100.0, result.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(productGatewayPort.findById(999L)).thenReturn(Optional.empty());

        GetProductUseCase getProductUseCase = new GetProductUseCase(productGatewayPort);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getProductUseCase.getProductById(999L);
        });

        assertEquals("Product not found", exception.getMessage());
    }
}

package com.hexagonalarch.shared.config;

import com.hexagonalarch.adapters.controllers.ProductController;
import com.hexagonalarch.core.ports.gateways.*;
import com.hexagonalarch.core.ports.usecases.Product.CreateProductUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetAllProductsUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetProductUseCasePort;
import com.hexagonalarch.core.usecases.Product.CreateProductUseCase;
import com.hexagonalarch.core.usecases.Product.GetAllProductsUseCase;
import com.hexagonalarch.core.usecases.Product.GetProductUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public ProductController productController(
            CreateProductUseCasePort createProductUseCasePort,
            GetProductUseCasePort getProductUseCasePort,
            GetAllProductsUseCasePort getAllProductsUseCasePort) {
        return new ProductController(createProductUseCasePort, getProductUseCasePort, getAllProductsUseCasePort);
    }

    @Bean
    public CreateProductUseCasePort createProductUseCasePort(ProductGatewayPort productGatewayPort) {
        return new CreateProductUseCase(productGatewayPort);
    }

    @Bean
    public GetProductUseCasePort getProductUseCasePort(ProductGatewayPort productGatewayPort) {
        return new GetProductUseCase(productGatewayPort);
    }

    @Bean
    public GetAllProductsUseCasePort getAllProductsUseCasePort(ProductGatewayPort productGatewayPort) {
        return new GetAllProductsUseCase(productGatewayPort);
    }
}

package com.hexagonalarch.core.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class AbstractTest {

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }
}


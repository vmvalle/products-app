package com.vmvalle.productsapp.unit.infrastructure.apirest;

import com.vmvalle.productsapp.application.ProductService;
import com.vmvalle.productsapp.domain.entity.Price;
import com.vmvalle.productsapp.infrastructure.apirest.ProductController;
import com.vmvalle.productsapp.infrastructure.apirest.dto.PriceResponse;
import com.vmvalle.productsapp.infrastructure.apirest.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTests {

    @Autowired
    private ProductController controller;

    @MockBean
    private ProductService service;

    @MockBean
    private PriceMapper mapper;

    @Test
    void shouldGetPriceProductByBrandInSelectedDate() {
        // given
        var productId = 35455L;
        var brandId = 1;
        var selectedDate = LocalDateTime.of(2022, Month.JUNE, 14, 0, 0, 0);

        when(service.calculateProductPrice(productId, brandId, selectedDate)).thenReturn(mock(Price.class));
        when(mapper.toPriceResponse(any(Price.class))).thenReturn(mock(PriceResponse.class));

        // when
        var price = controller.getPriceProductByBrandInSelectedDate(productId, brandId, selectedDate);

        // then
        assertTrue(Objects.nonNull(price));
    }

}

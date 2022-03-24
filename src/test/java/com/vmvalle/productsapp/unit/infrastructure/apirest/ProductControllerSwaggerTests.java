package com.vmvalle.productsapp.unit.infrastructure.apirest;

import com.vmvalle.productsapp.domain.model.Price;
import com.vmvalle.productsapp.domain.service.ProductService;
import com.vmvalle.productsapp.infrastructure.apirest.controller.ProductController;
import com.vmvalle.productsapp.infrastructure.apirest.controller.ProductControllerSwagger;
import com.vmvalle.productsapp.infrastructure.apirest.dto.PriceResponse;
import com.vmvalle.productsapp.infrastructure.apirest.mapper.PriceRestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerSwaggerTests {

    private ProductService service = mock(ProductService.class);

    private PriceRestMapper mapper = mock(PriceRestMapper.class);

    private ProductControllerSwagger controller = new ProductController(service, mapper);

    @Test
    void shouldGetPriceProductByBrandInSelectedDate() {
        // given
        var productId = 35455L;
        var brandId = 1;
        var selectedDate = LocalDateTime.of(2022, Month.JUNE, 14, 0, 0, 0);

        when(service.calculateProductPrice(productId, brandId, selectedDate)).thenReturn(mock(Price.class));
        when(mapper.toResponse(any(Price.class))).thenReturn(mock(PriceResponse.class));

        // when
        var price = controller.getPriceProductByBrandInSelectedDate(productId, brandId, selectedDate);

        // then
        assertTrue(Objects.nonNull(price));
    }

}

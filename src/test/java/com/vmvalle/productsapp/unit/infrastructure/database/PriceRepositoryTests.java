package com.vmvalle.productsapp.unit.infrastructure.database;

import com.vmvalle.productsapp.domain.repository.PriceRepository;
import com.vmvalle.productsapp.infrastructure.database.h2.entity.PriceEntity;
import com.vmvalle.productsapp.infrastructure.database.h2.repository.PriceH2Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceRepositoryTests {

    @Autowired
    private PriceRepository repository;

    @MockBean
    private PriceH2Repository priceH2Repository;

    @Test
    void shouldFindPricesByProductIdAndBrandIdOnSelectedDate() {
        // given
        var productId = 35455L;
        var brandId = 1;
        var selectedDate = LocalDateTime.of(2022, Month.JUNE, 14, 0, 0, 0);

        var listPrices = List.of(mock(PriceEntity.class));
        when(priceH2Repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(listPrices);

        // when
        var prices = repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);

        // then
        assertFalse(prices.isEmpty());
    }

}
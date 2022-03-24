package com.vmvalle.productsapp.unit.infrastructure.database;

import com.vmvalle.productsapp.domain.model.Price;
import com.vmvalle.productsapp.domain.repository.PriceRepository;
import com.vmvalle.productsapp.infrastructure.database.h2.entity.PriceEntity;
import com.vmvalle.productsapp.infrastructure.database.h2.mapper.PriceDataMapper;
import com.vmvalle.productsapp.infrastructure.database.h2.repository.PriceH2Repository;
import com.vmvalle.productsapp.infrastructure.database.h2.repository.impl.PriceRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryTests {

    private PriceH2Repository priceH2Repository = mock(PriceH2Repository.class);
    private PriceDataMapper mapper = mock(PriceDataMapper.class);

    private PriceRepository repository = new PriceRepositoryImpl(priceH2Repository, mapper);

    @Test
    void shouldFindPricesByProductIdAndBrandIdOnSelectedDate() {
        // given
        var productId = 35455L;
        var brandId = 1;
        var selectedDate = LocalDateTime.of(2022, Month.JUNE, 14, 0, 0, 0);

        var listPricesEntity = List.of(mock(PriceEntity.class));
        var listPrices = List.of(mock(Price.class));
        when(priceH2Repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(listPricesEntity);
        when(mapper.toDomainList(listPricesEntity)).thenReturn(listPrices);

        // when
        var prices = repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);

        // then
        assertFalse(prices.isEmpty());
    }

}

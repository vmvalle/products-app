package com.vmvalle.productsapp.unit.application;

import com.vmvalle.productsapp.domain.exception.ResourceNotFoundException;
import com.vmvalle.productsapp.domain.model.Price;
import com.vmvalle.productsapp.domain.model.RangeDate;
import com.vmvalle.productsapp.domain.repository.PriceRepository;
import com.vmvalle.productsapp.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductUseCaseTests {

    @Autowired
    private ProductService service;

    @MockBean
    private PriceRepository repository;

    private Long productId;
    private Integer brandId;

    private Price price1;
    private RangeDate rangeDate1;

    private Price price2;
    private RangeDate rangeDate2;

    @BeforeEach
    public void setUp() {
        productId = 35455L;
        brandId = 1;

        price1 = mock(Price.class);
        rangeDate1 = mock(RangeDate.class);
        when(rangeDate1.getStartDate()).thenReturn(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0));
        when(rangeDate1.getEndDate()).thenReturn(LocalDateTime.of(2021, Month.JANUARY, 1, 0, 0, 0));
        when(price1.getRangeDate()).thenReturn(rangeDate1);
        when(price1.getPriority()).thenReturn(0);
        when(price1.getProductPrice()).thenReturn(25.00);

        price2 = mock(Price.class);
        rangeDate2 = mock(RangeDate.class);
        when(rangeDate2.getStartDate()).thenReturn(LocalDateTime.of(2020, Month.JUNE, 14, 15, 0, 0));
        when(rangeDate2.getEndDate()).thenReturn(LocalDateTime.of(2021, Month.JANUARY, 1, 0, 0, 0));
        when(price2.getRangeDate()).thenReturn(rangeDate2);
        when(price2.getPriority()).thenReturn(1);
        when(price2.getProductPrice()).thenReturn(20.99);
    }

    @Test
    void shouldThrowExceptionWhenListPricesIsEmpty() {
        // given
        final LocalDateTime selectedDate = LocalDateTime.of(2022, Month.JUNE, 14, 0, 0, 0);
        final String message = "Not found price by";
        final List<Price> listPrices = Collections.emptyList();

        when(repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(listPrices);

        // when & then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.calculateProductPrice(productId, brandId, selectedDate));
        assertTrue(exception.getMessage().contains(message));
    }

    @Test
    void shouldReturnPrice1WhenSelectedDateIsEqualsStartDate() {
        // given
        final LocalDateTime selectedDate = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
        final Double expectedPrice = 25.00;

        final List<Price> listPrices = List.of(price1);
        when(repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(listPrices);

        // when
        Price price = service.calculateProductPrice(productId, brandId, selectedDate);

        // then
        assertEquals(expectedPrice, price.getProductPrice());
    }

    @Test
    void shouldReturnPrice1WhenSelectedDateIsAfterStartDate() {
        // given
        final LocalDateTime selectedDate = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 1);
        final Double expectedPrice = 25.00;

        List<Price> listPrices = List.of(price1);
        when(repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(listPrices);

        // when
        Price price = service.calculateProductPrice(productId, brandId, selectedDate);

        // then
        assertEquals(expectedPrice, price.getProductPrice());
    }

    @Test
    void shouldReturnPrice1WhenSelectedDateIsBeforeEndDate() {
        // given
        final LocalDateTime selectedDate = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
        final Double expectedPrice = 25.00;

        final List<Price> listPrices = List.of(price1);
        when(repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(listPrices);

        // when
        Price price = service.calculateProductPrice(productId, brandId, selectedDate);

        // then
        assertEquals(expectedPrice, price.getProductPrice());
    }

    @Test
    void shouldReturnPrice2ByPriority() {
        // given
        final LocalDateTime selectedDate = LocalDateTime.of(2020, Month.JUNE, 15, 0, 0, 1);
        final Double expectedPrice = 20.99;

        final List<Price> listPrices = Arrays.asList(price1, price2);
        when(repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(listPrices);

        // when
        Price price = service.calculateProductPrice(productId, brandId, selectedDate);

        // then
        assertEquals(expectedPrice, price.getProductPrice());
    }

}

package com.vmvalle.productsapp.integration;

import com.vmvalle.productsapp.domain.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductIntegrationTests {

    private static final String BASE_PRODUCTS_URL = "/products";
    private static final String PRODUCT_ID_PATH_PARAM_URL = "/{productId}";
    private static final String BRAND_ID_PARAM_NAME = "brandId";
    private static final String SELECTED_DATE_PARAM_NAME = "selectedDate";
    private static final String ERROR_MSG_404 = "Not found price by";

    @Autowired
    private MockMvc mockMvc;

    /**
     * Generate scenario data for Parameterized Tests
     *
     * @return Stream of data
     */
    private static Stream<Arguments> dateAndExpectedPrice() {
        return Stream.of(
                arguments("2020-06-14T10:00:00", 35.5),
                arguments("2020-06-14T16:00:00", 25.45),
                arguments("2020-06-14T21:00:00", 35.5),
                arguments("2020-06-15T10:00:00", 30.5),
                arguments("2020-06-16T21:00:00", 38.95)
        );
    }

    /**
     * Parameterized Tests with the cases indicated in the statement
     *
     * @param selectedDate Select date in case test.
     * @param expectedPrice Expected price of product in case test.
     * @throws Exception Exception
     */
    @ParameterizedTest
    @MethodSource("dateAndExpectedPrice")
    void testBySelectedDate(String selectedDate, double expectedPrice) throws Exception {
        final Long productId = 35455L;
        final int brandId = 1;

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM_URL, productId)
                        .queryParam(BRAND_ID_PARAM_NAME, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_PARAM_NAME, selectedDate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }

    /**
     * BrandId not found case test.
     *
     * @throws Exception Exception
     */
    @Test
    void testBrandIdNotFound() throws Exception {
        final Long productId = 35455L;
        final int brandId = Integer.MAX_VALUE;
        final String selectedDate = "2020-06-16T21:00:00";

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM_URL, productId)
                        .queryParam(BRAND_ID_PARAM_NAME, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_PARAM_NAME, selectedDate))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(ERROR_MSG_404)));
    }

    /**
     * ProductId not found case test.
     *
     * @throws Exception Exception
     */
    @Test
    void testProductIdNotFound() throws Exception {
        final Long productId = Long.MAX_VALUE;
        final int brandId = 1;
        final String selectedDate = "2020-06-16T21:00:00";

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM_URL, productId)
                        .queryParam(BRAND_ID_PARAM_NAME, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_PARAM_NAME, selectedDate))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(ERROR_MSG_404)));
    }

    /**
     * Price not exist on selected date case test.
     *
     * @throws Exception Exception
     */
    @Test
    void testPriceNotExistOnSelectedDate() throws Exception {
        final Long productId = 35455L;
        final int brandId = 1;
        final String selectedDate = "2023-06-16T21:00:00";

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM_URL, productId)
                        .queryParam(BRAND_ID_PARAM_NAME, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_PARAM_NAME, selectedDate))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains(ERROR_MSG_404)));

    }

    /**
     * Date selected with invalid format case test.
     *
     * @throws Exception Exception
     */
    @Test
    void testDateBadRequestFormat() throws Exception {
        final Long productId = 35455L;
        final int brandId = 1;
        final String date = "2023/06/16 15:00:00";

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM_URL, productId)
                        .queryParam(BRAND_ID_PARAM_NAME, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_PARAM_NAME, date))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}

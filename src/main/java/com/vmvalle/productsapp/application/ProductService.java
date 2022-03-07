package com.vmvalle.productsapp.application;

import com.vmvalle.productsapp.domain.entity.Price;

import java.time.LocalDateTime;

public interface ProductService {

    /**
     * Calculate the price by product identifier and brand identifier on the selected date.
     *
     * @param productId Product identifier
     * @param brandId Brand identifier
     * @param date Selected date
     * @return Price of the product
     */
    Price calculateProductPrice(Long productId, Integer brandId, LocalDateTime date);

}

package com.vmvalle.productsapp.domain.repository;

import com.vmvalle.productsapp.domain.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> findPricesByProductIdAndBrandIdOnSelectedDate(Long productId,
                                                              Integer brandId,
                                                              LocalDateTime selectedDate);

}

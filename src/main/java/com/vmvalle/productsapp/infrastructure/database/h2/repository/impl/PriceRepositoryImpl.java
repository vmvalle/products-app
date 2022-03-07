package com.vmvalle.productsapp.infrastructure.database.h2.repository.impl;

import com.vmvalle.productsapp.domain.entity.Price;
import com.vmvalle.productsapp.domain.repository.PriceRepository;
import com.vmvalle.productsapp.infrastructure.database.h2.repository.PriceH2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceH2Repository priceH2Repository;

    /**
     * Constructor of class PriceRepositoryImpl.
     *
     * @param priceH2Repository Repository of Price for H2 database.
     */
    @Autowired
    public PriceRepositoryImpl(PriceH2Repository priceH2Repository) {
        this.priceH2Repository = priceH2Repository;
    }

    @Override
    public List<Price> findPricesByProductIdAndBrandIdOnSelectedDate(Long productId,
                                                                     Integer brandId,
                                                                     LocalDateTime selectedDate) {
        return priceH2Repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);
    }
}

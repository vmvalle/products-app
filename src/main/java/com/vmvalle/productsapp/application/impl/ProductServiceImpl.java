package com.vmvalle.productsapp.application.impl;

import com.vmvalle.productsapp.application.ProductService;
import com.vmvalle.productsapp.domain.entity.Price;
import com.vmvalle.productsapp.domain.exception.ResourceNotFoundException;
import com.vmvalle.productsapp.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;


@Service
public class ProductServiceImpl implements ProductService {

    private final PriceRepository repository;

    /**
     * Constructor of class ProductServiceImpl.
     *
     * @param repository Repository of Price
     */
    @Autowired
    public ProductServiceImpl(PriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Price calculateProductPrice(Long productId, Integer brandId, LocalDateTime selectedDate) {
        final var prices = repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);
        return prices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(() -> new ResourceNotFoundException("Not found price by productId: " + productId +
                        ", and brandId: " + brandId + ", on selectedDate: " + selectedDate));
    }
}

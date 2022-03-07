package com.vmvalle.productsapp.infrastructure.apirest.impl;

import com.vmvalle.productsapp.infrastructure.apirest.ProductController;
import com.vmvalle.productsapp.infrastructure.apirest.dto.PriceResponse;
import com.vmvalle.productsapp.infrastructure.apirest.mapper.PriceMapper;
import com.vmvalle.productsapp.application.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    private final PriceMapper priceMapper;

    /**
     * Constructor of class ProductController.
     *
     * @param productService Product service.
     * @param priceMapper Price mapper.
     */
    @Autowired
    public ProductControllerImpl(ProductService productService, PriceMapper priceMapper) {
        this.productService = productService;
        this.priceMapper = priceMapper;
    }

    public PriceResponse getPriceProductByBrandInSelectedDate(
            Long productId,
            Integer brandId,
            LocalDateTime selectedDate) {

        log.info("Received request for getPriceProductByBrandInSelectedDate with params: productId: {}, brandId: {}, date: {}", productId, brandId,
                selectedDate);

        final var price = priceMapper.toPriceResponse(productService.calculateProductPrice(productId, brandId, selectedDate));

        log.info("Returned price: {}", price);

        return price;
    }

}

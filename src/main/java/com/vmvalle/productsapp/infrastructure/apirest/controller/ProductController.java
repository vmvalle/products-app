package com.vmvalle.productsapp.infrastructure.apirest.controller;

import com.vmvalle.productsapp.infrastructure.apirest.dto.PriceResponse;
import com.vmvalle.productsapp.infrastructure.apirest.mapper.PriceRestMapper;
import com.vmvalle.productsapp.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class ProductController implements ProductControllerSwagger {

    private final ProductService productService;

    private final PriceRestMapper priceRestMapper;

    /**
     * Constructor of class ProductController.
     *
     * @param productService Product service.
     * @param priceRestMapper Price mapper.
     */
    @Autowired
    public ProductController(ProductService productService, PriceRestMapper priceRestMapper) {
        this.productService = productService;
        this.priceRestMapper = priceRestMapper;
    }

    public PriceResponse getPriceProductByBrandInSelectedDate(
            Long productId,
            Integer brandId,
            LocalDateTime selectedDate) {

        log.info("Received request for getPriceProductByBrandInSelectedDate with params: productId: {}, brandId: {}, date: {}", productId, brandId,
                selectedDate);

        final var price = priceRestMapper.toResponse(productService.calculateProductPrice(productId, brandId, selectedDate));

        log.info("Returned price: {}", price);

        return price;
    }

}

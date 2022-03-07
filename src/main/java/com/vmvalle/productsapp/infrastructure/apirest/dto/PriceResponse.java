package com.vmvalle.productsapp.infrastructure.apirest.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PriceResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("brand_id")
    private Integer brandId;

    @JsonProperty("rate_id")
    private Integer rateId;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty
    private Double price;

}

package com.vmvalle.productsapp.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Price {

    private Integer id;

    private Integer brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer rateId;

    private Long productId;

    private Integer priority;

    private Double productPrice;

    private String currency;

}

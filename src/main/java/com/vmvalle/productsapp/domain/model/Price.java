package com.vmvalle.productsapp.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Price {

    private Integer id;

    private Integer brandId;

    private RangeDate rangeDate;

    private Integer rateId;

    private Long productId;

    private Integer priority;

    private Double productPrice;

    private Currency currency;

}

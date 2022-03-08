package com.vmvalle.productsapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RangeDate {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}

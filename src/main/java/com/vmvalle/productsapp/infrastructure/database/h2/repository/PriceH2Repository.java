package com.vmvalle.productsapp.infrastructure.database.h2.repository;

import com.vmvalle.productsapp.infrastructure.database.h2.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceH2Repository extends JpaRepository<PriceEntity, Integer> {

    /**
     * Find price of product by product identifier and brand identifier in selected date.
     *
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @param selectedDate Selected date.
     * @return Optional price.
     */
    @Query(value = "SELECT p FROM PriceEntity p WHERE p.productId =?1 AND p.brandId =?2 AND p.startDate <= ?3 AND p.endDate > ?3")
    List<PriceEntity> findPricesByProductIdAndBrandIdOnSelectedDate(Long productId, Integer brandId, LocalDateTime selectedDate);

}
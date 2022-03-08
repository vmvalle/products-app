package com.vmvalle.productsapp.unit.infrastructure.database;

import com.vmvalle.productsapp.infrastructure.database.h2.entity.PriceEntity;
import com.vmvalle.productsapp.infrastructure.database.h2.mapper.PriceDataMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PriceDataMapperTests {

    @Autowired
    private PriceDataMapper mapper;

    private EasyRandom generator = new EasyRandom();

    @Test
    void shouldConvertListOfPriceEntityToListPriceModel() {
        // given
        final var pricesEntity = List.of(generator.nextObject(PriceEntity.class));

        // when
        var prices = mapper.toDomainList(pricesEntity);

        // then
        assertEquals(pricesEntity.get(0).getProductId(), prices.get(0).getProductId());
        assertEquals(pricesEntity.get(0).getBrandId(), prices.get(0).getBrandId());
        assertEquals(pricesEntity.get(0).getRateId(), prices.get(0).getRateId());
        assertEquals(pricesEntity.get(0).getStartDate(), prices.get(0).getStartDate());
        assertEquals(pricesEntity.get(0).getEndDate(), prices.get(0).getEndDate());
        assertEquals(pricesEntity.get(0).getProductPrice(), prices.get(0).getProductPrice());
    }

}

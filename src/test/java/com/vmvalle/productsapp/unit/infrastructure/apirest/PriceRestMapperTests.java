package com.vmvalle.productsapp.unit.infrastructure.apirest;

import com.vmvalle.productsapp.domain.model.Price;
import com.vmvalle.productsapp.infrastructure.apirest.mapper.PriceRestMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PriceRestMapperTests {

    @Autowired
    private PriceRestMapper mapper;

    private EasyRandom generator = new EasyRandom();

    @Test
    void shouldConvertPriceToPriceResponse() {
        // given
        final var price = generator.nextObject(Price.class);

        // when
        var priceResponse = mapper.toPriceResponse(price);

        // then
        assertEquals(priceResponse.getProductId(), price.getProductId());
        assertEquals(priceResponse.getBrandId(), price.getBrandId());
        assertEquals(priceResponse.getRateId(), price.getRateId());
        assertEquals(priceResponse.getStartDate(), price.getStartDate());
        assertEquals(priceResponse.getEndDate(), price.getEndDate());
        assertEquals(priceResponse.getPrice(), price.getProductPrice());
    }

}

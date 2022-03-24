package com.vmvalle.productsapp.unit.infrastructure.apirest;

import com.vmvalle.productsapp.domain.model.Price;
import com.vmvalle.productsapp.infrastructure.apirest.mapper.PriceRestMapper;
import com.vmvalle.productsapp.infrastructure.apirest.mapper.PriceRestMapperImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PriceRestMapperTests {

    private PriceRestMapper mapper = new PriceRestMapperImpl();

    private EasyRandom generator = new EasyRandom();

    @Test
    void shouldConvertPriceToPriceResponse() {
        // given
        final var price = generator.nextObject(Price.class);

        // when
        var priceResponse = mapper.toResponse(price);

        // then
        assertEquals(priceResponse.getProductId(), price.getProductId());
        assertEquals(priceResponse.getBrandId(), price.getBrandId());
        assertEquals(priceResponse.getRateId(), price.getRateId());
        assertEquals(priceResponse.getStartDate(), price.getRangeDate().getStartDate());
        assertEquals(priceResponse.getEndDate(), price.getRangeDate().getEndDate());
        assertEquals(priceResponse.getPrice(), price.getProductPrice());
    }

}

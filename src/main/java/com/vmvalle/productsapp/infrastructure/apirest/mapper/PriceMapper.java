package com.vmvalle.productsapp.infrastructure.apirest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmvalle.productsapp.infrastructure.apirest.dto.PriceResponse;
import com.vmvalle.productsapp.domain.entity.Price;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * Map Price object to PriceResponse.
     *
     * @param price Object of Price class.
     * @return Object of PriceResponse class.
     */
    @Mapping(target="price", source="productPrice")
    PriceResponse toPriceResponse(Price price);

}

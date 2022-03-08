package com.vmvalle.productsapp.infrastructure.apirest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmvalle.productsapp.infrastructure.apirest.dto.PriceResponse;
import com.vmvalle.productsapp.domain.model.Price;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {

    /**
     * Map Price object to PriceResponse.
     *
     * @param price Object of Price class.
     * @return Object of PriceResponse class.
     */
    @Mapping(target="price", source="productPrice")
    @Mapping(target="startDate", source="rangeDate.startDate")
    @Mapping(target="endDate", source="rangeDate.endDate")
    PriceResponse toResponse(Price price);

}

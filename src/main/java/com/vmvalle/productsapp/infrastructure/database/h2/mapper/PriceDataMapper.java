package com.vmvalle.productsapp.infrastructure.database.h2.mapper;

import com.vmvalle.productsapp.domain.model.Price;
import com.vmvalle.productsapp.infrastructure.database.h2.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceDataMapper {

    /**
     * Map Pric  object of database to Price model object.
     *
     * @param priceEntity Object of Price database class.
     * @return  List of Price model ojects.
     */
    @Mapping(target = "rangeDate.startDate", source = "startDate")
    @Mapping(target = "rangeDate.endDate", source = "endDate")
    Price toDomain(PriceEntity priceEntity);

    /**
     * Map Price list objects of database to Prices list model object.
     *
     * @param pricesEntity List of Price database objects.
     * @return  List of Price model ojects.
     */
    List<Price> toDomainList(List<PriceEntity> pricesEntity);

}

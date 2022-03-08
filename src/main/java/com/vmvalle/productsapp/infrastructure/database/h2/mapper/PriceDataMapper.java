package com.vmvalle.productsapp.infrastructure.database.h2.mapper;

import com.vmvalle.productsapp.domain.model.Price;
import com.vmvalle.productsapp.infrastructure.database.h2.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceDataMapper {

    /**
     * Map Price list objects of database to Prices list model object.
     *
     * @param pricesEntity List of Price database class objects.
     * @return  List of Price model ojects.
     */
    List<Price> toDomainList(List<PriceEntity> pricesEntity);

}

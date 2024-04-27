package com.nhatdang2604.mappers.dtos;

import com.nhatdang2604.dtos.ProductDto;
import com.nhatdang2604.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends DtoMapper<ProductDto, Product> {
    //do nothing
}

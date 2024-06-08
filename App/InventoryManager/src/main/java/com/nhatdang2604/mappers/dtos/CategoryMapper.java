package com.nhatdang2604.mappers.dtos;

import com.nhatdang2604.dtos.CategoryDto;
import com.nhatdang2604.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends DtoMapper<CategoryDto, Category> {
    //do nothing
}

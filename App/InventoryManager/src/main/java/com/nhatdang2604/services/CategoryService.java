package com.nhatdang2604.services;

import com.nhatdang2604.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> saveAll(List<CategoryDto> records);
    public List<CategoryDto> findByIds(List<Long> ids);
}

package com.nhatdang2604.services;

import com.nhatdang2604.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> saveAll(List<ProductDto> records);
    public List<ProductDto> findByIds(List<Long> ids);
}

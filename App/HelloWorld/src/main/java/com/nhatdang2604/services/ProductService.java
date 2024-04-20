package com.nhatdang2604.services;

import com.nhatdang2604.entities.Product;
import com.nhatdang2604.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    public List<Product> saveAll(List<Product> records);
    public List<ProductDto> findByIds(List<Long> ids);
}

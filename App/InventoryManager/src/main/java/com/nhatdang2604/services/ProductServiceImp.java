package com.nhatdang2604.services;

import com.nhatdang2604.entities.Product;
import com.nhatdang2604.dtos.ProductDto;
import com.nhatdang2604.mappers.dtos.ProductMapper;
import com.nhatdang2604.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> saveAll(List<ProductDto> dtos) {

        List<Product> products = dtos.stream().map(productMapper::toEntity).toList();
        List<Product> insertedProducts = productRepository.saveAll(products);
        List<ProductDto> insertedDtos = insertedProducts.stream().map(productMapper::toDto).toList();
        return insertedDtos;
    }

    @Override
    public List<ProductDto> findByIds(List<Long> ids) {
        return productRepository.findByIds(ids);
    }
}

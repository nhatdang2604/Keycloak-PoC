package com.nhatdang2604.services;

import com.nhatdang2604.entities.Product;
import com.nhatdang2604.dtos.ProductDto;
import com.nhatdang2604.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public List<Product> saveAll(List<Product> records) {
        return productRepository.saveAll(records);
    }

    @Override
    public List<ProductDto> findByIds(List<Long> ids) {
        return productRepository.findByIds(ids);
    }
}

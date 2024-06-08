package com.nhatdang2604.services;

import com.nhatdang2604.dtos.CategoryDto;
import com.nhatdang2604.dtos.ProductDto;
import com.nhatdang2604.entities.Category;
import com.nhatdang2604.entities.Product;
import com.nhatdang2604.mappers.dtos.CategoryMapper;
import com.nhatdang2604.mappers.dtos.ProductMapper;
import com.nhatdang2604.repositories.CategoryRepository;
import com.nhatdang2604.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> saveAll(List<CategoryDto> dtos) {
        List<Category> categories = dtos.stream().map(categoryMapper::toEntity).toList();
        List<Category> insertedCategories= categoryRepository.saveAll(categories);
        List<CategoryDto> insertedDtos = insertedCategories.stream().map(categoryMapper::toDto).toList();
        return insertedDtos;
    }

    @Override
    public List<CategoryDto> findByIds(List<Long> ids) {
        return categoryRepository.findByIds(ids);
    }
}

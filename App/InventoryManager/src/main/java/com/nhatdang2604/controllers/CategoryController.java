package com.nhatdang2604.controllers;

import com.nhatdang2604.dtos.CategoryDto;
import com.nhatdang2604.dtos.ProductDto;
import com.nhatdang2604.services.CategoryService;
import com.nhatdang2604.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto category) {
        List<CategoryDto> toInsert = Arrays.asList(category);
        List<CategoryDto> afterInsert = categoryService.saveAll(toInsert);
        CategoryDto inserted = afterInsert.getFirst();
        return ResponseEntity.ok(inserted);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        List<Long> toFind = Arrays.asList(id);
        List<CategoryDto> founds =  categoryService.findByIds(toFind);

        if (founds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CategoryDto found = founds.getFirst();
        return ResponseEntity.ok(found);
    }
}
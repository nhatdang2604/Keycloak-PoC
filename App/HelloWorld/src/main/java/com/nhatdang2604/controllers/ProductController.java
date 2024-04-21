package com.nhatdang2604.controllers;

import com.nhatdang2604.dtos.ProductDto;
import com.nhatdang2604.entities.Product;
import com.nhatdang2604.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto product) {
        List<ProductDto> toInsert = Arrays.asList(product);
        List<ProductDto> afterInsert = productService.saveAll(toInsert);
        ProductDto inserted = afterInsert.getFirst();
        return ResponseEntity.ok(inserted);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        List<Long> toFind = Arrays.asList(id);
        List<ProductDto> founds =  productService.findByIds(toFind);

        if (founds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ProductDto found = founds.getFirst();
        return ResponseEntity.ok(found);
    }

}
package com.nhatdang2604.repositories;

import com.nhatdang2604.entities.Product;
import com.nhatdang2604.dtos.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT new com.nhatdang2604.dtos.ProductDto(p.id, p.name)
        FROM Product p
        WHERE p.id IN :ids
    """)
    public List<ProductDto> findByIds(@Param("ids") List<Long> ids);

}

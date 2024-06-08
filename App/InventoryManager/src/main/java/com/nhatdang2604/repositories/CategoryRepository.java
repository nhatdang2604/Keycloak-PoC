package com.nhatdang2604.repositories;

import com.nhatdang2604.dtos.CategoryDto;
import com.nhatdang2604.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
        SELECT new com.nhatdang2604.dtos.CategoryDto(c.id, c.name, c.createdAt, c.updatedAt)
        FROM Category c
        WHERE c.id IN :ids
    """)
    public List<CategoryDto> findByIds(@Param("ids") List<Long> ids);

}

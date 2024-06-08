package com.nhatdang2604.mappers.dtos;

import org.mapstruct.Mapping;

public interface DtoMapper<D, E> {
    D toDto(E e);

    @Mapping(target = "createdAt", defaultExpression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", defaultExpression = "java(java.time.LocalDateTime.now())")
    E toEntity(D dto);
}

package com.nhatdang2604.mappers.dtos;

public interface DtoMapper<D, E> {
    D toDto(E e);
    E toEntity(D dto);
}

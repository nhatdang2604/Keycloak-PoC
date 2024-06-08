package com.nhatdang2604.dtos;

import java.time.LocalDateTime;

public record CategoryDto(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}

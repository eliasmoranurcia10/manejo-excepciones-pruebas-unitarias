package com.nttdata.dockerized.postgresql.model.dto.category;

public record UpdateCategoryDto(
        String description,
        String status
) {
}

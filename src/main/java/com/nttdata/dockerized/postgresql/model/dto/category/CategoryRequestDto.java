package com.nttdata.dockerized.postgresql.model.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank(message = "Agregar la descripcion de la categoría")
        String description,
        @NotBlank(message = "Agregar el status")
        String status
) {
}

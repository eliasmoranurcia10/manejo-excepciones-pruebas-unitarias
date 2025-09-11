package com.nttdata.microservicios.model.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
        Integer categoryId,

        @NotBlank(message = "Agregar la descripcion de la categoría")
        String description,

        @NotBlank(message = "Agregar el status")
        String status
) {
}

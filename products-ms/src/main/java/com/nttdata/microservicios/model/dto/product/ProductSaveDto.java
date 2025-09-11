package com.nttdata.microservicios.model.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductSaveDto(
        @NotBlank(message = "Nombre del Producto vacío")
        String name,

        @NotNull(message = "El valor del precio de venta no debe ser vacío")
        @Digits(integer = 100, fraction = 2, message = "El precio debe tener al menos 2 decimales permitidos")
        BigDecimal salePrice,

        @PositiveOrZero(message = "Solo se permite valores positivos o cero")
        Integer quantityStock,

        @Positive(message = "el id de la categoría debe ser positivo")
        Integer categoryId
) {

}

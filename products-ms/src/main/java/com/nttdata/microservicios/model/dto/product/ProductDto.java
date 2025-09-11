package com.nttdata.microservicios.model.dto.product;

import com.nttdata.microservicios.model.dto.category.CategoryDto;

import java.math.BigDecimal;

public record ProductDto(
        Integer idProduct,
        String name,
        BigDecimal salePrice,
        Integer quantityStock,
        CategoryDto category
) {
}

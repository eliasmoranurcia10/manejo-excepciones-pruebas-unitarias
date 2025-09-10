package com.nttdata.dockerized.postgresql.model.dto.product;

import com.nttdata.dockerized.postgresql.model.dto.category.CategoryDto;

import java.math.BigDecimal;

public record ProductDto(
        Integer idProduct,
        String name,
        BigDecimal salePrice,
        Integer quantityStock,
        CategoryDto category
) {
}

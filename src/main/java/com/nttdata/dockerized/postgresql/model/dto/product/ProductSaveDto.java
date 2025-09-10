package com.nttdata.dockerized.postgresql.model.dto.product;

import java.math.BigDecimal;

public record ProductSaveDto(
        String name,
        BigDecimal salePrice,
        Integer quantityStock,
        Integer categoryId
) {

}

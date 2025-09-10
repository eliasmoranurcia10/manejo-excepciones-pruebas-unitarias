package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductSaveDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {
    List<ProductDto> listAll();
    ProductDto findById(Integer id);
    ProductDto save(ProductSaveDto productSaveDto);
    ProductDto update(Integer id, ProductSaveDto productSaveDto);
    void delete(Integer id);

    List<ProductDto> listByCategoria(String descripcionCategoria);
    List<ProductDto> listByRango(BigDecimal minPrice, BigDecimal maxPrice);
}

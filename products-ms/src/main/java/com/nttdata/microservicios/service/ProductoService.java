package com.nttdata.microservicios.service;

import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.dto.product.ProductSaveDto;

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

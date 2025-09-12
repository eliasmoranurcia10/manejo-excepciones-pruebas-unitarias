package com.nttdata.microservicios.service;



import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.dto.product.ProductSaveDto;

import java.math.BigDecimal;
import java.util.List;

public interface CompositionProductService {
    List<ProductDto> listProductos();

    ProductDto getProduct(Integer id);

    List<ProductDto> getProductsByNameCategory(String nameCategory);

    List<ProductDto> getProductsByRange(BigDecimal minPrice, BigDecimal maxPrice);

    ProductDto saveProduct(ProductSaveDto productSaveDto);

    ProductDto updateProductos(Integer id, ProductSaveDto productSaveDto);

    void deleteProduct(Integer id);
}

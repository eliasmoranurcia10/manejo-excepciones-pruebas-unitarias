package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.feign.ProductFeignClient;
import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.dto.product.ProductSaveDto;
import com.nttdata.microservicios.service.CompositionProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CompositionProductServiceImpl implements CompositionProductService {

    private final ProductFeignClient productFeignClient;

    public CompositionProductServiceImpl(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<ProductDto> listProductos() {
        return productFeignClient.getProducts();
    }

    @Override
    public ProductDto getProduct(Integer id) {
        return productFeignClient.getProductById(id);
    }

    @Override
    public List<ProductDto> getProductsByNameCategory(String nameCategory) {
        return productFeignClient.getProductsByNameCategory(nameCategory);
    }

    @Override
    public List<ProductDto> getProductsByRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productFeignClient.getProductsByRange(minPrice, maxPrice);
    }

    @Override
    public ProductDto saveProduct(ProductSaveDto productSaveDto) {
        return productFeignClient.saveProduct(productSaveDto);
    }

    @Override
    public ProductDto updateProductos(Integer id, ProductSaveDto productSaveDto) {
        return productFeignClient.updateProduct(id, productSaveDto);
    }

    @Override
    public void deleteProduct(Integer id) {
        productFeignClient.deleteProduct(id);
    }
}

package com.nttdata.microservicios.feign;

import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.dto.product.ProductSaveDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "products-ms")
public interface ProductFeignClient {

    @GetMapping("/api/products")
    List<ProductDto> getProducts();

    @GetMapping("/api/products/{id}")
    ProductDto getProductById(@PathVariable Integer id);

    @GetMapping("/api/products/search")
    List<ProductDto> getProductsByNameCategory(@RequestParam String nameCategory);

    @GetMapping("/api/products/range")
    List<ProductDto> getProductsByRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice);

    @PostMapping("/api/products")
    ProductDto saveProduct(@RequestBody ProductSaveDto productSaveDto);

    @PutMapping("/api/products/{id}")
    ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductSaveDto productSaveDto);

    @DeleteMapping("/api/products/{id}")
    void deleteProduct(@PathVariable Integer id);
}

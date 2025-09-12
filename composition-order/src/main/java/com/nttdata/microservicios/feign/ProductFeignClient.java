package com.nttdata.microservicios.feign;

import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.dto.product.ProductSaveDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "products-ms", url = "http://localhost:8090/api/products")
public interface ProductFeignClient {

    @GetMapping
    List<ProductDto> getProducts();

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable Integer id);

    @GetMapping("/search")
    List<ProductDto> getProductsByNameCategory(@RequestParam String nameCategory);

    @GetMapping("/range")
    List<ProductDto> getProductsByRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice);

    @PostMapping
    ProductDto saveProduct(@RequestBody ProductSaveDto productSaveDto);

    @PutMapping("/{id}")
    ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductSaveDto productSaveDto);

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Integer id);
}

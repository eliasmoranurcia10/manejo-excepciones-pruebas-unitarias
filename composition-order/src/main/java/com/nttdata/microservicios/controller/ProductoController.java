package com.nttdata.microservicios.controller;

import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.dto.product.ProductSaveDto;
import com.nttdata.microservicios.service.CompositionProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    private final CompositionProductService compositionProductService;

    public ProductoController(CompositionProductService compositionProductService) {
        this.compositionProductService = compositionProductService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return compositionProductService.listProductos();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        return compositionProductService.getProduct(id);
    }

    @GetMapping("/search")
    public List<ProductDto> getProductsByNameCategory(@RequestParam String nameCategory) {
        return compositionProductService.getProductsByNameCategory(nameCategory);
    }

    @GetMapping("/range")
    public List<ProductDto> getProductsByRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice
    ) {
        return compositionProductService.getProductsByRange(minPrice, maxPrice);
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductSaveDto productSaveDto) {
        return compositionProductService.saveProduct(productSaveDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductSaveDto productSaveDto) {
        return compositionProductService.updateProductos(id, productSaveDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        compositionProductService.deleteProduct(id);
    }
}

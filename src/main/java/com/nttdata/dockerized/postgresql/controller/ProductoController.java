package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.ProductMapper;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductSaveDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productoService.listAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        return productoService.findById(id);
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody @Valid ProductSaveDto productSaveDto) {
        return productoService.save(productSaveDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductSaveDto productSaveDto) {
        return productoService.update(id, productSaveDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productoService.delete(id);
    }

    @GetMapping("/search")
    public List<ProductDto> getProductsByName(
            @RequestParam(required = false) String nameCategory
    ) {
        return productoService.listByCategoria(nameCategory);
    }

    @GetMapping("/range")
    public List<ProductDto> getProductsByRange(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        return productoService.listByRango(minPrice, maxPrice);
    }
}

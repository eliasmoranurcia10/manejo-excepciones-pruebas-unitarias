package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.ProductMapper;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductSaveDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import com.nttdata.dockerized.postgresql.service.ProductoService;
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
    private final CategoriaService categoriaService;
    private final ProductMapper productMapper;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService, ProductMapper productMapper) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productMapper.toProductsDto( productoService.listAll() ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
        return Optional.ofNullable(productoService.findById(id))
                .map(
                        producto ->ResponseEntity.ok( productMapper.toProductDto(producto) )
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductSaveDto productSaveDto) {
        Categoria categoria = categoriaService.findById( productSaveDto.categoryId() );
        Producto producto = productMapper.toProducto(productSaveDto);
        if( producto == null || categoria == null) return ResponseEntity.notFound().build();
        producto.setCategoria(categoria);
        Producto saved = productoService.save(producto);
        return new ResponseEntity<>( productMapper.toProductDto(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer id, @RequestBody ProductSaveDto productSaveDto) {
        Producto producto = productoService.findById(id);
        Categoria categoria = categoriaService.findById(productSaveDto.categoryId());
        if( producto == null || categoria == null) return ResponseEntity.notFound().build();
        producto.setCategoria(categoria);
        productMapper.updateProductoFromDto(productSaveDto, producto);
        return ResponseEntity.ok(productMapper.toProductDto( productoService.save(producto) ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        if( productoService.findById(id) == null ) return ResponseEntity.notFound().build();
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> getProductsByName(
            @RequestParam(required = false) String nameCategory
    ) {
        return ResponseEntity.ok(productMapper.toProductsDto( productoService.listByCategoria(nameCategory) ));
    }

    @GetMapping("/range")
    public ResponseEntity<List<ProductDto>> getProductsByRange(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        if( minPrice == null ) minPrice = BigDecimal.ZERO;
        if( maxPrice == null ) maxPrice = new BigDecimal("100000");

        return ResponseEntity.ok(
                productMapper.toProductsDto( productoService.listByRango(minPrice, maxPrice) )
        );
    }
}

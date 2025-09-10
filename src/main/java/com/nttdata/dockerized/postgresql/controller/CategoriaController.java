package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.CategoryMapper;
import com.nttdata.dockerized.postgresql.model.dto.category.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.category.UpdateCategoryDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoryMapper categoryMapper;

    public CategoriaController(CategoriaService categoriaService, CategoryMapper categoryMapper) {
        this.categoriaService = categoriaService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryMapper.toCategoriesDto( categoriaService.listAll() ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        return Optional.ofNullable(categoriaService.findById(id))
                .map(
                        categoria ->ResponseEntity.ok( categoryMapper.toCategoryDto(categoria) )
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid CategoryDto categoryDto) {
        return new ResponseEntity<>( categoryMapper.toCategoryDto(
                categoriaService.save( categoryMapper.toCategoria(categoryDto) )
        ) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id, @RequestBody @Valid UpdateCategoryDto updateCategoryDto) {
        Categoria categoria = categoriaService.findById(id);
        if( categoria == null) return ResponseEntity.notFound().build();
        categoryMapper.updateCategoriaFromDto(updateCategoryDto, categoria);
        return ResponseEntity.ok(categoryMapper.toCategoryDto( categoriaService.save(categoria) ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id){
        if( categoriaService.findById(id) == null ) return ResponseEntity.notFound().build();
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

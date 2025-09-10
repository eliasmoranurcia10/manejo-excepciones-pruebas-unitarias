package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.category.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.category.CategoryRequestDto;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoriaService.listAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Integer id) {
        return categoriaService.findById(id);
    }

    @PostMapping
    public CategoryDto save(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return categoriaService.save(categoryRequestDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Integer id, @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return categoriaService.update(id, categoryRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoriaService.delete(id);
    }

}

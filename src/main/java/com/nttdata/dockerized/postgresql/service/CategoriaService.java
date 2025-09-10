package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.category.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.category.CategoryRequestDto;

import java.util.List;

public interface CategoriaService {
    List<CategoryDto> listAll();

    CategoryDto findById(Integer id);

    CategoryDto save(CategoryRequestDto categoryRequestDto);

    CategoryDto update(Integer id, CategoryRequestDto categoryRequestDto);

    void delete(Integer id);
}
package com.nttdata.microservicios.service;

import com.nttdata.microservicios.model.dto.category.CategoryDto;
import com.nttdata.microservicios.model.dto.category.CategoryRequestDto;

import java.util.List;

public interface CategoriaService {
    List<CategoryDto> listAll();

    CategoryDto findById(Integer id);

    CategoryDto save(CategoryRequestDto categoryRequestDto);

    CategoryDto update(Integer id, CategoryRequestDto categoryRequestDto);

    void delete(Integer id);
}
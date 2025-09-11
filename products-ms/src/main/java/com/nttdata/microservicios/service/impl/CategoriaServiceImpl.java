package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.exception.BadRequestException;
import com.nttdata.microservicios.exception.InternalServerErrorException;
import com.nttdata.microservicios.exception.ResourceNotFoundException;
import com.nttdata.microservicios.mapper.CategoryMapper;
import com.nttdata.microservicios.model.dto.category.CategoryDto;
import com.nttdata.microservicios.model.dto.category.CategoryRequestDto;
import com.nttdata.microservicios.model.entity.Categoria;
import com.nttdata.microservicios.repository.CategoriaRepository;
import com.nttdata.microservicios.service.CategoriaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoryMapper categoryMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoryMapper categoryMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryDto> listAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categoryMapper.toCategoriesDto(categorias);
    }

    @Override
    public CategoryDto findById(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la categoría con el id: "+ id)
        );
        return categoryMapper.toCategoryDto(categoria);
    }

    @Override
    public CategoryDto save(CategoryRequestDto categoryRequestDto) {
        try{
            Categoria categoria = categoryMapper.toCategoriaRequest(categoryRequestDto);
            return categoryMapper.toCategoryDto(categoriaRepository.save(categoria));
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al guardar una categoría");
        }
    }

    @Override
    public CategoryDto update(Integer id, CategoryRequestDto categoryRequestDto) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        return categoriaRepository.findById(id)
                .map( categoria -> {
                    categoryMapper.updateCategoriaFromDto(categoryRequestDto, categoria);
                    return categoryMapper.toCategoryDto( categoriaRepository.save(categoria) );
                } )
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró la categoría con id:" + id)
                );
    }


    @Override
    public void delete(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la categoría con el id: "+ id)
        );

        try{
            categoriaRepository.delete(categoria);
        } catch (DataIntegrityViolationException exception) {
            throw new BadRequestException("No se puede eliminar la categoría porque tienes registros asociados");
        }
    }
}

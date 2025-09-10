package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.category.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.category.UpdateCategoryDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "categoryId", source = "idCategoria")
    @Mapping(target = "description", source = "descripcion")
    @Mapping(target = "status", source = "estado")
    CategoryDto toCategoryDto(Categoria categoria);
    List<CategoryDto> toCategoriesDto(List<Categoria> categorias);

    @InheritInverseConfiguration
    Categoria toCategoria(CategoryDto categoryDto);


    @Mapping(target = "idCategoria", ignore = true)
    @Mapping(target = "descripcion", source = "description")
    @Mapping(target = "estado", source = "status")
    @Mapping(target = "productos", ignore = true)
    void updateCategoriaFromDto(UpdateCategoryDto updateCategoryDto, @MappingTarget Categoria categoria);

    default String mapEstadoToStatus(Boolean estado){
        if(estado == null ) return "Inactivo";
        return estado? "Activo":"Inactivo";
    }

    default Boolean mapStatusToEstado(String status) {
        if(status == null ) return null;
        return "Activo".equalsIgnoreCase(status);
    }
}

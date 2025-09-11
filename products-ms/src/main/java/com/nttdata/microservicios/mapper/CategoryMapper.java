package com.nttdata.microservicios.mapper;

import com.nttdata.microservicios.model.dto.category.CategoryDto;
import com.nttdata.microservicios.model.dto.category.CategoryRequestDto;
import com.nttdata.microservicios.model.entity.Categoria;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "categoryId", source = "idCategoria")
    @Mapping(target = "description", source = "descripcion")
    @Mapping(target = "status", source = "estado", qualifiedByName = "mapEstadoToStatus")
    CategoryDto toCategoryDto(Categoria categoria);
    List<CategoryDto> toCategoriesDto(List<Categoria> categorias);

    @InheritInverseConfiguration
    @Mapping(target = "estado", source = "status", qualifiedByName = "mapStatusToEstado")
    Categoria toCategoria(CategoryDto categoryDto);

    @Mapping(target = "idCategoria", ignore = true)
    @Mapping(target = "descripcion", source = "description")
    @Mapping(target = "estado", source = "status", qualifiedByName = "mapStatusToEstado")
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoriaRequest(CategoryRequestDto categoryRequestDto);

    @InheritConfiguration(name = "toCategoriaRequest")
    void updateCategoriaFromDto(CategoryRequestDto categoryRequestDto, @MappingTarget Categoria categoria);

    @Named("mapEstadoToStatus")
    default String mapEstadoToStatus(Boolean estado){
        if(estado == null ) return "Inactivo";
        return estado? "Activo":"Inactivo";
    }

    @Named("mapStatusToEstado")
    default Boolean mapStatusToEstado(String status) {
        if(status == null ) return null;
        return "Activo".equalsIgnoreCase(status);
    }
}

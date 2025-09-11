package com.nttdata.microservicios.mapper;

import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.dto.product.ProductSaveDto;
import com.nttdata.microservicios.model.entity.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(target = "idProduct", source = "idProducto")
    @Mapping(target = "name", source = "nombre")
    @Mapping(target = "salePrice", source = "precioVenta")
    @Mapping(target = "quantityStock", source = "cantidadStock")
    @Mapping(target = "category", source = "categoria")
    ProductDto toProductDto(Producto producto);
    List<ProductDto> toProductsDto(List<Producto> productos);


    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "nombre", source = "name")
    @Mapping(target = "precioVenta", source = "salePrice")
    @Mapping(target = "cantidadStock", source = "quantityStock")
    @Mapping(target = "categoria.idCategoria", source = "categoryId")
    Producto toProducto(ProductSaveDto productSaveDto);

    @InheritConfiguration(name = "toProducto")
    void updateProductoFromDto(ProductSaveDto productSaveDto, @MappingTarget Producto producto);

}

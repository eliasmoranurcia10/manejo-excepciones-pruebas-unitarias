package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductSaveDto;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
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
    @Mapping(target = "detallePedidos", ignore = true)
    Producto toProducto(ProductSaveDto productSaveDto);

    @InheritConfiguration(name = "toProducto")
    void updateProductoFromDto(ProductSaveDto productSaveDto, @MappingTarget Producto producto);

}

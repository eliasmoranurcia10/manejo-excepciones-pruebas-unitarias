package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.InternalServerErrorException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.mapper.ProductMapper;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductSaveDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductMapper productMapper;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductMapper productMapper, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.productMapper = productMapper;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<ProductDto> listAll() {
        List<Producto> productos = productoRepository.findAll();
        return productMapper.toProductsDto(productos);
    }

    @Override
    public ProductDto findById(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        Producto producto = productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el producto con el id: "+id)
        );
        return productMapper.toProductDto(producto);
    }

    @Override
    public ProductDto save(ProductSaveDto productSaveDto) {
        try {
            Producto producto = productMapper.toProducto(productSaveDto);
            Categoria categoria = categoriaRepository.findById(productSaveDto.categoryId()).orElseThrow(
                    () -> new ResourceNotFoundException("No se encontró la categoría con el id: " + productSaveDto.categoryId())
            );
            producto.setCategoria(categoria);
            return productMapper.toProductDto(productoRepository.save(producto));
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al guardar un producto");
        }
    }

    @Override
    public ProductDto update(Integer id, ProductSaveDto productSaveDto) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");

        return productoRepository.findById(id)
                .map(producto -> {
                    Categoria categoria = categoriaRepository.findById(productSaveDto.categoryId()).orElseThrow(
                            () -> new ResourceNotFoundException("No se encontró la categoría con el id: " + productSaveDto.categoryId())
                    );
                    productMapper.updateProductoFromDto(productSaveDto, producto);
                    producto.setCategoria(categoria);
                    return productMapper.toProductDto( productoRepository.save(producto) );
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró el producto con id:" + id)
                );
    }

    @Override
    public void delete(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        Producto producto = productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el producto con el id: "+ id)
        );
        try{
            productoRepository.delete(producto);
        } catch (DataIntegrityViolationException exception) {
            throw new BadRequestException("No se puede eliminar el producto porque tienes registros asociados");
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al eliminar el producto");
        }
    }

    @Override
    public List<ProductDto> listByCategoria(String descripcionCategoria) {
        if(descripcionCategoria==null || descripcionCategoria.isBlank()) {
            throw new BadRequestException("Colocar el email a buscar en el parámetro");
        }
        List<Producto> productos = productoRepository.findByCategoria_Descripcion(descripcionCategoria);
        return productMapper.toProductsDto(productos);
    }

    @Override
    public List<ProductDto> listByRango(BigDecimal minPrice, BigDecimal maxPrice) {
        if( minPrice == null ) minPrice = BigDecimal.ZERO;
        if( maxPrice == null ) maxPrice = BigDecimal.valueOf(Double.MAX_VALUE);
        if(minPrice.compareTo(maxPrice) > 0 ) throw new BadRequestException("El precio mínimo no puede ser mayor al máximo");

        return productMapper.toProductsDto(
                productoRepository.findByPrecioVentaBetweenOrderByPrecioVentaAsc(minPrice,maxPrice)
        );
    }


}

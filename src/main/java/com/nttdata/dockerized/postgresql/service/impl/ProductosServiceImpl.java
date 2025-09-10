package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductosServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductosServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Integer idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void delete(Integer idProducto) {
        productoRepository.deleteById(idProducto);
    }

    @Override
    public List<Producto> listByCategoria(String descripcionCategoria) {
        return productoRepository.findByCategoria_Descripcion(descripcionCategoria);
    }

    @Override
    public List<Producto> listByRango(BigDecimal minimo, BigDecimal maximo) {
        return productoRepository.findByPrecioVentaBetweenOrderByPrecioVentaAsc(minimo, maximo);
    }


}

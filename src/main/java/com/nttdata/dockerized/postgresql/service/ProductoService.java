package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Producto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {
    List<Producto> listAll();
    Producto findById(Integer idProducto);
    Producto save(Producto producto);
    void delete(Integer idProducto);

    List<Producto> listByCategoria(String descripcionCategoria);
    List<Producto> listByRango(BigDecimal minimo, BigDecimal maximo);
}

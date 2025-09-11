package com.nttdata.microservicios.repository;

import com.nttdata.microservicios.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategoria_Descripcion(String descripcionCategoria);

    List<Producto> findByPrecioVentaBetweenOrderByPrecioVentaAsc(BigDecimal minimo, BigDecimal maximo);
}

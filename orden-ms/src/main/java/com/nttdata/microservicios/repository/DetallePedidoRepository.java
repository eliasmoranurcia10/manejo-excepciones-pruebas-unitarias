package com.nttdata.microservicios.repository;

import com.nttdata.microservicios.model.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
}

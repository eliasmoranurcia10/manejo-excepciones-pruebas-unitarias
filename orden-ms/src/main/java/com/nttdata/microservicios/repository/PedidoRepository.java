package com.nttdata.microservicios.repository;

import com.nttdata.microservicios.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}

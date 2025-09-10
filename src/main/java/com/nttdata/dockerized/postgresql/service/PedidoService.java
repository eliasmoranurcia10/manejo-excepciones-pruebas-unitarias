package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PedidoService {
    List<Pedido> listAll();
    Pedido findById(Integer idPedido);
    Pedido save(Pedido pedido);
    void delete(Integer idPedido);
}

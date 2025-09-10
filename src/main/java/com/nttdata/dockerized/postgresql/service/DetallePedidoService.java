package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DetallePedidoService {

    List<DetallePedido> listAll();
    DetallePedido findById(Integer idDetallePedido);
    DetallePedido save(DetallePedido detallePedido);
    void delete(Integer idDetallePedido);

}

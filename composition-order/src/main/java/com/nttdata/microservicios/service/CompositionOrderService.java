package com.nttdata.microservicios.service;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;

public interface CompositionOrderService {

    PedidoDto obtenerPedido(Integer id);
}

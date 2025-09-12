package com.nttdata.microservicios.service;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;

public interface CompositionOrderService {

    PedidoDto obtenerPedido(Integer id);

    PedidoDto savePedido(PedidoRequestDto pedidoRequestDto);
}

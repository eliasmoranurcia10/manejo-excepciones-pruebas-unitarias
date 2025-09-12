package com.nttdata.microservicios.service;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;

import java.util.List;

public interface CompositionOrderService {

    List<PedidoDto> listPedidos();

    PedidoDto obtenerPedido(Integer id);

    PedidoDto savePedido(PedidoRequestDto pedidoRequestDto);

    PedidoDto updatePedido(Integer id, PedidoRequestDto pedidoRequestDto);

    void deletePedido(Integer id);

}

package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.feign.OrderFeignClient;
import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import com.nttdata.microservicios.service.CompositionOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionOrderServiceImpl implements CompositionOrderService {

    private final OrderFeignClient orderFeignClient;

    public CompositionOrderServiceImpl(OrderFeignClient orderFeignClient) {
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public List<PedidoDto> listPedidos() {
        return orderFeignClient.getPedidos();
    }

    @Override
    public PedidoDto obtenerPedido(Integer id) {
        return orderFeignClient.getPedidoById(id);
    }

    @Override
    public PedidoDto savePedido(PedidoRequestDto pedidoRequestDto) {
        return orderFeignClient.createPedido(pedidoRequestDto);
    }

    @Override
    public PedidoDto updatePedido(Integer id, PedidoRequestDto pedidoRequestDto) {
        return orderFeignClient.updatePedido(id,pedidoRequestDto);
    }

    @Override
    public void deletePedido(Integer id) {
        orderFeignClient.deletePedido(id);
    }


}

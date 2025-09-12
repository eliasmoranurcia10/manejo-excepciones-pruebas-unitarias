package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.feign.OrderFeignClient;
import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.service.CompositionOrderService;
import org.springframework.stereotype.Service;

@Service
public class CompositionOrderServiceImpl implements CompositionOrderService {

    private final OrderFeignClient orderFeignClient;

    public CompositionOrderServiceImpl(OrderFeignClient orderFeignClient) {
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public PedidoDto obtenerPedido(Integer id) {
        return orderFeignClient.getPedidoById(id);
    }
}

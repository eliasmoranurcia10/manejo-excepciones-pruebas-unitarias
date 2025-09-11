package com.nttdata.microservicios.model.dto.detallepedido;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;

import java.math.BigDecimal;

public record DetallePedidoDto(
        Integer orderDetailId,
        PedidoDto order,
        Integer productId,
        Integer purchaseQuantity,
        BigDecimal unitPrice
) {
}

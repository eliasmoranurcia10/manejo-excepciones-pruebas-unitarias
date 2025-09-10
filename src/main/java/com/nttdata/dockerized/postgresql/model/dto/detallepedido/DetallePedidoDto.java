package com.nttdata.dockerized.postgresql.model.dto.detallepedido;

import com.nttdata.dockerized.postgresql.model.dto.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;

import java.math.BigDecimal;

public record DetallePedidoDto(
        Integer orderDetailId,
        PedidoDto order,
        ProductDto product,
        Integer purchaseQuantity,
        BigDecimal unitPrice
) {
}

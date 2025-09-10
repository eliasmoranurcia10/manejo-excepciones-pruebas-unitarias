package com.nttdata.dockerized.postgresql.model.dto.detallepedido;

import com.nttdata.dockerized.postgresql.model.dto.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;

import java.math.BigDecimal;

public record DetallePedidoRequestDto(
        Integer orderId,
        Integer idProduct,
        Integer purchaseQuantity,
        BigDecimal unitPrice
) {
}

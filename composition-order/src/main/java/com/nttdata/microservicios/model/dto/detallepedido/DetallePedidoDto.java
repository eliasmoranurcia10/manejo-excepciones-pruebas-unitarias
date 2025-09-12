package com.nttdata.microservicios.model.dto.detallepedido;

import java.math.BigDecimal;

public record DetallePedidoDto(
        Integer orderDetailId,
        Integer orderId,
        Integer productId,
        Integer purchaseQuantity,
        BigDecimal unitPrice
) {
}

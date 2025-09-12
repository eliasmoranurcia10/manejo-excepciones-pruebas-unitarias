package com.nttdata.microservicios.model.dto.detallepedido;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.product.ProductDto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DetallePedidoResponseDto(
        @NotNull(message = "Ingresar el pedido")
        PedidoDto pedidoDto,
        @NotNull(message = "Ingresar el producto")
        ProductDto productDto,
        @NotNull(message = "Ingresar cantidad de compra")
        @Positive(message = "La cantidad de compra debe ser un número positivo")
        Integer purchaseQuantity,
        @NotNull(message = "El valor del precio no debe ser vacío")
        @Digits(integer = 100, fraction = 2, message = "El precio unitario debe tener al menos 2 decimales permitidos")
        @Positive(message = "El precio unitario debe ser un número positivo")
        BigDecimal unitPrice
) {
}

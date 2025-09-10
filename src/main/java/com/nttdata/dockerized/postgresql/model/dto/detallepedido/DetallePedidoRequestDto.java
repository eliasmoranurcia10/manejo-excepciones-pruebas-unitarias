package com.nttdata.dockerized.postgresql.model.dto.detallepedido;

import com.nttdata.dockerized.postgresql.model.dto.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.product.ProductDto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record DetallePedidoRequestDto(
        @NotNull(message = "Ingresar número de orden")
        Integer orderId,
        @NotNull(message = "Ingresar número de producto")
        Integer idProduct,
        @NotNull(message = "Ingresar cantidad de compra")
        @Positive(message = "La cantidad de compra debe ser un número positivo")
        Integer purchaseQuantity,
        @NotNull(message = "El valor del precio no debe ser vacío")
        @Digits(integer = 100, fraction = 2, message = "El precio unitario debe tener al menos 2 decimales permitidos")
        @Positive(message = "El precio unitario debe ser un número positivo")
        BigDecimal unitPrice
) {
}

package com.nttdata.microservicios.model.dto.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record PedidoRequestDto(
        @PastOrPresent(message = "La fecha de pedido debe ser pasado o actual")
        LocalDate orderDate,
        @NotBlank(message = "Ingresar estado del pedido")
        String status,
        @NotNull(message = "Colocar el codigo de usuario que realiza el pedido")
        Long userId
) {
}

package com.nttdata.microservicios.model.dto.pedido;

import com.nttdata.microservicios.model.dto.user.UserDto;

import java.time.LocalDate;

public record PedidoDto(
        Integer orderId,
        LocalDate orderDate,
        String status,
        UserDto user
) {
}

package com.nttdata.dockerized.postgresql.model.dto.pedido;

import com.nttdata.dockerized.postgresql.model.dto.user.UserDto;

import java.time.LocalDate;

public record PedidoDto(
        Integer orderId,
        LocalDate orderDate,
        String status,
        UserDto user
) {
}

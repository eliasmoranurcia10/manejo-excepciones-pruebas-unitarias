package com.nttdata.dockerized.postgresql.model.dto.pedido;

import com.nttdata.dockerized.postgresql.model.dto.user.UserDto;

import java.time.LocalDate;

public record PedidoRequestDto(
        LocalDate orderDate,
        String status,
        Long userId
) {
}

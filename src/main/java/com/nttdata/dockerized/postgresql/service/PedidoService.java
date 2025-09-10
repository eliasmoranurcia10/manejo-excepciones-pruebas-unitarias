package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.pedido.PedidoRequestDto;

import java.util.List;


public interface PedidoService {
    List<PedidoDto> listAll();
    PedidoDto findById(Integer id);
    PedidoDto save(PedidoRequestDto pedidoRequestDto);
    PedidoDto update(Integer id, PedidoRequestDto pedidoRequestDto);
    void delete(Integer id);
}

package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.detallepedido.DetallePedidoRequestDto;

import java.util.List;


public interface DetallePedidoService {

    List<DetallePedidoDto> listAll();
    DetallePedidoDto findById(Integer id);
    DetallePedidoDto save(DetallePedidoRequestDto detallePedidoRequestDto);
    DetallePedidoDto update(Integer id, DetallePedidoRequestDto detallePedidoRequestDto);
    void delete(Integer id);

}

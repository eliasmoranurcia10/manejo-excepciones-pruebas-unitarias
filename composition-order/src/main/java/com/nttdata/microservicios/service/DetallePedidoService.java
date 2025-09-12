package com.nttdata.microservicios.service;

import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoResponseDto;

import java.util.List;


public interface DetallePedidoService {

    List<DetallePedidoResponseDto> listAll();
    DetallePedidoDto findById(Integer id);
    DetallePedidoDto save(DetallePedidoRequestDto detallePedidoRequestDto);
    DetallePedidoDto update(Integer id, DetallePedidoRequestDto detallePedidoRequestDto);
    void delete(Integer id);

}

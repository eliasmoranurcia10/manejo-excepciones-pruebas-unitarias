package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.exception.BadRequestException;
import com.nttdata.microservicios.exception.InternalServerErrorException;
import com.nttdata.microservicios.exception.ResourceNotFoundException;
import com.nttdata.microservicios.mapper.OrderDetailsMapper;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.microservicios.model.entity.DetallePedido;
import com.nttdata.microservicios.repository.DetallePedidoRepository;
import com.nttdata.microservicios.service.DetallePedidoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final OrderDetailsMapper orderDetailsMapper;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository, OrderDetailsMapper orderDetailsMapper) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.orderDetailsMapper = orderDetailsMapper;
    }

    @Override
    public List<DetallePedidoDto> listAll() {
        List<DetallePedido> detallesPedido = detallePedidoRepository.findAll();
        return orderDetailsMapper.toDetallesPedidoDto(detallesPedido);
    }

    @Override
    public DetallePedidoDto findById(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        DetallePedido detallePedido = detallePedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el detalle del pedido con el id: "+id)
        );
        return orderDetailsMapper.toDetallePedidoDto(detallePedido);
    }

    @Override
    public DetallePedidoDto save(DetallePedidoRequestDto detallePedidoRequestDto) {
        try{
            DetallePedido detallePedido = orderDetailsMapper.toDetallePedidoRequest(detallePedidoRequestDto);
            return orderDetailsMapper.toDetallePedidoDto(detallePedidoRepository.save(detallePedido));

        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al guardar el detalle");
        }
    }

    @Override
    public DetallePedidoDto update(Integer id, DetallePedidoRequestDto detallePedidoRequestDto) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");

        return detallePedidoRepository.findById(id)
                .map(detallePedido -> {
                    orderDetailsMapper.updateDetallePedidoFromDto(detallePedidoRequestDto, detallePedido);
                    return orderDetailsMapper.toDetallePedidoDto(detallePedidoRepository.save(detallePedido));
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró el detalle con id:" + id)
                );
    }

    @Override
    public void delete(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        DetallePedido detallePedido = detallePedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el detalle del pedido con el id: "+id)
        );
        try {
            detallePedidoRepository.delete(detallePedido);
        } catch (DataIntegrityViolationException ex){
            throw new BadRequestException("No se puede eliminar el detalle del pedido porque tienes registros asociados");
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al eliminar el detalle del pedido");
        }
    }
}

package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.exception.BadRequestException;
import com.nttdata.microservicios.exception.InternalServerErrorException;
import com.nttdata.microservicios.exception.ResourceNotFoundException;
import com.nttdata.microservicios.mapper.OrderMapper;
import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import com.nttdata.microservicios.model.entity.Pedido;
import com.nttdata.microservicios.model.entity.User;
import com.nttdata.microservicios.repository.PedidoRepository;
import com.nttdata.microservicios.repository.UserRepository;
import com.nttdata.microservicios.service.PedidoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, OrderMapper orderMapper, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<PedidoDto> listAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return orderMapper.toPedidosDto(pedidos);
    }

    @Override
    public PedidoDto findById(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el pedido con el id: "+id)
        );
        return orderMapper.toPedidoDto(pedido);
    }

    @Override
    public PedidoDto save(PedidoRequestDto pedidoRequestDto) {
        try{
            Pedido pedido = orderMapper.toPedidoRequest(pedidoRequestDto);
            User usuario = userRepository.findById(pedidoRequestDto.userId()).orElseThrow(
                    () -> new ResourceNotFoundException("No se encontró al usuario con el id: " + pedidoRequestDto.userId() )
            );
            pedido.setUsuario(usuario);
            return orderMapper.toPedidoDto( pedidoRepository.save(pedido) );
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al guardar un pedido");
        }
    }

    @Override
    public PedidoDto update(Integer id, PedidoRequestDto pedidoRequestDto) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");

        return pedidoRepository.findById(id)
                .map(pedido -> {
                    User usuario = userRepository.findById(pedidoRequestDto.userId()).orElseThrow(
                            () -> new ResourceNotFoundException("No se encontró al usuario con el id: " + pedidoRequestDto.userId() )
                    );
                    orderMapper.updatePedidoFromDto(pedidoRequestDto, pedido);
                    pedido.setUsuario(usuario);
                    return orderMapper.toPedidoDto(pedidoRepository.save(pedido)) ;
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró el pedido con id:" + id)
                );
    }

    @Override
    public void delete(Integer id) {
        if(id==null) throw new BadRequestException("El id no puede ser nulo");
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el pedido con el id: "+id)
        );
        try {
            pedidoRepository.delete(pedido);
        } catch (DataIntegrityViolationException ex){
            throw new BadRequestException("No se puede eliminar el pedido porque tienes registros asociados");
        } catch (Exception ex) {
            throw new InternalServerErrorException("Error inesperado al eliminar el pedido");
        }
    }
}

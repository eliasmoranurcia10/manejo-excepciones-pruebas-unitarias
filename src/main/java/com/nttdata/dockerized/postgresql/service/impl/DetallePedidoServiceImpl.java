package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.InternalServerErrorException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.mapper.OrderDetailsMapper;
import com.nttdata.dockerized.postgresql.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.DetallePedidoRepository;
import com.nttdata.dockerized.postgresql.repository.PedidoRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import com.nttdata.dockerized.postgresql.service.DetallePedidoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository, OrderDetailsMapper orderDetailsMapper, PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.orderDetailsMapper = orderDetailsMapper;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
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
            Pedido pedido = pedidoRepository.findById(detallePedidoRequestDto.orderId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "No existe pedido con el id: " + detallePedidoRequestDto.orderId()
                    )
            );
            Producto producto = productoRepository.findById(detallePedidoRequestDto.idProduct()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "No existe el producto con el id: " + detallePedidoRequestDto.idProduct()
                    )
            );
            detallePedido.setPedido(pedido);
            detallePedido.setProducto(producto);
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
                    Pedido pedido = pedidoRepository.findById(detallePedidoRequestDto.orderId()).orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "No existe pedido con el id: " + detallePedidoRequestDto.orderId()
                            )
                    );
                    Producto producto = productoRepository.findById(detallePedidoRequestDto.idProduct()).orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "No existe el producto con el id: " + detallePedidoRequestDto.idProduct()
                            )
                    );
                    orderDetailsMapper.updateDetallePedidoFromDto(detallePedidoRequestDto, detallePedido);
                    detallePedido.setPedido(pedido);
                    detallePedido.setProducto(producto);
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

package com.nttdata.microservicios.service.impl;

import com.nttdata.microservicios.exception.BadRequestException;
import com.nttdata.microservicios.exception.InternalServerErrorException;
import com.nttdata.microservicios.exception.ResourceNotFoundException;
import com.nttdata.microservicios.mapper.OrderDetailsMapper;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoResponseDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.product.ProductDto;
import com.nttdata.microservicios.model.entity.DetallePedido;
import com.nttdata.microservicios.repository.DetallePedidoRepository;
import com.nttdata.microservicios.service.CompositionOrderService;
import com.nttdata.microservicios.service.CompositionProductService;
import com.nttdata.microservicios.service.DetallePedidoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final CompositionOrderService compositionOrderService;
    private final CompositionProductService compositionProductService;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository, OrderDetailsMapper orderDetailsMapper, CompositionOrderService compositionOrderService, CompositionProductService compositionProductService) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.orderDetailsMapper = orderDetailsMapper;
        this.compositionOrderService = compositionOrderService;
        this.compositionProductService = compositionProductService;
    }

    @Override
    public List<DetallePedidoResponseDto> listAll() {
        List<DetallePedido> detallesPedido = detallePedidoRepository.findAll();
        //return orderDetailsMapper.toDetallesPedidoDto(detallesPedido);
        return detallesPedido.stream()
            .map(detallePedido -> {
                //DetallePedidoResponseDto detailDto = orderDetailsMapper.toDetallePedidoResponseDto(detallePedido);
                PedidoDto pedidoDto = compositionOrderService.obtenerPedido(detallePedido.getPedidoId());
                ProductDto productDto = compositionProductService.getProduct(detallePedido.getProductoId());

                return new DetallePedidoResponseDto(
                        pedidoDto,
                        productDto,
                        detallePedido.getCantidadCompra(),
                        detallePedido.getPrecioUnitario()
                );

        }).collect(Collectors.toList());
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

package com.nttdata.microservicios.mapper;

import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.microservicios.model.entity.DetallePedido;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    @Mapping(target = "orderDetailId", source = "idDetallePedido")
    @Mapping(target = "orderId", source = "pedidoId")
    @Mapping(target = "productId", source = "productoId")
    @Mapping(target = "purchaseQuantity", source = "cantidadCompra")
    @Mapping(target = "unitPrice", source = "precioUnitario")
    DetallePedidoDto toDetallePedidoDto(DetallePedido detallePedido);
    List<DetallePedidoDto> toDetallesPedidoDto(List<DetallePedido> detallesPedido);

    @InheritInverseConfiguration
    DetallePedido toDetallePedido(DetallePedidoDto detallePedidoDto);

    @Mapping(target = "idDetallePedido", ignore = true)
    @Mapping(target = "pedidoId", source = "orderId")
    @Mapping(target = "productoId", source = "productId")
    @Mapping(target = "cantidadCompra", source = "purchaseQuantity")
    @Mapping(target = "precioUnitario", source = "unitPrice")
    DetallePedido toDetallePedidoRequest(DetallePedidoRequestDto detallePedidoRequestDto);

    @InheritConfiguration(name = "toDetallePedidoRequest")
    void updateDetallePedidoFromDto(DetallePedidoRequestDto detallePedidoRequestDto,@MappingTarget DetallePedido detallePedido);
}

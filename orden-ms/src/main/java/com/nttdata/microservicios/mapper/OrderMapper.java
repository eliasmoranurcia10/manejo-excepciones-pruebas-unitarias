package com.nttdata.microservicios.mapper;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import com.nttdata.microservicios.model.entity.Pedido;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OrderMapper {

    @Mapping(target = "orderId", source = "idPedido")
    @Mapping(target = "orderDate", source = "fechaPedido")
    @Mapping(target = "status", source = "estado")
    @Mapping(target = "user", source = "usuario")
    PedidoDto toPedidoDto(Pedido pedido);
    List<PedidoDto> toPedidosDto(List<Pedido> pedidos);

    @InheritInverseConfiguration
    Pedido toPedido(PedidoDto pedidoDto);


    @Mapping(target = "idPedido", ignore = true)
    @Mapping(target = "fechaPedido", source = "orderDate")
    @Mapping(target = "estado", source = "status")
    @Mapping(target = "usuario", ignore = true)
    //@Mapping(target = "usuario.idUser", source = "userId")
    Pedido toPedidoRequest(PedidoRequestDto pedidoRequestDto);

    @InheritConfiguration(name = "toPedidoRequest")
    void updatePedidoFromDto(PedidoRequestDto pedidoRequestDto, @MappingTarget Pedido pedido);
}

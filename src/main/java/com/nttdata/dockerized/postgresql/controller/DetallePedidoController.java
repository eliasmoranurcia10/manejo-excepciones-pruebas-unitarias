package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.OrderDetailsMapper;
import com.nttdata.dockerized.postgresql.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.service.DetallePedidoService;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/details")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;
    private final OrderDetailsMapper orderDetailsMapper;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService, OrderDetailsMapper orderDetailsMapper, PedidoService pedidoService, ProductoService productoService) {
        this.detallePedidoService = detallePedidoService;
        this.orderDetailsMapper = orderDetailsMapper;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidoDto>> getAllDetails() {
        return ResponseEntity.ok( orderDetailsMapper.toDetallesPedidoDto( detallePedidoService.listAll() ) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoDto> getDetailById(@PathVariable Integer id) {
        return Optional.of( detallePedidoService.findById(id) )
                .map( detail -> ResponseEntity.ok( orderDetailsMapper.toDetallePedidoDto(detail) ) )
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetallePedidoDto> saveDetail(
            @RequestBody @Valid DetallePedidoRequestDto detallePedidoRequestDto
    ) {
        Pedido pedido = pedidoService.findById(detallePedidoRequestDto.orderId());
        Producto producto = productoService.findById(detallePedidoRequestDto.idProduct());
        DetallePedido detallePedido = orderDetailsMapper.toDetallePedidoRequest(detallePedidoRequestDto);
        if(pedido==null || producto==null || detallePedido==null) return ResponseEntity.notFound().build();
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        return new ResponseEntity<>(
                orderDetailsMapper.toDetallePedidoDto( detallePedidoService.save(detallePedido) ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoDto> updateDetail(
            @PathVariable Integer id,
            @RequestBody @Valid DetallePedidoRequestDto detallePedidoRequestDto
    ) {

        Pedido pedido = pedidoService.findById(detallePedidoRequestDto.orderId());
        Producto producto = productoService.findById(detallePedidoRequestDto.idProduct()) ;
        DetallePedido detallePedido = detallePedidoService.findById(id);
        if(pedido==null || producto==null || detallePedido==null) return ResponseEntity.notFound().build();

        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        orderDetailsMapper.updateDetallePedidoFromDto(detallePedidoRequestDto, detallePedido);
        return ResponseEntity.ok( orderDetailsMapper.toDetallePedidoDto( detallePedidoService.save(detallePedido) ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetail(@PathVariable Integer id) {
        if( detallePedidoService.findById(id) == null ) return ResponseEntity.notFound().build();
        detallePedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.OrderMapper;
import com.nttdata.dockerized.postgresql.model.dto.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.dto.pedido.PedidoRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import com.nttdata.dockerized.postgresql.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orders")
public class PedidoController {
    private final PedidoService pedidoService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public PedidoController(PedidoService pedidoService, UserService userService, OrderMapper orderMapper) {
        this.pedidoService = pedidoService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> getAllPedidos() {
        return ResponseEntity.ok( orderMapper.toPedidosDto( pedidoService.listAll() ) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getPedidoById(@PathVariable Integer id) {
        return Optional.ofNullable( pedidoService.findById(id) )
                .map( pedido -> ResponseEntity.ok( orderMapper.toPedidoDto( pedido )) )
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoDto> savePedido(@RequestBody PedidoRequestDto pedidoRequestDto) {
        User user = userService.findById(pedidoRequestDto.userId());
        Pedido pedido = orderMapper.toPedidoRequest(pedidoRequestDto);
        if(user == null || pedido == null ) return ResponseEntity.notFound().build();
        pedido.setUsuario(user);
        return new ResponseEntity<>(
                orderMapper.toPedidoDto( pedidoService.save(pedido) ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> updatePedido(
            @PathVariable Integer id,
            @RequestBody PedidoRequestDto pedidoRequestDto
    ){
        User user = userService.findById(pedidoRequestDto.userId());
        Pedido pedido = pedidoService.findById(id);
        if(pedido == null || user == null) return ResponseEntity.notFound().build();

        pedido.setUsuario(user);
        orderMapper.updatePedidoFromDto(pedidoRequestDto, pedido);
        return ResponseEntity.ok(orderMapper.toPedidoDto( pedidoService.save(pedido) ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Integer id) {
        if(pedidoService.findById(id) == null ) return ResponseEntity.notFound().build();
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

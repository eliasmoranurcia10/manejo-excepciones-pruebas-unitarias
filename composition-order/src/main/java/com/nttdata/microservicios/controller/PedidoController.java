package com.nttdata.microservicios.controller;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import com.nttdata.microservicios.service.CompositionOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class PedidoController {
    private final CompositionOrderService compositionOrderService;

    public PedidoController(CompositionOrderService compositionOrderService) {
        this.compositionOrderService = compositionOrderService;
    }

    @GetMapping
    public List<PedidoDto> getAllOrder() {
        return compositionOrderService.listPedidos();
    }

    @GetMapping("/{id}")
    public PedidoDto getOrder(@PathVariable Integer id) {
        return compositionOrderService.obtenerPedido(id);
    }

    @PostMapping
    public PedidoDto saveOrder(@RequestBody PedidoRequestDto pedidoRequestDto) {
        return compositionOrderService.savePedido(pedidoRequestDto);
    }

    @PutMapping("/{id}")
    public PedidoDto updateOrder(@PathVariable Integer id, @RequestBody PedidoRequestDto pedidoRequestDto) {
        return compositionOrderService.updatePedido(id, pedidoRequestDto);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        compositionOrderService.deletePedido(id);
    }
}

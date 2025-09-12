package com.nttdata.microservicios.controller;

import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import com.nttdata.microservicios.service.CompositionOrderService;
import com.nttdata.microservicios.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/details")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;
    private final CompositionOrderService compositionOrderService;

    public DetallePedidoController(DetallePedidoService detallePedidoService, CompositionOrderService compositionOrderService) {
        this.detallePedidoService = detallePedidoService;
        this.compositionOrderService = compositionOrderService;
    }

    @GetMapping("/order/{id}")
    public PedidoDto getOrder(@PathVariable Integer id) {
        return compositionOrderService.obtenerPedido(id);
    }

    @PostMapping("/order")
    public PedidoDto saveOrder(@RequestBody PedidoRequestDto pedidoRequestDto) {
        return compositionOrderService.savePedido(pedidoRequestDto);
    }

    @GetMapping
    public List<DetallePedidoDto> getAllDetails() {
        return detallePedidoService.listAll();
    }

    @GetMapping("/{id}")
    public DetallePedidoDto getDetailById(@PathVariable Integer id) {
        return detallePedidoService.findById(id);
    }

    @PostMapping
    public DetallePedidoDto saveDetail(
            @RequestBody @Valid DetallePedidoRequestDto detallePedidoRequestDto
    ) {
        return detallePedidoService.save(detallePedidoRequestDto);
    }

    @PutMapping("/{id}")
    public DetallePedidoDto updateDetail(
            @PathVariable Integer id,
            @RequestBody @Valid DetallePedidoRequestDto detallePedidoRequestDto
    ) {
        return detallePedidoService.update(id, detallePedidoRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDetail(@PathVariable Integer id) {
        detallePedidoService.delete(id);
    }
}

package com.nttdata.microservicios.controller;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import com.nttdata.microservicios.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDto> getAllPedidos() {
        return pedidoService.listAll();
    }

    @GetMapping("/{id}")
    public PedidoDto getPedidoById(@PathVariable Integer id) {
        return pedidoService.findById(id);
    }

    @PostMapping
    public PedidoDto savePedido(@RequestBody @Valid PedidoRequestDto pedidoRequestDto) {
        return pedidoService.save(pedidoRequestDto);
    }

    @PutMapping("/{id}")
    public PedidoDto updatePedido(
            @PathVariable Integer id,
            @RequestBody @Valid PedidoRequestDto pedidoRequestDto
    ){
        return pedidoService.update(id, pedidoRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Integer id) {
        pedidoService.delete(id);
    }
}

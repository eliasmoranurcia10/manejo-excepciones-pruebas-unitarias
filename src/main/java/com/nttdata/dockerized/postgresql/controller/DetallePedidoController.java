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

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
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

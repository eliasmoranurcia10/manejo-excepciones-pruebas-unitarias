package com.nttdata.microservicios.controller;

import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoDto;
import com.nttdata.microservicios.model.dto.detallepedido.DetallePedidoRequestDto;
import com.nttdata.microservicios.service.DetallePedidoService;
import jakarta.validation.Valid;
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

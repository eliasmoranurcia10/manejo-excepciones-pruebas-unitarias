package com.nttdata.microservicios.feign;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "orden-ms", url = "${orden.service.url}")
public interface OrderFeignClient {

    @GetMapping
    List<PedidoDto> getPedidos();

    @GetMapping("/{id}")
    PedidoDto getPedidoById(@PathVariable("id") Integer id);

    @PostMapping
    PedidoDto createPedido(@RequestBody PedidoRequestDto request);

    @PutMapping("/{id}")
    PedidoDto updatePedido(@PathVariable("id") Integer id, @RequestBody PedidoRequestDto requestDto);

    @DeleteMapping("/{id}")
    void deletePedido(@PathVariable("id") Integer id);
}

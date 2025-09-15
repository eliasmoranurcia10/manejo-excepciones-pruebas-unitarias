package com.nttdata.microservicios.feign;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "orden-ms")
public interface OrderFeignClient {

    @GetMapping("/api/orders")
    List<PedidoDto> getPedidos();

    @GetMapping("/api/orders/{id}")
    PedidoDto getPedidoById(@PathVariable("id") Integer id);

    @PostMapping("/api/orders")
    PedidoDto createPedido(@RequestBody PedidoRequestDto request);

    @PutMapping("/api/orders/{id}")
    PedidoDto updatePedido(@PathVariable("id") Integer id, @RequestBody PedidoRequestDto requestDto);

    @DeleteMapping("/api/orders/{id}")
    void deletePedido(@PathVariable("id") Integer id);
}

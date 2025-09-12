package com.nttdata.microservicios.feign;

import com.nttdata.microservicios.model.dto.pedido.PedidoDto;
import com.nttdata.microservicios.model.dto.pedido.PedidoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orden-ms", url = "http://localhost:8095/api/orders")
public interface OrderFeignClient {
    @GetMapping("/{id}")
    PedidoDto getPedidoById(@PathVariable("id") Integer id);
}

package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import com.nttdata.dockerized.postgresql.repository.DetallePedidoRepository;
import com.nttdata.dockerized.postgresql.service.DetallePedidoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public List<DetallePedido> listAll() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido findById(Integer idDetallePedido) {
        return detallePedidoRepository.findById(idDetallePedido).orElseThrow(
                () -> new RuntimeException("Detalle del pedido no existe")
        );
    }

    @Override
    public DetallePedido save(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public void delete(Integer idDetallePedido) {
        detallePedidoRepository.deleteById(idDetallePedido);
    }
}

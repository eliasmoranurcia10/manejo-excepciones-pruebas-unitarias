package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.repository.PedidoRepository;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Integer idPedido) {
        return pedidoRepository.findById(idPedido).orElseThrow(
                () -> new RuntimeException("Pedido no existe")
        );
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void delete(Integer idPedido) {
        pedidoRepository.deleteById(idPedido);
    }
}

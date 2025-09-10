package com.nttdata.dockerized.postgresql.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;

    @Column
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User usuario;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detallesPedido;
}

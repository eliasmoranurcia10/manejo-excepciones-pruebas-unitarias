package com.nttdata.microservicios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    private Integer idDetallePedido;

    @Column(name = "id_pedido")
    private Integer pedidoId;

    @Column(name = "id_producto")
    private Integer productoId;

    @Column(name = "cantidad_compra")
    private Integer cantidadCompra;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
}

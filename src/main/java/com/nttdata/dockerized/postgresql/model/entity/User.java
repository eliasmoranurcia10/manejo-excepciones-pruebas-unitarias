package com.nttdata.dockerized.postgresql.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long idUser;

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column
    private Boolean active;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

}

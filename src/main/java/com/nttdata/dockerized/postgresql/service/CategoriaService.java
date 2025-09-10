package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listAll();

    Categoria findById(Integer idCategoria);

    Categoria save(Categoria categoria);

    void delete(Integer idCategoria);
}
package com.nttdata.microservicios.repository;

import com.nttdata.microservicios.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}

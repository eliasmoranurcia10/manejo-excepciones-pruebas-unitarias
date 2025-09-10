package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    public List<Categoria> listAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria).orElseThrow(
                () -> new RuntimeException("Categoría no encontrada")
        );
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void delete(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}

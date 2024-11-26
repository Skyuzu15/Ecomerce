package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.Categoria;
import com.meuprojeto.ecomerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
        if (categoriaRepository.existsById(id)) {
            categoriaAtualizada.setId(id);
            return categoriaRepository.save(categoriaAtualizada);
        } else {
            throw new RuntimeException("Categoria não encontrada");
        }
    }
}

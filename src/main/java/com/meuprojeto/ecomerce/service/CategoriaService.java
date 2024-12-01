package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.Categoria;
import com.meuprojeto.ecomerce.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(categoriaAtualizada.getNome());
            categoria.setDescricao(categoriaAtualizada.getDescricao());
            categoria.setImagem_url(categoriaAtualizada.getImagem_url());
            categoria.setSlug(categoriaAtualizada.getSlug());
            categoria.setStatus(categoriaAtualizada.getStatus());
            categoria.setDataAtualizacao(LocalDateTime.now());
            categoria.setOrdem(categoriaAtualizada.getOrdem());
            categoria.setParent(categoriaAtualizada.getParent());
            return categoriaRepository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}

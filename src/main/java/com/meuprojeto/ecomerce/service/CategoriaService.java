package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.exception.ResourceNotFoundException;
import com.meuprojeto.ecomerce.model.Categoria;
import com.meuprojeto.ecomerce.model.Status;
import com.meuprojeto.ecomerce.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        System.out.println("Chamando listarCategorias no CategoriaService");

        List<Categoria> categorias = categoriaRepository.findAll();

        System.out.println("Categorias retornadas: " + categorias);

        return categorias;
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria criarCategoria(Categoria categoria) {

        if (categoria.getSlug() == null || categoria.getSlug().isEmpty()) {
            categoria.setSlug(gerarSlug(categoria.getNome())); // Exemplo de lógica adicional
            }
            return categoriaRepository.save(categoria);
        }

        private String gerarSlug(String nome) {
            return nome.toLowerCase().replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
        }

    public Categoria mapearCategoria(Map<String, Object> categoriaRequest) {
        Categoria categoria = new Categoria();

        // Mapeamento manual dos campos
        categoria.setNome((String) categoriaRequest.get("nome"));
        categoria.setDescricao((String) categoriaRequest.get("descricao"));
        categoria.setSlug((String) categoriaRequest.get("slug"));
        categoria.setImagem_url((String) categoriaRequest.get("imagemUrl"));
        categoria.setOrdem((Integer) categoriaRequest.get("ordem"));

        // Status - converte string para enum
        if (categoriaRequest.containsKey("status")) {
            try {
                categoria.setStatus(Status.valueOf((String) categoriaRequest.get("status")));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Status inválido: " + categoriaRequest.get("status"));
            }
        }

        // Parent (se existir)
        if (categoriaRequest.containsKey("parent")) {
            Long parentId = ((Number) categoriaRequest.get("parent")).longValue();
            Categoria parent = categoriaRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Categoria pai não encontrada com ID: " + parentId));
            categoria.setParent(parent);
        }

        return categoria;
    }

    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(categoriaAtualizada.getNome());
            categoria.setDescricao(categoriaAtualizada.getDescricao());
            categoria.setImagem_url(categoriaAtualizada.getImagem_url());
            categoria.setSlug(categoriaAtualizada.getSlug());
            categoria.setStatus(categoriaAtualizada.getStatus());  // Atualizando o status
            return categoriaRepository.save(categoria);  // Salvando a categoria atualizada no banco
        }).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));  // Lançando exceção caso não encontre a categoria
    }

    public void deletar(Long id) {
        // Verifica se a categoria existe
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria com ID " + id + " não encontrada.");
        }
        categoriaRepository.deleteById(id);
    }
}

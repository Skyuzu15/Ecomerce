package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.exception.ProdutoNotFoundException;
import com.meuprojeto.ecomerce.exception.ResourceNotFoundException;
import com.meuprojeto.ecomerce.model.Categoria;
import com.meuprojeto.ecomerce.model.Produto;
import com.meuprojeto.ecomerce.repository.CategoriaRepository;
import com.meuprojeto.ecomerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        try {
            Produto produtoExistente = produtoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

            if (produtoAtualizado.getCategoria() != null) {
                Categoria categoriaExistente = categoriaRepository.findById(produtoAtualizado.getCategoria().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
                produtoExistente.setCategoria(categoriaExistente);
            }

            return produtoRepository.save(produtoExistente);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar produto", e);
        }
    }

    public void excluirProduto(Long id) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
        produtoRepository.delete(produtoExistente);
    }
}


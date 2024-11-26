package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.Product;
import com.meuprojeto.ecomerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Product> listarTodos() {
        return produtoRepository.findAll();
    }

    public Product buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Product salvar(Product product) {
        return produtoRepository.save(product);
    }

    public void deletarPorId(Long id) {
        produtoRepository.deleteById(id);
    }

    public Product atualizar(Long id, Product productAtualizado) {
        if (produtoRepository.existsById(id)) {
            productAtualizado.setId(id);
            return produtoRepository.save(productAtualizado);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }
}

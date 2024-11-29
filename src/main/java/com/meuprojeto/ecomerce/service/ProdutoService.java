package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.dto.ProductDTO;
import com.meuprojeto.ecomerce.model.Product;
import com.meuprojeto.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProductRepository repository;

    public List<Product> listarTodos() {
        return repository.findAll();
    }

    public Optional<Product> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Product salvar(Product product) {
        return repository.save(product);
    }

    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }

    public void criarProduto(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        repository.save(product);
    }

    public Product atualizarProduto(Long id, Product produtoAtualizado) {
        Product produtoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setName(produtoAtualizado.getName() != null ? produtoAtualizado.getName() : produtoExistente.getName());
        produtoExistente.setDescription(produtoAtualizado.getDescription() != null ? produtoAtualizado.getDescription() : produtoExistente.getDescription());
        produtoExistente.setPrice(produtoAtualizado.getPrice() != null ? produtoAtualizado.getPrice() : produtoExistente.getPrice());
        produtoExistente.setStockQuantity(produtoAtualizado.getStockQuantity() != null
                ? produtoAtualizado.getStockQuantity()
                : produtoExistente.getStockQuantity());

        return repository.save(produtoExistente);
    }
}

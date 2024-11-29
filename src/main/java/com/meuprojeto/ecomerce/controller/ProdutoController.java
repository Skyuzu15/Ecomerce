package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.dto.ProductDTO;
import com.meuprojeto.ecomerce.model.Product;
import com.meuprojeto.ecomerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarProduto(@Valid @RequestBody Product produto, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body("Erros de validação: " + sb.toString());
        }

        produtoService.salvar(produto);
        return ResponseEntity.ok("Produto salvo com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Product>> listarProdutos() {
        List<Product> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<String> criarProduto(@Valid @RequestBody ProductDTO productDTO) {
        produtoService.criarProduto(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Product produtoAtualizado) {
        Product produtoAtualizadoSalvo = produtoService.atualizarProduto(id, produtoAtualizado);
        return ResponseEntity.ok(produtoAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    private void configurarValoresPadrao(Product produto) {
        if (produto.getName() == null || produto.getName().isEmpty()) {
            produto.setName("Nome Padrão");
        }
        if (produto.getDescription() == null) {
            produto.setDescription("Sem descrição");
        }
        if (produto.getPrice() == null || produto.getPrice() <= 0.0) {
            produto.setPrice(10.0);
        }
        if (produto.getStockQuantity() == null || produto.getStockQuantity() < 2) {
            produto.setStockQuantity(50);
        }
    }

    @GetMapping("/erro")
    public void gerarErro() {
        throw new RuntimeException("Erro simulado para teste de exceções");
    }
}

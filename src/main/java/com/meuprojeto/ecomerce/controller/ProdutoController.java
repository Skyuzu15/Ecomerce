package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.Product;
import com.meuprojeto.ecomerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar")
    public ResponseEntity<Product> salvarProduto(@RequestBody Product produto) {
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
        Product produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @GetMapping("/listar")
    public List<Product> listarProdutos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarPorId(@PathVariable Long id) {
        Optional<Product> produto = Optional.ofNullable(produtoService.buscarPorId(id));
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public Product atualizarProduto(@PathVariable Long id, @RequestBody Product produto) {
        produto.setId(id);
        return produtoService.salvar(produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarPorId(id);
    }

    @GetMapping("/api/produtos/{idOuAcao}")
    public ResponseEntity<?> tratarRequisicao(@PathVariable String idOuAcao) {
        if ("salvar".equals(idOuAcao)) {
            // Lógica para salvar
            return ResponseEntity.ok("Produto salvo!");
        }
        try {
            Long id = Long.parseLong(idOuAcao);
            // Lógica para tratar o ID
            return ResponseEntity.ok("Produto com ID: " + id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Parâmetro inválido");
        }
    }

}

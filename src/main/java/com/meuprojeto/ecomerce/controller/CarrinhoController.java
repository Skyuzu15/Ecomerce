package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.ItemCarrinho;
import com.meuprojeto.ecomerce.model.Pedidos;
import com.meuprojeto.ecomerce.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/{idCarrinho}/adicionar")
    public ResponseEntity<String> adicionarProdutoAoCarrinho(
            @PathVariable Long idCarrinho,
            @RequestParam Long idProduto,
            @RequestParam int quantidade) {
        try {
            carrinhoService.adicionarProdutoAoCarrinho(idCarrinho, idProduto, quantidade);
            return ResponseEntity.ok("Produto adicionado ao carrinho com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/carrinho/item/{idItemCarrinho}/quantidade")
    public ResponseEntity<?> atualizarQuantidadeItem(@PathVariable Long idItemCarrinho, @RequestParam int quantidade) {
        try {
            carrinhoService.atualizarQuantidadeItem(idItemCarrinho, quantidade);
            return ResponseEntity.ok("Quantidade do item atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItemCarrinho(@PathVariable Long id) {
        if (!carrinhoService.existsItemCarrinhoById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado no carrinho.");
        }

        carrinhoService.deleteItemCarrinhoById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Item removido com sucesso.");
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<?> listarItensDoCarrinho(@PathVariable Long id) {
        List<ItemCarrinho> itens = carrinhoService.listarItensDoCarrinho(id);
        if (itens.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho vazio ou não encontrado.");
        }

        List<Map<String, Serializable>> resposta = itens.stream().map(item -> {
            BigDecimal subtotal = BigDecimal.valueOf(item.getQuantidade())
                    .multiply(BigDecimal.valueOf(item.getPrecoUnitario()));

            Map<String, Serializable> itemMap = new HashMap<>();
            itemMap.put("produto", item.getProduto().getNome());
            itemMap.put("quantidade", item.getQuantidade());
            itemMap.put("preco_unitario", item.getPrecoUnitario());
            itemMap.put("subtotal", subtotal);

            return itemMap;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/carrinho/{idCarrinho}/total")
    public Double calcularTotalCarrinho(@PathVariable Long idCarrinho) {
        BigDecimal totalCarrinho = carrinhoService.calcularTotalCarrinho(idCarrinho);
        return totalCarrinho.doubleValue();  // Converte o BigDecimal para Double
    }

    @PostMapping("/carrinho/{idCarrinho}/checkout")
    public Pedidos realizarCheckout(@PathVariable Long idCarrinho) {
        return carrinhoService.realizarCheckout(idCarrinho);
    }
}


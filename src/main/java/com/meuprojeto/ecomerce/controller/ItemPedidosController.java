package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.ItemPedidos;
import com.meuprojeto.ecomerce.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itempedidos")
public class ItemPedidosController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    // Listar todos os itens de pedido
    @GetMapping
    public ResponseEntity<List<ItemPedidos>> listarTodos() {
        List<ItemPedidos> itensPedidos = itemPedidoService.listarTodos();
        return ResponseEntity.ok(itensPedidos);
    }

    // Buscar item do pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidos> buscarPorId(@PathVariable Long id) {
        return itemPedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo item de pedido
    @PostMapping
    public ResponseEntity<ItemPedidos> criarItemPedido(@RequestBody ItemPedidos itemPedido) {
        ItemPedidos novoItemPedido = itemPedidoService.salvar(itemPedido);
        return ResponseEntity.ok(novoItemPedido);
    }

    // Atualizar item de pedido
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidos> atualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedidos itemPedidos) {
        if (!itemPedidoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemPedidos.setId(id); // Garantir que o ID seja o mesmo
        ItemPedidos itemPedidoAtualizado = itemPedidoService.salvar(itemPedidos);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }

    // Deletar item de pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemPedido(@PathVariable Long id) {
        if (!itemPedidoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemPedidoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}


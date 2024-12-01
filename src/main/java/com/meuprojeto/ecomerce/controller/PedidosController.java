package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.Pedidos;
import com.meuprojeto.ecomerce.service.PedidosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    @GetMapping
    public ResponseEntity<List<Pedidos>> listarTodos() {
        List<Pedidos> pedidos = pedidosService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> buscarPorId(@PathVariable Long id) {
        return pedidosService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedidos> criarPedido(@Valid @RequestBody Pedidos pedido) {
        Pedidos novoPedido = pedidosService.salvar(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedidos pedido) {
        if (!pedidosService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(id); // Garantir que o ID seja o mesmo
        Pedidos pedidoAtualizado = pedidosService.salvar(pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        if (!pedidosService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pedidosService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}



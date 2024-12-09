package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.Pedidos;
import com.meuprojeto.ecomerce.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    @PostMapping
    public ResponseEntity<Pedidos> criarOuAtualizarPedido(@RequestBody Pedidos pedido) {
        Pedidos pedidoSalvo = pedidosService.savePedido(pedido);
        return ResponseEntity.ok(pedidoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Pedidos>> listarTodosPedidos() {
        List<Pedidos> pedidos = pedidosService.listarTodosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> buscarPedidoPorId(@PathVariable Long id) {
        Optional<Pedidos> pedido = pedidosService.buscarPedidoPorId(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        if (pedidosService.buscarPedidoPorId(id).isPresent()) {
            pedidosService.deletarPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}




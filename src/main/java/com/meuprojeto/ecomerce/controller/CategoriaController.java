package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.Categoria;
import com.meuprojeto.ecomerce.model.Status;
import com.meuprojeto.ecomerce.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        System.out.println("Chamando o endpoint /categorias/listar no CategoriaController");
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria novaCategoria = categoriaService.criarCategoria(categoria);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", novaCategoria);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Erro ao criar categoria.");
            errorResponse.put("details", e.getMessage());
            errorResponse.put("timestamp", LocalDateTime.now());
            errorResponse.put("path", "/categorias");

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            Categoria atualizada = categoriaService.atualizarCategoria(id, categoria);
            return ResponseEntity.ok(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletar(id);


        return ResponseEntity.noContent().build();
    }

}

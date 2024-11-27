package com.meuprojeto.ecomerce.controller;

import com.meuprojeto.ecomerce.model.Categoria;
import com.meuprojeto.ecomerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/salvar")
    public Categoria salvarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.salvar(categoria);
    }

    @GetMapping("/listar")
    public List<Categoria> listarCategoria() {
        return categoriaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        return categoriaService.salvar(categoria);
    }

    @DeleteMapping
    public void  deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarPorId(id);
    }
}

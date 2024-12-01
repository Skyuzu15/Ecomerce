package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.Pedidos;
import com.meuprojeto.ecomerce.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {

    @Autowired
    private PedidosRepository pedidosRepository;

    public List<Pedidos> listarTodos() {
        return pedidosRepository.findAll();
    }

    public Optional<Pedidos> buscarPorId(Long id) {
        return pedidosRepository.findById(id);
    }

    public Pedidos salvar(Pedidos pedidos) {
        return pedidosRepository.save(pedidos);
    }

    public void deletarPorId(Long id) {
        pedidosRepository.deleteById(id);
    }
}

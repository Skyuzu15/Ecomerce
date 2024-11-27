package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.Cliente;
import com.meuprojeto.ecomerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Método para listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public void deletarPorId(Long id) {
        clienteRepository.deleteById(id);
    }
}

package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.Cliente;
import com.meuprojeto.ecomerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        cliente.setDataCadastro(LocalDateTime.now()); // Define a data de cadastro como o momento atual
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            cliente.setCpf(clienteAtualizado.getCpf());
            return clienteRepository.save(cliente);
        }
        throw new RuntimeException("Cliente não encontrado");
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

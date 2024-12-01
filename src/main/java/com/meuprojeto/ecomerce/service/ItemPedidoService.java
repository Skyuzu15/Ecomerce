package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.ItemPedidos;
import com.meuprojeto.ecomerce.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    // Listar todos os itens do pedido
    public List<ItemPedidos> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    // Buscar item do pedido por ID
    public Optional<ItemPedidos> buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    // Salvar item do pedido
    public ItemPedidos salvar(ItemPedidos itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    // Deletar item do pedido por ID
    public void deletarPorId(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}


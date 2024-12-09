package com.meuprojeto.ecomerce.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meuprojeto.ecomerce.model.Pedidos;
import com.meuprojeto.ecomerce.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidosService {

    @Autowired
    private PedidosRepository pedidosRepository;

    public Pedidos savePedido(Pedidos pedido) {
        double valorTotal = calcularValorTotal(pedido.getProdutos());
        pedido.setTotal(valorTotal);
        return pedidosRepository.save(pedido);
    }

    // Método auxiliar para calcular o total com base nos produtos
    private double calcularValorTotal(String produtosJson) {
        // Supondo que produtosJson seja um JSON no formato [{"id":1,"quantidade":2}]
        double total = 0.0;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> produtos = objectMapper.readValue(produtosJson, new TypeReference<>() {});
            for (Map<String, Object> produto : produtos) {
                Long produtoId = Long.parseLong(produto.get("id").toString());
                int quantidade = Integer.parseInt(produto.get("quantidade").toString());

                // Aqui, você precisaria buscar o preço do produto do banco de dados (simulado)
                double precoProduto = buscarPrecoProduto(produtoId);
                total += precoProduto * quantidade;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular o total: " + e.getMessage());
        }

        return total;
    }

    // Simula buscar o preço do produto no banco
    private double buscarPrecoProduto(Long produtoId) {
        // Aqui você precisaria acessar o repositório de produtos
        // Este é apenas um exemplo com valores fictícios
        return switch (produtoId.intValue()) {
            case 1 -> 1000.0;
            case 2 -> 1500.0;
            default -> 0.0;
        };
    }


    public List<Pedidos> listarTodosPedidos() {
        return pedidosRepository.findAll();
    }

    public Optional<Pedidos> buscarPedidoPorId(Long id) {
        return pedidosRepository.findById(id);
    }

    public void deletarPedido(Long id) {
        pedidosRepository.deleteById(id);
    }
}




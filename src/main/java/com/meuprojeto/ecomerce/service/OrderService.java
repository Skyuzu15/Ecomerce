package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.Order;
import com.meuprojeto.ecomerce.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllPedidos() {
        return orderRepository.findAll();
    }

    public Optional<Order> getPedidoById(Long id) {
        return orderRepository.findById(id);
    }

    public Order savePedido(Order order) {
        return orderRepository.save(order);
    }

    public void deletaPedido(Long id) {
        orderRepository.deleteById(id);
    }
}

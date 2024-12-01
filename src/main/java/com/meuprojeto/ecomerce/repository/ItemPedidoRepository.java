package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.ItemPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidos, Long> {
    // Métodos personalizados podem ser adicionados aqui
}


package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
}


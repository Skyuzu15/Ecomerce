package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

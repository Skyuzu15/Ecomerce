package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

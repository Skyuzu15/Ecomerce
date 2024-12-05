package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {}


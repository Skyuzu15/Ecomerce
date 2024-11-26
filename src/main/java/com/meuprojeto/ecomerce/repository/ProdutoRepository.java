package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Product, Long> {
}

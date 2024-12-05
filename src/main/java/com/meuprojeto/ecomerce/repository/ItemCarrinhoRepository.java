package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.Carrinho;
import com.meuprojeto.ecomerce.model.ItemCarrinho;
import com.meuprojeto.ecomerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
    Optional<ItemCarrinho> findByCarrinhoAndProduto(Carrinho carrinho, Produto produto);

    @Modifying
    @Query("UPDATE ItemCarrinho i SET i.quantidade = :quantidade WHERE i.idItemCarrinho = :idItemCarrinho")
    void atualizarQuantidade(@Param("idItemCarrinho") Long idItemCarrinho, @Param("quantidade") int quantidade);

    List<ItemCarrinho> findByCarrinhoIdCarrinho(Long idCarrinho);

}


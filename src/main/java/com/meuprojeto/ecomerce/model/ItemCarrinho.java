package com.meuprojeto.ecomerce.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemCarrinho;

    @ManyToOne
    @JoinColumn(name = "id_carrinho", referencedColumnName = "idCarrinho")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;

    private Integer quantidade;
    private Double precoUnitario;

    public Long getIdItemCarrinho() {
        return idItemCarrinho;
    }

    public void setIdItemCarrinho(Long idItemCarrinho) {
        this.idItemCarrinho = idItemCarrinho;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}


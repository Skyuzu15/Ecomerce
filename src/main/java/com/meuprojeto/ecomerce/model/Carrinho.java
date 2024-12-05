package com.meuprojeto.ecomerce.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrinho;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemCarrinho> itensCarrinho;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Aqui o relacionamento com Cliente
    private Cliente cliente;

    public Carrinho() {}

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public Cliente getCliente() {
        return cliente;  // Método para acessar o cliente
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

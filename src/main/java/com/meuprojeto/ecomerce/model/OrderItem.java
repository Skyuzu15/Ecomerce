//Contém as entidades (Models) que mapeiam as tabelas do banco
package com.meuprojeto.ecomerce.model;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id //Define a classe como uma entidade do JPA (Java Persistence API).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (Id) Indicam que o campo id é a chave primária e será gerado automaticamente.
    private Long id;

    @ManyToOne  //indica que muitos pedidos podem estar associados a um único usuário.
    private Product product;

    @ManyToOne
    private Order order;

    private int quantify;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantify() {
        return quantify;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

//A entidade OrderItem representa os itens de um pedido. Ela contém o produto, o pedido e a quantidade do produto no pedido.
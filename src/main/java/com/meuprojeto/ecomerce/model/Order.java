//Contém as entidades (Models) que mapeiam as tabelas do banco
package com.meuprojeto.ecomerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {

    @Id //Define a classe como uma entidade do JPA (Java Persistence API).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (Id) Indicam que o campo id é a chave primária e será gerado automaticamente.
    private Long id;
    private LocalDateTime orderDate;
    private double totalAmount;

    @ManyToOne // indica que muitos pedidos podem estar associados a um único usuário.
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //define que um pedido pode ter múltiplos itens de pedido.
    private List<OrderItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}

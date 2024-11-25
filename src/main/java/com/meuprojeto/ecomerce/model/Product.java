//Contém as entidades (Models) que mapeiam as tabelas do banco
package com.meuprojeto.ecomerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //Define a classe como uma entidade do JPA (Java Persistence API).
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (Id) Indicam que o campo id é a chave primária e será gerado automaticamente.
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

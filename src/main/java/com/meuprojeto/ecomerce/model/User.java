//Contém as entidades (Models) que mapeiam as tabelas do banco
package com.meuprojeto.ecomerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id //Define a classe como uma entidade do JPA (Java Persistence API).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (Id) Indicam que o campo id é a chave primária e será gerado automaticamente.
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

//Aqui, temos um exemplo básico para um usuário com os atributos name, email e password

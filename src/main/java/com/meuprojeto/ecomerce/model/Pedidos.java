package com.meuprojeto.ecomerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")  // Nome da tabela no banco de dados
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Para gerar o ID automaticamente
    @Column(name = "id")
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "pagamento")
    private String pagamento;

    @Column(name = "produtos")
    private String produtos;  // Pode ser um JSON ou um formato de lista de produtos

    @Column(name = "total")
    private double total;

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

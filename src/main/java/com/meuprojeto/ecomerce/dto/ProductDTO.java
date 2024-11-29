package com.meuprojeto.ecomerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {

    @NotNull(message = "O nome do produto não pode ser nulo.")
        private String name;

    @NotNull(message = "A descrição não pode ser nula.")
        private String description;

    @PositiveOrZero(message = "O preço deve ser igual ou maior que zero.")
        private Double price;

    @PositiveOrZero(message = "A quantidade em estoque deve ser igual ou maior que zero.")

    private Integer stockQuantity;

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

    public Double getPrice() {
            return price;
    }

    public void setPrice(Double price) {
            this.price = price;
    }

    public Integer getStockQuantity() {
            return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
    }
}

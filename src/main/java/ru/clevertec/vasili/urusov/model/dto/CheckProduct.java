package ru.clevertec.vasili.urusov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckProduct {

     private String name;
     private Double price;
     private Boolean discount;
     private Double pricePerQuantity;
     private Integer quantity;

    public CheckProduct(String name, Double price, Boolean discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

}

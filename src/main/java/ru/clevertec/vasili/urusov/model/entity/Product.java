package ru.clevertec.vasili.urusov.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Boolean discount;

    public Product(String name, Double price, Boolean discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

}

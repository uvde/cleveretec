package ru.clevertec.vasili.urusov.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOfClient {

    private Map<Integer, Integer> productForClient;
    private Integer numberDiscountCart;

}

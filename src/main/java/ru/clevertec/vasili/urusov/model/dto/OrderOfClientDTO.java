package ru.clevertec.vasili.urusov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOfClientDTO {

    private Map<Long, Integer> idAntQuantityOfProductOfClient;
    private Integer numberDiscountCart;

}

package ru.clevertec.vasili.urusov.util;

import org.apache.commons.lang3.StringUtils;
import ru.clevertec.vasili.urusov.model.dto.OrderOfClient;

import java.util.HashMap;
import java.util.Map;

public class ParserRequestClient {

        public static OrderOfClient getOrderOfClient(String[] requestClient){

        Map<Integer, Integer> requestProduct = new HashMap<>();
        Integer numberOfDiscountCart = -1;

        for (int i = 0; i < requestClient.length; i++) {
            if (requestClient[i].startsWith("card")){
                if (StringUtils.isNumeric(requestClient[i].substring(5))) {
                    numberOfDiscountCart = Integer.parseInt(requestClient[i].substring(5));
                }
            }else {
                String[] idAndCountOfProduct = requestClient[i].split("-");
                Integer idProduct = Integer.parseInt(idAndCountOfProduct[0]);
                Integer countProduct = Integer.parseInt(idAndCountOfProduct[1]);
                requestProduct.put(idProduct, countProduct);
            }
        }

        OrderOfClient orderOfClient = new OrderOfClient(requestProduct, numberOfDiscountCart);

        return orderOfClient;
    }
}

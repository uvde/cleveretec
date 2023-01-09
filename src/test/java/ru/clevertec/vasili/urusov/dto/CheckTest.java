package ru.clevertec.vasili.urusov.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.vasili.urusov.model.dto.Check;
import ru.clevertec.vasili.urusov.model.dto.CheckProduct;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckTest {

    @Test
    public void missingProductOfClient(){

        NullPointerException thrown = assertThrows(NullPointerException.class,()->{
                Check
                    .builder()
                    .setCardDiscount(6.7)
                    .setDiscountOfProductWithSale(1.3)
                    .setTotalPrice(156.9)
                        .build();
        });

        assertEquals("CheckBuilder dont has productOfClient", thrown.getMessage());

    }

    @Test
    public void missingTotalPrise(){

        List<CheckProduct> productList = new ArrayList<>();

        NullPointerException thrown = assertThrows(NullPointerException.class, ()->{
                Check
                    .builder()
                    .setProductsOfClient(productList)
                    .setDiscountOfProductWithSale(8.7)
                    .setCardDiscount(3.5)
                    .build();

        });

        assertEquals("CheckBuilder dont has totalPrice", thrown.getMessage());
    }

    @Test
    public void missingCartDiscount(){

        List<CheckProduct> productList = new ArrayList<>();

        Check check = Check
                .builder()
                .setProductsOfClient(productList)
                .setDiscountOfProductWithSale(2.1)
                .setCardDiscount(null)
                .setTotalPrice(23.4)
                .build();

        //check cardDiscount
        assertEquals(0., check.getCardDiscount());
    }

    @Test
    public void missingDiscountOfProductWithSale(){
        List<CheckProduct> productList = new ArrayList<>();

        Check check = Check
                .builder()
                .setProductsOfClient(productList)
                .setDiscountOfProductWithSale(null)
                .setCardDiscount(2.3)
                .setTotalPrice(23.4)
                .build();

        //check discountOfProductWithSale
        assertEquals(0., check.getDiscountOfProductWithSale());
    }

    @Test
    public void testCorrectCase(){
        List<CheckProduct> productList = new ArrayList<>();

        productList.add(new CheckProduct("appal", 3.7, true, 18.5, 5));
        productList.add(new CheckProduct("cherry", 7.3, false, 14.6, 2));
        productList.add(new CheckProduct("blueberry", 8.5, true, 25.5, 3));

        Check check = Check
                .builder()
                .setProductsOfClient(productList)
                .setDiscountOfProductWithSale(2.5)
                .setCardDiscount(3.3)
                .setTotalPrice(23.4)
                .build();
        //check size of productOfClient;
        assertEquals(3, check.getProductsOfClient().size());

        //check element of productOfClient
        for (CheckProduct checkProductActual : check.getProductsOfClient()){
            assertTrue(productList.contains(checkProductActual));
        }

        //check discountOfProductWithSale
        assertEquals(2.5, check.getDiscountOfProductWithSale());

        //check cardDiscount
        assertEquals(3.3, check.getCardDiscount());

        //check totalPrice
        assertEquals(23.4, check.getTotalPrice());

    }

}

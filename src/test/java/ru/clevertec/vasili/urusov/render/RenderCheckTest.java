package ru.clevertec.vasili.urusov.render;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.vasili.urusov.model.dto.Check;
import ru.clevertec.vasili.urusov.model.dto.CheckProduct;
import ru.clevertec.vasili.urusov.render.impl.RenderCheck;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RenderCheckTest {

    @Test
    public void testCorrectCase(){
        List<CheckProduct> productList = new ArrayList<>();
        productList.add(new CheckProduct("appal", 3.7, true, 18.5, 5));
        productList.add(new CheckProduct("cherry", 7.3, false, 14.6, 2));
        productList.add(new CheckProduct("blueberry", 8.5, true, 25.5, 3));

        Double discountOfProductWithSale = 1.3;
        Double cardDiscount = 2.5;
        Double totalPrice = 58.6;

        Check check = new Check(productList, discountOfProductWithSale, cardDiscount, totalPrice);
        String expected = "| QTY | DESCRIPTION    |   PRICE |   TOTAL |\r\n" +
                "| 5   | appal          |$   3.70 |$  18.50 |\r\n" +
                "| 2   | cherry         |$   7.30 |$  14.60 |\r\n" +
                "| 3   | blueberry      |$   8.50 |$  25.50 |\r\n" +
                "Total... $58.6\r\n" +
                "------------------------\r\n" +
                "Discount product with sale... $1.30\r\n" +
                "Discount with cart... $2.50\r\n" +
                "------------------------\r\n" +
                "Total with discount... $54.80\r\n";

        Render render = new RenderCheck(check);
        String renderedCheck = render.render();

        assertEquals(expected, renderedCheck);

    }
}

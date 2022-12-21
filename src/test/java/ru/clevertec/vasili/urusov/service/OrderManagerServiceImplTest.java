package ru.clevertec.vasili.urusov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.vasili.urusov.model.dto.OrderOfClientDTO;
import ru.clevertec.vasili.urusov.model.dto.Check;
import ru.clevertec.vasili.urusov.model.dto.CheckProduct;
import ru.clevertec.vasili.urusov.model.entity.Product;
import ru.clevertec.vasili.urusov.repository.CardRepository;
import ru.clevertec.vasili.urusov.repository.ProductRepository;
import ru.clevertec.vasili.urusov.service.impl.OrderManagerServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderManagerServiceImplTest {

    @InjectMocks
    OrderManagerServiceImpl orderManagerService;
    @Mock
    ProductRepository productRepository;
    @Mock
    CardRepository cardRepository;

    @BeforeEach
    public void before(){

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L,"appal", 3.0, true));
        productList.add(new Product(2L,"cherry", 7.0, true));
        productList.add(new Product(3L,"blueberry", 8.0, true));

        productList
                .forEach(product -> {
                    when(productRepository.existsById(product.getId())).thenReturn(true);
                    when(productRepository.findById(product.getId()))
                            .thenReturn(Optional.of(product));
                });

        Integer number = 1111;
        when(cardRepository.existsByNumber(number)).thenReturn(true);

    }
    @Test
    public void testCorrectCase(){

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L,"appal", 3.0, true));
        productList.add(new Product(2L,"cherry", 7.0, true));
        productList.add(new Product(3L,"blueberry", 8.0, true));


        Map<Long, Integer> idAndQuantityMap = new HashMap<>();
        idAndQuantityMap.put(1L, 1);
        idAndQuantityMap.put(2L, 3);
        idAndQuantityMap.put(3L, 2);

        Integer number = 1111;

        OrderOfClientDTO orderOfClientDTO = new OrderOfClientDTO(idAndQuantityMap, number);

        Check checkActual = orderManagerService.creatCheck(orderOfClientDTO);
        //check size
        assertEquals(3, checkActual.getProductsOfClient().size());

        //check
        Optional<CheckProduct> checkProductOptional = findProduct(checkActual, "appal");
        assertTrue(checkProductOptional.isPresent());
        assertEquals(1, checkProductOptional.get().getQuantity());
        assertEquals(3.0, checkProductOptional.get().getPricePerQuantity());

        checkProductOptional = findProduct(checkActual, "cherry");
        assertTrue(checkProductOptional.isPresent());
        assertEquals(3, checkProductOptional.get().getQuantity());
        assertEquals(21.0, checkProductOptional.get().getPricePerQuantity());

        checkProductOptional = findProduct(checkActual, "blueberry");
        assertTrue(checkProductOptional.isPresent());
        assertEquals(2, checkProductOptional.get().getQuantity());
        assertEquals(16.0, checkProductOptional.get().getPricePerQuantity());

        //check cardDiscount
        assertEquals(1.2, checkActual.getCardDiscount());

        //check totalPrice
        assertEquals(40.0, checkActual.getTotalPrice());

        //check totalPriceWithDiscount
        assertEquals(38.8, checkActual.getTotalPriseWithDiscount());

        //check
        assertEquals(0.0, checkActual.getDiscountOfProductWithSale());

    }

    private Optional<CheckProduct> findProduct(Check checkActual, String name) {
        return checkActual.getProductsOfClient()
                .stream()
                .filter(checkProduct -> checkProduct.getName().equals(name))
                .findFirst();
    }
}

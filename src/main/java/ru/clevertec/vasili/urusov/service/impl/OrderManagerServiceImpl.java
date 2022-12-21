package ru.clevertec.vasili.urusov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.vasili.urusov.model.dto.OrderOfClientDTO;
import ru.clevertec.vasili.urusov.model.entity.Check;
import ru.clevertec.vasili.urusov.model.entity.CheckProduct;
import ru.clevertec.vasili.urusov.model.entity.Product;
import ru.clevertec.vasili.urusov.repository.CardRepository;
import ru.clevertec.vasili.urusov.repository.ProductRepository;
import ru.clevertec.vasili.urusov.service.OrderManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManagerServiceImpl implements OrderManagerService {

    private final ProductRepository productRepository;
    private final CardRepository cardRepository;

    @Transactional(readOnly = true)
    public Check creatCheck(OrderOfClientDTO orderOfClientDTO){
        Check.CheckBuilder checkBuilder = Check.builder();
        hasProductInRepositoryById(orderOfClientDTO);

        List<CheckProduct> productsOfClient = creatListOfProductsOfClient(orderOfClientDTO);
        checkBuilder.setTotalPrice(totalPrice(productsOfClient));
        checkBuilder.setProductsOfClient(productsOfClient);
        creatDiscount(productsOfClient, orderOfClientDTO, checkBuilder);

        return checkBuilder.build();
    }


    private List<CheckProduct> creatListOfProductsOfClient(OrderOfClientDTO orderOfClientDTO) {
        List<CheckProduct> productsOfClient = new ArrayList<>();
        Map<Long, Integer> order = orderOfClientDTO.getIdAntQuantityOfProductOfClient();
        for (Map.Entry<Long, Integer> idAndQuantityOfProduct : order.entrySet()){
            Optional<Product> product = productRepository.findById(idAndQuantityOfProduct.getKey());
            Integer quantity = idAndQuantityOfProduct.getValue();
            Double pricePerQuantity = quantity * product.get().getPrice();
            CheckProduct checkProduct = new CheckProduct(product.get().getName(), product.get().getPrice(), product.get().getDiscount());
            checkProduct.setQuantity(quantity);
            checkProduct.setPricePerQuantity(pricePerQuantity);
            productsOfClient.add(checkProduct);
        }
        return productsOfClient;
    }

    private void hasProductInRepositoryById(OrderOfClientDTO orderOfClientDTO){
        Map<Long, Integer> order = orderOfClientDTO.getIdAntQuantityOfProductOfClient();
        for(Long id : order.keySet()) {
            if (!productRepository.existsById(id)) {
                throw new IllegalArgumentException("There isn't product with this id = " + id);
            }
        }
    }

    private Double totalPrice(List<CheckProduct> productsOfClient){
        double totalPrice = 0.;
        for (CheckProduct product : productsOfClient){
            totalPrice += product.getPricePerQuantity();
        }
        return totalPrice;
    }

    private void creatDiscount(List<CheckProduct> productsOfClient, OrderOfClientDTO orderOfClientDTO, Check.CheckBuilder checkBuilder) {
        Integer countOfProductWithDiscount = 0;
        for (CheckProduct checkProduct : productsOfClient){
            if (checkProduct.getDiscount()){
                countOfProductWithDiscount++;
            }
        }
        if (countOfProductWithDiscount > 5){
            checkBuilder.setDiscountOfProductWithSale(discountByProductWithDiscount(productsOfClient));
        }
        if (cardRepository.existsByNumber(orderOfClientDTO.getNumberDiscountCart())){
            checkBuilder.setCardDiscount(discountByAllProductInBasket(productsOfClient, countOfProductWithDiscount));
        }
    }

    private Double discountByProductWithDiscount(List<CheckProduct> productsOfClient) {
        Double priseOfProduct = 0.;
        for (CheckProduct productOfClient: productsOfClient){
            if (productOfClient.getDiscount()){
                priseOfProduct += productOfClient.getPricePerQuantity();
            }
        }
        Double discount = (priseOfProduct * 10) / 100;
        return discount;
    }

    private Double discountByAllProductInBasket(List<CheckProduct> productsOfClient, Integer countOfProductWithDiscount) {
        Double totalPrise = 0.;
        if (countOfProductWithDiscount > 5){
            for (CheckProduct product : productsOfClient){
                if (!product.getDiscount())
                totalPrise += product.getPricePerQuantity();
            }
        }else {
            for (CheckProduct product : productsOfClient){
                totalPrise += product.getPricePerQuantity();
            }
        }
        Double discount = (totalPrise*3)/100;
        return discount;
    }
}

package ru.clevertec.vasili.urusov.render.impl;

import ru.clevertec.vasili.urusov.model.dto.Check;
import ru.clevertec.vasili.urusov.model.dto.CheckProduct;
import ru.clevertec.vasili.urusov.render.Render;


public class RenderCheck implements Render {

    private Check check;

    public RenderCheck(Check check) {
        this.check = check;
    }

    @Override
    public String render() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("| %-4s| %-30s|%8s |%8s |%n", "QTY", "DESCRIPTION", "PRICE", "TOTAL"));
        for (CheckProduct product : check.getProductsOfClient()){
            stringBuilder.append(String.format("| %-4d| %-30s|$%7.2f |$%7.2f |%n", product.getQuantity(), product.getName() ,product.getPrice() ,product.getPricePerQuantity()));
        }
        stringBuilder.append("Total... $").append(String.format("%.2f", check.getTotalPrice())).append("\r\n");
        stringBuilder.append("------------------------\r\n");
        stringBuilder.append("Discount product with sale... $").append(String.format("%.2f", check.getDiscountOfProductWithSale())).append("\r\n");
        stringBuilder.append("Discount with cart... $").append(String.format("%.2f", check.getCardDiscount())).append("\r\n");
        stringBuilder.append("------------------------\r\n");
        stringBuilder.append("Total with discount... $").append(String.format("%.2f", check.getTotalPriseWithDiscount())).append("\r\n");
        return stringBuilder.toString();
    }
}

package ru.clevertec.vasili.urusov.model.entity;

import java.util.List;

public class Check {

    private final List<CheckProduct> productsOfClient;
    private final Double discountOfProductWithSale;
    private final Double cardDiscount;
    private final Double totalPrice;

        public Check(List<CheckProduct> productOfClient, Double discountOfProductWithSale, Double cardDiscount, Double totalPrice) {
            this.productsOfClient = productOfClient;
            this.discountOfProductWithSale = discountOfProductWithSale;
            this.cardDiscount = cardDiscount;
            this.totalPrice = totalPrice;
        }

        public List<CheckProduct> getProductsOfClient() {
            return productsOfClient;
        }

        public Double getDiscountOfProductWithSale() {
            return discountOfProductWithSale;
        }

        public Double getCardDiscount() {
            return cardDiscount;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public Double getTotalPriseWithDiscount(){
            Double totalPriseWithDiscount = totalPrice - (discountOfProductWithSale + cardDiscount);
            return totalPriseWithDiscount;
        }

    public static CheckBuilder builder(){
        return new CheckBuilder();
    }

    public static class CheckBuilder{

        private List<CheckProduct> productsOfClient;
        private Double discountOfProductWithSale;
        private Double cardDiscount;
        private Double totalPrice;

        public CheckBuilder setProductsOfClient(List<CheckProduct> productOfClient) {
            this.productsOfClient = productOfClient;
            return this;
        }

        public CheckBuilder setDiscountOfProductWithSale(Double discountOfProductWithSale) {
            this.discountOfProductWithSale = discountOfProductWithSale;
            return this;
        }

        public CheckBuilder setCardDiscount(Double cardDiscount) {
            this.cardDiscount = cardDiscount;
            return this;
        }

        public CheckBuilder setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Check build(){

            if (this.productsOfClient == null){
                throw new NullPointerException("CheckBuilder dont has productOfClient");
            }
            if (this.discountOfProductWithSale == null){
                this.discountOfProductWithSale = 0.;
            }
            if (this.cardDiscount == null){
                this.cardDiscount = 0.;
            }
            if (this.totalPrice == null){
                throw new NullPointerException("CheckBuilder dont has totalPrice");
            }
            Check check = new Check(productsOfClient, discountOfProductWithSale, cardDiscount, totalPrice);
            return check;
        }
    }

}

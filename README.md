# Clevertec Test Task

## Vasily Urusov

### Contacts
- __Location__: Minsk, Belarus
- __Phone__: +375 29 778-48-36
- __Email__: uvd.1994@gmail.com
- __GitHub__: [uvde](https://github.com/uvde)


Stack

+ Java 17
+ Gradle 7.5
+ Spring Boot
+ PostgreSQL
+ Docker
+ FlyWay
+ Swagger

### Start This Project
1. git clone https://github.com/uvde/cleveretec.git
2. go to directory of project 
3. docker-compose up


### Pattern that used in project
- Builder
is the best decision for a difficult entity.
```java
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
```
- Decorator.
I use it for make a good render in CheckServiceImpl.
```java
 Render render = new RenderObverseInformation(new RenderCheck(check));
 return render.render();
```
[Render](https://github.com/uvde/cleveretec/blob/63a9a8aa41289dd3ec9abb9ab564bf4627689cb9/src/main/java/ru/clevertec/vasili/urusov/render)
- Factory. 
I use it only for example 
[OutputFactory.java](https://github.com/uvde/cleveretec/blob/63a9a8aa41289dd3ec9abb9ab564bf4627689cb9/src/main/java/ru/clevertec/vasili/urusov/output/OutputFactory.java).
This class is singleton with double check.

## Endpoints

 __Swagger__ : http://localhost:8080/swagger-ui/index.html#/

### Endpoint for check

http://localhost:8080/v1/check/creat
```json
{
    "idAntQuantityOfProductOfClient": {
        "1": 1,
        "2": 4,
        "3": 2,
        "4": 3,
        "5": 5,
        "6": 3
    },
    "numberDiscountCart": 1111
}
```
Use the PostMan to post this json to app.
[Example of check](https://github.com/uvde/cleveretec/blob/cd058476c2052e2a596af242d2f68f9acd93f0e4/data/check.txt)

## Command-line arguments
I created this class [ParserRequestClient.java](https://github.com/uvde/cleveretec/blob/9ef54cfadcddeeced9886b022f02772b09c21169/src/main/java/ru/clevertec/vasili/urusov/util/ParserRequestClient.java) for parsing the information from the command-line arguments.
It does nothing in Spring app because there is a more flexible and efficient way to get the  information such as json.

## Migration db
I use FlyWay to create tables in PostgreSQL. It is a simple migration db in Spring App. 
[There are](https://github.com/uvde/cleveretec/blob/0e45683ec53da607db4ebbe7dc49a4f56e1f2359/src/main/resources/db/migration) SQL scripts for migration.
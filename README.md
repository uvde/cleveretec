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

### Start This Project
1. git clone https://github.com/uvde/cleveretec.git
2. go to directory of project 
3. docker-compose up

### Pattern that used in project
- Builder
is the best decision for difficult entity.
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
- Factory
I use it only for example.  
ru/clevertec/vasili/urusov/output/OutputFactory.java

## Endpoints

 __Swagger__ : http://localhost:8080/swagger-ui/index.html#/

## How to populate the database
Use the Postman for post json to app. 
1 http://localhost:8080/v1/cards/list/add
```json
[
    {
        "number": 1111
    },
    {
        "number": 1112
    },
    {
        "number": 1113
    },
    {
        "number": 1114
    },
    {
        "number": 2222
    },
    {
        "number": 1144
    },
    {
        "number": 5555
    },
    {
        "number": 6969
    },
    {
        "number": 7777
    },
    {
        "number": 8888
    },
    {
        "number": 9999
    },
    {
        "number": 1010
    }
]
```

2 http://localhost:8080/v1/products/list/add
```json
[
    {
        "name": "Syrup - Golden, Lyles",
        "price": 60.15,
        "discount": true
    },
    {
        "name": "Compound - Passion Fruit",
        "price": 45.87,
        "discount": true
    },
    {
        "name": "Pickerel - Fillets",
        "price": 77.07,
        "discount": true
    },
    {
        "name": "Tea - Herbal I Love Lemon",
        "price": 19.97,
        "discount": true
    },
    {
        "name": "Olives - Green, Pitted",
        "price": 24.53,
        "discount": true
    },
    {
        "name": "Dish Towel",
        "price": 57.88,
        "discount": false
    },
    {
        "name": "Ice Cream - Strawberry",
        "price": 40.2,
        "discount": false
    },
    {
        "name": "Mcgillicuddy Vanilla Schnap",
        "price": 81.57,
        "discount": true
    },
    {
        "name": "Spice - Greek 1 Step",
        "price": 14.6,
        "discount": true
    },
    {
        "name": "Grenadine",
        "price": 78.46,
        "discount": false
    },
    {
        "name": "Turnip - White, Organic",
        "price": 80.79,
        "discount": true
    },
    {
        "name": "Appetizer - Escargot Puff",
        "price": 58.88,
        "discount": true
    },
    {
        "name": "Cheese - Wine",
        "price": 75.02,
        "discount": true
    },
    {
        "name": "Salt - Sea",
        "price": 87.77,
        "discount": false
    },
    {
        "name": "Lemonade - Black Cherry",
        "price": 31.1,
        "discount": false
    },
    {
        "name": "Plate Pie Foil",
        "price": 41.99,
        "discount": true
    },
    {
        "name": "Tea - Decaf Lipton",
        "price": 25.7,
        "discount": true
    },
    {
        "name": "Nut - Pistachio, Shelled",
        "price": 68.93,
        "discount": false
    },
    {
        "name": "Coconut - Shredded, Unsweet",
        "price": 25.79,
        "discount": false
    },
    {
        "name": "Cookie Dough - Chunky",
        "price": 29.84,
        "discount": false
    },
    {
        "name": "Yoplait - Strawbrasp Peac",
        "price": 51.46,
        "discount": false
    },
    {
        "name": "Cherries - Maraschino,jar",
        "price": 97.27,
        "discount": true
    },
    {
        "name": "Juice - Grape, White",
        "price": 30.82,
        "discount": false
    },
    {
        "name": "Beets - Golden",
        "price": 98.88,
        "discount": true
    },
    {
        "name": "Wine - Sauvignon Blanc",
        "price": 52.17,
        "discount": false
    },
    {
        "name": "Sauce - Demi Glace",
        "price": 6.53,
        "discount": false
    },
    {
        "name": "Cinnamon Buns Sticky",
        "price": 69.87,
        "discount": true
    },
    {
        "name": "Vinegar - Balsamic, White",
        "price": 19.13,
        "discount": false
    },
    {
        "name": "Banana",
        "price": 53.87,
        "discount": false
    },
    {
        "name": "Yeast Dry - Fleischman",
        "price": 1.86,
        "discount": false
    },
    {
        "name": "Ecolab Silver Fusion",
        "price": 21.06,
        "discount": false
    },
    {
        "name": "Crush - Cream Soda",
        "price": 26.17,
        "discount": false
    },
    {
        "name": "Wine - White, Pinot Grigio",
        "price": 38.48,
        "discount": false
    },
    {
        "name": "Sauce - Apple, Unsweetened",
        "price": 10.24,
        "discount": false
    },
    {
        "name": "Lamb - Leg, Bone In",
        "price": 34.13,
        "discount": true
    },
    {
        "name": "Wine - Domaine Boyar Royal",
        "price": 14.3,
        "discount": false
    },
    {
        "name": "Wine - Trimbach Pinot Blanc",
        "price": 1.51,
        "discount": false
    },
    {
        "name": "Soup - Verve - Chipotle Chicken",
        "price": 67.08,
        "discount": false
    },
    {
        "name": "Nantucket - 518ml",
        "price": 37.73,
        "discount": false
    },
    {
        "name": "Juice - V8 Splash",
        "price": 40.58,
        "discount": false
    },
    {
        "name": "Appetizer - Smoked Salmon / Dill",
        "price": 17.25,
        "discount": false
    },
    {
        "name": "Roe - White Fish",
        "price": 71.38,
        "discount": false
    }
]
```

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

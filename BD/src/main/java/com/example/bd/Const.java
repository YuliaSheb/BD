package com.example.bd;

public class Const {
    public static final String USER_TABLE = "Account";
    public static final Integer USERS_ID = Integer.valueOf("Id");
    public static final String USERS_LOGIN = "Login";
    public static final String USERS_PASSWORD = "Passwords";

    public static final String CUSTOMER_TABLE = "Customer";
    public static final Integer CUSTOMER_ID = Integer.valueOf("Id");
    public static final String CUSTOMER_NAME = "Name";
    public static final String CUSTOMER_SURNAME = "Surname";
    public static final String CUSTOMER_PATRONOMIC = "Patronomic";
    public static final Integer CUSTOMER_BASKETID = Integer.valueOf("BasketId");
    public static final Integer CUSTOMER_ACCOUNTID = Integer.valueOf("AccountId");

    public static final String CONFECTION_TABLE = "Confection";
    public static final Integer CONFECTION_ID = Integer.valueOf("Id");
    public static final String CONFECTION_NAME = "Name";
    public static final String CONFECTION_SURNAME = "Surname";
    public static final String CONFECTION_PATRONOMIC = "Patronomic";
    public static final Integer CONFECTION_TYPEDESSERTID = Integer.valueOf("TypeDessertId");
    public static final Integer CONFECTION_ACCOUNTID = Integer.valueOf("AccountId");

    public static final String TYPEDESSERT_TABLE = "TypeDessert";
    public static final Integer TYPEDESSERT_ID = Integer.valueOf("Id");
    public static final String TYPEDESSERT_TYPENAME = "TypeName";

    public static final String REVIEW_TABLE = "Review";
    public static final String REVIEW_ID = "Id";
    public static final String REVIEW_CUSTOMERID = "CustomerId";
    public static final String REVIEW_REVIEWS = "Reviews";
    public static final String REVIEW_ORDERSID = "OrdersId";

    public static final String ORDERS_TABLE = "Orders";
    public static final String ORDERS_ID = "Id";
    public static final String ORDERS_BASKETID = "BasketId";
    public static final String ORDERS_STATEORDER = "StateOrder";
    public static final String ORDERS_PRICES = "Prices";

    public static final String INGREDIENTS_TABLE = "Ingredients";
    public static final Integer INGREDIENTS_ID = Integer.valueOf("Id");
    public static final Integer INGREDIENTS_INGREDIENID = Integer.valueOf("IngredientId");
    public static final Integer INGREDIENTS_DESSERTID = Integer.valueOf("DessertId");

    public static final String DETAILS_TABLE = "Destails";
    public static final Integer DETAILS_ID = Integer.valueOf("Id");
    public static final Integer DETAILS_ORDERID = Integer.valueOf("OrderId");
    public static final Integer DETAILS_DESSERTID = Integer.valueOf("DessertId");

    public static final String INGREDIENT_TABLE = "Ingredient";
    public static final Integer INGREDIENT_ID = Integer.valueOf("Id");
    public static final String INGREDIENT_NAME = "Name";
    public static final Integer INGREDIENT_PROTEIN = Integer.valueOf("Protein");
    public static final Integer INGREDIENT_FATS = Integer.valueOf("Fats");
    public static final Integer INGREDIENT_CARBOHYDRATES = Integer.valueOf("Carbohydrates");

    public static final String DESSERT_TABLE = "Dessert";
    public static final Integer DESSERT_ID = Integer.valueOf("Id");
    public static final String DESSERT_NAME = "Name";
    public static final Integer DESSERT_TYPEDESSERTID = Integer.valueOf("TypeDessertId");

    public static final String DELIVERY_TABLE = "Delivery";
    public static final String DELIVERY_ID = "Id";
    public static final String DELIVERY_ORDERID = "OrderId";
    public static final String DELIVERY_CITY = "City";
    public static final String DELIVERY_STREET = "Street";
    public static final String DELIVERY_NUMBERHOME = "Numberhome";

    public static final String BASKET_TABLE = "Basket";
    public static final Integer BASKET_ID = Integer.valueOf("Id");
    public static final Integer BASKET_COUNTORDER = Integer.valueOf("CountOrder");
}

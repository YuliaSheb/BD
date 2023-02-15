package com.example.bd;

public class Basket{
    private Integer OrderId;
    private String Name;
    private String Type;
    private String StateOrder;
    private Double Prices;

    public Basket(int OrderId, String Name, String Type, String StateOrder, Double Prices) {
        this.OrderId = OrderId;
        this.Name = Name;
        this.Type = Type;
        this.StateOrder = StateOrder;
        this.Prices = Prices;
    }

    public Integer getId() {
        return OrderId;
    }

    public void setId(Integer OrderId) {
        this.OrderId = OrderId;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

    public Double getPrices() {
        return Prices;
    }

    public void setPrices(Double Prices) {
        this.Prices = Prices;
    }

    public void setStateOrder(String StateOrder) {
        this.StateOrder = StateOrder;
    }

    public String getStateOrder() {
        return StateOrder;
    }
}

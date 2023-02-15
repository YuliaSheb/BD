package com.example.bd;

public class Order {
    private Integer Id;
    private String Name;
    private String Type;
    private String StateOrder;

    public Order(int Id, String Name,String Type,String StateOrder) {
        this.Id = Id;
        this.Name = Name;
        this.Type = Type;
        this.StateOrder = StateOrder;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
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
    public void setStateOrder(String StateOrder) {
        this.StateOrder = StateOrder;
    }

    public String getStateOrder() {
        return StateOrder;
    }
}

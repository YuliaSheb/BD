package com.example.bd;

public class Dessert {
    private Integer Id;
    private String Name;
    private String Type;

    public Dessert(int Id, String Name,String Type) {
        this.Id = Id;
        this.Name = Name;
        this.Type = Type;
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
}

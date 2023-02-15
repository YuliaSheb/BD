package com.example.bd;

public class DessertC {
    private Integer Id;
    private String Name;
    private String Type;
    private String Ingredient;
    private Integer Protein;
    private Integer Fat;
    private Integer Carb;

    public DessertC(int Id, String Name,String Type,String Ingredient,int Protein,int Fat,int Carb) {
        this.Id = Id;
        this.Name = Name;
        this.Type = Type;
        this.Ingredient = Ingredient;
        this.Protein = Protein;
        this.Fat = Fat;
        this.Carb = Carb;
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

    public String getType() { return Type; }

    public void setIngredient(String Ingredient) {
        this.Ingredient = Ingredient;
    }

    public String getIngredient() { return Ingredient; }

    public Integer getProtein() {
        return Protein;
    }

    public void setProtein(Integer Protein) {
        this.Protein = Protein;
    }

    public Integer getFat() {
        return Fat;
    }

    public void setFat(Integer Fat) {
        this.Fat = Fat;
    }

    public Integer getCarb() {
        return Carb;
    }

    public void setCarb(Integer Carb) {
        this.Carb = Carb;
    }
}

package com.example.bd;

public class Profile {
    private String Name;
    private String Surname;
    private String Patronomic;

    public Profile(String Name,String Surname,String Patronomic) {
        this.Name = Name;
        this.Surname = Surname;
        this.Patronomic = Patronomic;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getPatronomic() {
        return Patronomic;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public void setPatronomic(String patronomic) {
        this.Patronomic = patronomic;
    }
}

package com.example.bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionSTring = "jdbc:postgresql://" +dbHost + ":" +dbPort + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectionSTring, dbUser,dbPass);
        return dbConnection;
    }
    public void SignUpUser(String login,String password)
    {
        String insert = "INSERT INTO Account(Login,Passwords) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUserCustomer(String login,String password) {
        ResultSet resultSet = null;
        String select = "SELECT Account.Id,Account.Login,Account.Passwords,Customer.AccountId FROM Account " +
                "JOIN Customer ON Account.Id = Customer.AccountId WHERE Account.Login = ? AND Account.Passwords =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getUserConfection(String login,String password) {
        ResultSet resultSet = null;
        String select = "SELECT Account.Id,Account.Login,Account.Passwords,Confection.AccountId FROM Account " +
                "JOIN Confection ON Account.Id = Confection.AccountId WHERE Account.Login = ? AND Account.Passwords =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ObservableList<Profile> getProfile(String log) {
        ObservableList<Profile> list = FXCollections.observableArrayList();
        String select = "SELECT Name,Surname,Patronomic FROM Customer WHERE AccountId = (SELECT Id FROM Account WHERE Login = ?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, log);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String Name = resultSet.getString("Name");
                String Surname = resultSet.getString("Surname");
                String Patronomic = resultSet.getString("Patronomic");
                list.add(new Profile(Name,Surname,Patronomic));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void UpdateProfile(String name, String surname, String patronomic, String log)
    {
        String update = "UPDATE Customer SET Name = ?, Surname = ?, Patronomic = ? WHERE AccountId = (SELECT Id FROM Account WHERE Login = ?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,patronomic);
            preparedStatement.setString(4, log);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Order> getOrderC() {
        ObservableList<Order> list = FXCollections.observableArrayList();
        String select = "SELECT Orders.Id,Dessert.Name,TypeDessert.TypeName,Orders.StateOrder FROM Dessert " +
                "JOIN TypeDessert ON TypeDessert.Id = Dessert.TypeDessertId\n" +
                "JOIN Destails ON Dessert.Id = Destails.DessertId\n" +
                "JOIN Orders ON Orders.Id = Destails.OrderId";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                String Type = resultSet.getString("TypeName");
                String StateOrder = resultSet.getString("StateOrder");
                list.add(new Order(Id,Name,Type,StateOrder));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList<Basket> getBasket() {
        ObservableList<Basket> list = FXCollections.observableArrayList();
        String select = "SELECT Orders.Id,Dessert.Name,TypeDessert.TypeName,Orders.StateOrder,Orders.Prices FROM Dessert " +
                "JOIN TypeDessert ON TypeDessert.Id = Dessert.TypeDessertId " +
                "JOIN Destails ON Dessert.Id = Destails.DessertId " +
                "JOIN Orders ON Orders.Id = Destails.OrderId WHERE ORDERS.STATEORDER = 'Обрабатывается' AND Orders.Id = Orders.Id";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                String Type = resultSet.getString("TypeName");
                String StateOrder = resultSet.getString("StateOrder");
                Double Prices = resultSet.getDouble("Prices");
                list.add(new Basket(Id,Name,Type,StateOrder,Prices));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void Ord(Integer Id)
    {
        String update = "UPDATE Orders SET StateOrder = 'Готовится' WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void DoneOrd(Integer Id)
    {
        String update = "UPDATE Orders SET StateOrder = 'Готов' WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Dessert> getDessert() {
        ObservableList<Dessert> list = FXCollections.observableArrayList();
        String select = "SELECT Dessert.Id,Dessert.Name,Dessert.TypeDessertId,TypeDessert.Id,TypeDessert.TypeName FROM Dessert JOIN TypeDessert ON TypeDessert.Id = Dessert.TypeDessertId";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                String Type = resultSet.getString("TypeName");
                list.add(new Dessert(Id,Name,Type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList<DessertC> getDessertC() {
        ObservableList<DessertC> list = FXCollections.observableArrayList();
        String select = "SELECT Dessert.Id,Dessert.Name,Dessert.TypeDessertId,\n" +
                "TypeDessert.Id,TypeDessert.TypeName,Ingredient.Name AS Ingred,\n" +
                "Ingredient.Protein, Ingredient.Fats, Ingredient.Carbohydrates \n" +
                "FROM Dessert \n" +
                "JOIN TypeDessert ON TypeDessert.Id = Dessert.TypeDessertId\n" +
                "JOIN Ingredients ON Dessert.Id = Ingredients.DessertId \n" +
                "JOIN Ingredient ON Ingredients.IngredientId = Ingredient.Id";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                String Type = resultSet.getString("TypeName");
                String Ingred = resultSet.getString("Ingred");
                Integer Protein = resultSet.getInt("Protein");
                Integer Fat = resultSet.getInt("Fats");
                Integer Carb = resultSet.getInt("Carbohydrates");
                list.add(new DessertC(Id,Name,Type,Ingred,Protein,Fat,Carb));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void CreateBasket()
    {
        String insert = "INSERT INTO Basket(Id,CountOrder) VALUES((SELECT Id FROM Account ORDER BY Id DESC LIMIT 1) ,1)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AddReview(String log,String review,Integer Id)
    {
        String insert = "INSERT INTO Review(CustomerId,Reviews,OrdersId) VALUES((SELECT Id FROM Account WHERE Login = ?),?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,log);
            preparedStatement.setString(2,review);
            preparedStatement.setInt(3,Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AddCustomer(String name,String surname,String patronomic)
    {
        String insert = "INSERT INTO Customer(Name,Surname,Patronomic,BasketId,AccountId) VALUES(?,?,?,(SELECT Id FROM Account ORDER BY Id DESC LIMIT 1) ,(SELECT Id FROM Account ORDER BY Id DESC LIMIT 1))";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,patronomic);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AddDelivery(String city,String street,Integer numberHome)
    {
        String insert = "INSERT INTO Delivery (OrderId,City,Street,NumberHome) VALUES ((SELECT id FROM Orders ORDER BY Id DESC LIMIT 1) ,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,city);
            preparedStatement.setString(2,street);
            preparedStatement.setInt(3,numberHome);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AddDesForOrd(Integer count, String StateOrder, double Price, Integer DessertId, String log)
    {
        String insert = "INSERT INTO Orders(BasketId,StateOrder,Prices) VALUES ((SELECT BASKET.ID FROM BASKET " +
                "JOIN ACCOUNT ON ACCOUNT.ID = BASKET.ID WHERE Login = ?),?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,log);
            preparedStatement.setString(2,StateOrder);
            preparedStatement.setDouble(3,Price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String insert2 = "INSERT INTO Destails (OrderId,DessertId) VALUES ((SELECT Orders.ID FROM Orders ORDER BY Orders.Id DESC LIMIT 1),?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert2);
            preparedStatement.setInt(1,DessertId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void UpdateState(Integer Id)
    {
        String update = "UPDATE Orders SET StateOrder = 'Оформлен' WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setInt(1,Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AddNewDessertTest(String name, String type) {
        String insert = "INSERT INTO Dessert(Name,TypeDessertId) VALUES (?,(SELECT Id FROM TypeDessert WHERE TypeName =?))";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AddNewIngredient(String ingredient, Integer protein, Integer fat, Integer carbohydrate)
    {
        ResultSet resultSet = null;
        String select = "SELECT Ingredient(Name,Protein,Fats,Carbohydrates) WHERE Name =? AND Protein =? AND Fats =? " +
                "AND Carbohydrates =?";
        String insert2 = "INSERT INTO Ingredient(Name,Protein,Fats,Carbohydrates) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1,ingredient);
            preparedStatement.setInt(2, protein);
            preparedStatement.setInt(3, fat);
            preparedStatement.setInt(4, carbohydrate);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
            {
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert2);
                preparedStatement2.setString(1,ingredient);
                preparedStatement.setInt(2, protein);
                preparedStatement.setInt(3, fat);
                preparedStatement.setInt(4, carbohydrate);
                preparedStatement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        String insert3 = "INSERT INTO Ingredients(DessertId,IngredientId) VALUES ((SELECT Id FROM Dessert " +
//                "WHERE Name =?),(SELECT Id FROM Ingredient WHERE Name =?))";
//        try {
//            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert3);
//            preparedStatement.setString(1,name);
//            preparedStatement.setString(2, ingredient);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    public void UpdateDessert(String name, String type, Integer Id)
    {
        String update = "UPDATE Dessert SET Name = ?, TypeDessertId = (SELECT Id FROM TypeDessert WHERE TypeName =?) WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,type);
            preparedStatement.setInt(3, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void DeleteDessert(Integer Id)
    {
        String update = "DELETE FROM Dessert WHERE Id =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AddConfection(String name,String surname,String patronomic)
    {
        String insert = "INSERT INTO Confection(Name,Surname,Patronomic,AccountId) VALUES(?,?,?,(SELECT Id FROM Account ORDER BY Id DESC LIMIT 1))";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,patronomic);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

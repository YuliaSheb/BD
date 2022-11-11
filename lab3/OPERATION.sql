SELECT * FROM Customers;
SELECT name, Surname FROM Confections;
SELECT * FROM Delivery WHERE City='Минск';
SELECT Name, Surname FROM Customers WHERE AccountId = 1;
SELECT * FROM Dessert WHERE TypeDessertId > 1 AND Name not in ('Черный лес', 'Песочное печенье');
SELECT City, count(OrderID) from Delivery GROUP BY City;
SELECT City, count(OrderID) FROM Delivery GROUP BY City HAVING count(OrderID) >= 2;
SELECT * FROM Ingredient ORDER BY Name;
SELECT * FROM Orders JOIN Dessert ON Orders.DessertId = Dessert.Id;

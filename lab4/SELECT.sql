SELECT Id, Name 
FROM Ingredient  
WHERE (Name LIKE 'С%') OR (Name ILIKE 'М%');  

SELECT  Name,
        (SELECT TypeName FROM TypeDessert 
        WHERE Typedessert.Id = Dessert.TypeDessertId) AS Type
FROM Dessert;

SELECT  Name,Surname,Patronomic,
        (SELECT Login FROM Account 
        WHERE Account.Id = Confections.AccountId) AS Log,
		(SELECT TypeName FROM TypeDessert 
        WHERE TypeDessert.Id = Confections.TypeDessertId) AS Des
FROM Confections;

SELECT p.Id, p.Name
FROM Dessert p
WHERE p.Confectionsid IN
   (SELECT c.Id
    FROM Confections c
    WHERE c.SurName like 'Ш%');

SELECT City, count(OrderID) from Delivery GROUP BY City;
SELECT City, count(OrderID) FROM Delivery GROUP BY City HAVING count(OrderID) >= 2;
SELECT * FROM Orders INNER JOIN Dessert ON Orders.DessertId = Dessert.Id;
SELECT * FROM Orders LEFT JOIN Dessert ON Orders.DessertId = Dessert.Id;
SELECT * FROM Orders RIGHT JOIN Dessert ON Orders.DessertId = Dessert.Id;
SELECT * FROM Orders FULL JOIN Basket ON Orders.BasketId = Basket.Id;
SELECT * FROM Orders CROSS JOIN Dessert;
SELECT Confections.SurName, Confections.Name, Confections.Patronomic, TypeDessert.TypeName, Dessert.Name
FROM Dessert 
LEFT JOIN Confections ON Dessert.ConfectionsId = Confections.Id
LEFT JOIN TypeDessert ON Dessert.TypeDessertId = TypeDessert.Id;
SELECT Name, SurName FROM Customers UNION SELECT Name, SurName FROM Confections;

SELECT Name FROM Dessert d 
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.dessertId = d.Id
       AND prices > 30 )
ORDER BY Name;

CREATE TABLE ConfectionsTest (LIKE Confections);
INSERT INTO ConfectionsTest
SELECT * FROM Confections where Surname LIKE 'Ш%';

CREATE TABLE CustomersTest (LIKE Customers);
INSERT INTO CustomersTest
SELECT * FROM Customers where Patronomic ILIKE '%о%';

SELECT Prices,
		 	CASE 
				WHEN Prices> 0
                	AND Prices <= 5 THEN 'Cheap'
           	WHEN Prices > 5
                	AND Prices <= 30 THEN 'Medium'
           	WHEN Prices > 30 THEN 'Expensive'
       	 	END duration
FROM Orders;

SELECT Prices,
		 	CASE 
				WHEN Prices> 0
                	AND Prices <= 5 THEN 'Cheap'
           	WHEN Prices > 5
                	AND Prices <= 30 THEN 'Medium'
           	WHEN Prices > 30 THEN 'Expensive'
       	 	END duration,
			(SELECT Name FROM Dessert WHERE Dessert.Id=Orders.DessertId)
FROM Orders;

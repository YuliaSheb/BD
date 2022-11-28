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


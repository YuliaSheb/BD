CREATE TABLE Account
(
	Id SERIAL PRIMARY KEY,
	Login CHARACTER VARYING(30) NOT NULL UNIQUE,
	Passwords CHARACTER VARYING(30) NOT NULL 
);

CREATE TABLE Confection
(
	Id SERIAL PRIMARY KEY,
	Name CHARACTER VARYING(30) NOT NULL,
	Surname CHARACTER VARYING(30) NOT NULL,
	Patronomic CHARACTER VARYING(30) NOT NULL,
	AccountId INTEGER NOT NULL,
	FOREIGN KEY (AccountId) REFERENCES Account (Id)
);
CREATE TABLE TypeDessert
(
	Id SERIAL PRIMARY KEY,
	TypeName CHARACTER VARYING(30) NOT NULL,
        ConfectionId INTEGER NOT NULL,
        FOREIGN KEY (ConfectionId) REFERENCES Confection (Id)
);
CREATE TABLE Basket
(
	Id SERIAL PRIMARY KEY,
	CountOrder INTEGER NOT NULL
	CustomerId INTEGER NOT NULL,
        FOREIGN KEY (CustomerId) REFERENCES Customer (Id)
);
CREATE TABLE Orders
(
	Id SERIAL PRIMARY KEY,
	BasketId INTEGER NOT NULL,
	Cost DECIMAL NOT NULL,
	OrderState CHARACTER VARYING(30) NOT NULL,
	FOREIGN KEY (BasketId) REFERENCES Basket (Id)
);
CREATE TABLE Dessert
(
	Id SERIAL PRIMARY KEY,
	Name CHARACTER VARYING(30) NOT NULL,
	Price DECIMAL NOT NULL, 
	TypeId INTEGER NOT NULL,
	FOREIGN KEY (TypeId) REFERENCES TypeDessert (Id)
);
CREATE TABLE OrderDetails
(
        Id SERIAL PRIMARY KEY, 
        OrderId INTEGER NOT NULL,
        DessertId INTEGER NOT NULL,
        FOREIGN KEY (OrderId) REFERENCES Orders (Id),
        FOREIGN KEY (DessertId) REFERENCES Dessert (Id)
);
CREATE TABLE Customer
(
	Id SERIAL PRIMARY KEY,
	Name CHARACTER VARYING(30) NOT NULL,
	Surname CHARACTER VARYING(30) NOT NULL,
	Patronomic CHARACTER VARYING(30) NOT NULL,
	BasketId INTEGER NOT NULL,
	AccountId INTEGER NOT NULL,
	FOREIGN KEY (AccountId) REFERENCES Account (Id),
	FOREIGN KEY (BasketId) REFERENCES Basket (Id)
);
CREATE TABLE Review
(
	Id SERIAL PRIMARY KEY,
	CustomerId INTEGER NOT NULL,
	Review CHARACTER VARYING(1000) NOT NULL,
	OrdersId INTEGER NOT NULL,
	FOREIGN KEY (OrdersId) REFERENCES Orders (Id),
	FOREIGN KEY (CustomerId) REFERENCES Customers (Id)
);
CREATE TABLE Delivery
(
	Id SERIAL PRIMARY KEY,
	OrderId INTEGER NOT NULL,
	City CHARACTER VARYING(30) NOT NULL,
	Street CHARACTER VARYING(30) NOT NULL,
	HomeNumber INTEGER NOT NULL,
	FOREIGN KEY (OrderId) REFERENCES Orders (Id)
);
CREATE TABLE Ingredient
(
	Id SERIAL PRIMARY KEY,
	Name CHARACTER VARYING(30) NOT NULL,
	Protein INTEGER NOT NULL,
	Fats INTEGER NOT NULL,
	Carbohydrates INTEGER NOT NULL
);
CREATE TABLE Ingredients
(
	Id SERIAL PRIMARY KEY,
	IngredientId INTEGER NOT NULL,
	DessertId INTEGER NOT NULL,
	FOREIGN KEY (IngredientId) REFERENCES Ingredient (Id),
	FOREIGN KEY (DessertId) REFERENCES Dessert (Id)
);

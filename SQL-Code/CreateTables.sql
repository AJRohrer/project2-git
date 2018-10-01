#Tables need to be created in this order, the dependencies are such that if a table is created out of order the next table will not
#see the reference it expects, resulting in failed creation of tables.

USE eCommerceDB;
CREATE TABLE Orders (
    OrderID INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY, #we dont want a null primary key, we need an ID for every order.
    orderStatus VARCHAR(255),
    FOREIGN KEY (CustomerID) 
    REFERENCES Customer(CustomerID)
); 

CREATE TABLE Address(
	AddressID INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Street VARCHAR(255),
    AptNo VARCHAR(255),
    City VARCHAR(255),
    State VARCHAR(255),
    ZipCode VARCHAR(255)
);


CREATE TABLE Customer (
	CustomerID INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    FName VARCHAR(255),
    LName VARCHAR(255),
    Phone VARCHAR(255),
    Username VARCHAR(255),
    Password VARCHAR(255),
    Email VARCHAR(255),
    AddressID INT(32) NOT NULL,
    #this is creating a link to the address table. This just makes sure that when an address is created that it has a valid customer number assigned to it.
    FOREIGN KEY fk_AddressID(AddressID)
    REFERENCES Address(AddressID) #this tells this data type what table to find the related data
);

CREATE TABLE CreditCard (
	ccNo INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ExpirationDate VARCHAR(255),
    ccHolderFName VARCHAR(255),
    ccSecurityCode INT(32),
    CustomerID INT(32) NOT NULL,
    FOREIGN KEY fk_CustomerID(CustomerID)
    REFERENCES Customer(CustomerID)
);

CREATE TABLE Partner (
	PartnerID INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CompanyName VARCHAR(255),
    AddressID INT(32) NOT NULL,
    FOREIGN KEY fk_AddressID(AddressID)
    REFERENCES Address(AddressID)
);

#ALTER TABLE Product ADD PartnerID INT(32) NOT NULL;
#ALTER TABLE Product ADD CONSTRAINT fk_PartnerID FOREIGN KEY (PartnerID) REFERENCES Partner(PartnerID);

CREATE TABLE Product (
	ProductID INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255),
    Description VARCHAR(255),
    Price DECIMAL(7,2), #holds up to 7 digits total, always two decimals. Ex: 12345.67 or 54321.00
    ProductID INT(32) NOT NULL,
    FOREIGN KEY fk_product(ProductID)
    REFERENCES Product(ProductID)
);

#just used to relate products to orders and the product data.
CREATE TABLE OrderList (
	OrderID INT(32) NOT NULL,
    ProductID INT(32) NOT NULL,
	FOREIGN KEY fk_OrderID(OrderID)
    REFERENCES Orders(OrderID),
    FOREIGN KEY fk_ProductID(ProductID)
    REFERENCES Product(ProductID)
);


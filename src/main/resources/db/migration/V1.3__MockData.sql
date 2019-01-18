--Customer Table
INSERT INTO CUSTOMERS(CUSTOMER_ID, FIRST_NAME, LAST_NAME, USER_NAME)
	VALUES(1, 'ALEX', 'DANIEL', 'ALEXDANIEL');
INSERT INTO CUSTOMERS(CUSTOMER_ID, FIRST_NAME, LAST_NAME, USER_NAME)
	VALUES(2, 'TEST', 'TEST', 'test');


--Supplier Table
INSERT INTO SUPPLIER(SUPPLIER_ID, NAME) VALUES(1, 'APPLE');
INSERT INTO SUPPLIER(SUPPLIER_ID, NAME) VALUES(2, 'MICROSOFT');
INSERT INTO SUPPLIER(SUPPLIER_ID, NAME) VALUES(3, 'SAMSUNG');

--Category Table
INSERT INTO PRODUCT_CATEGORY(CATEGORY_ID, DESCRIPTION, NAME) VALUES(1, 'TV', 'TV');
INSERT INTO PRODUCT_CATEGORY(CATEGORY_ID, DESCRIPTION, NAME) VALUES(2, 'PHONE', 'PHONE');
INSERT INTO PRODUCT_CATEGORY(CATEGORY_ID, DESCRIPTION, NAME) VALUES(3, 'PC', 'PC');
INSERT INTO PRODUCT_CATEGORY(CATEGORY_ID, DESCRIPTION, NAME) VALUES(4, 'LAPTOP', 'LAPTOP');

--Product Table
INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(1, 'Latest Phone Apple', 'Iphone X', 799, 1, 2, 1);
INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(2, 'Latest Phone Microsoft', 'Nokia 9', 499, 1, 2, 2);
INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(3, 'Latest Phone Samsung', 'Samsung Note 9', 599, 1, 2, 3);


INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(4, 'Latest TV Apple', 'APPLE TV', 1099, 5, 1, 1);
INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(5, 'Latest TV Microsoft', 'TV MSFT', 999, 5, 1, 2);
INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(6, 'Latest TV Samsung', 'SMART TV', 700, 6, 1, 3);


INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(7, 'Latest PC Apple', 'MACBOOK PRO 2019', 1799, 1, 3, 1);
INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(8, 'Latest PC Microsoft', 'SURFACE PRO', 1499, 1, 3, 2);
INSERT INTO PRODUCT(PRODUCT_ID, DESCRIPTION, NAME, PRICE, WEIGHT, CATEGORY_ID, SUPPLIER_ID)
	VALUES(9, 'Latest PC Samsung', 'SAMSUNG PC', 1599, 1, 3, 3);


--Location Table
INSERT INTO LOCATION(LOCATION_ID, CITY, COUNTRY, COUNTY, STREET_ADDRESS, NAME)
	VALUES(1, 'CLUJ-NAPOCA', 'ROMANIA', 'CLUJ', 'Brassai', 'APPLE CLUJ');

INSERT INTO LOCATION(LOCATION_ID, CITY, COUNTRY, COUNTY, STREET_ADDRESS, NAME)
	VALUES(2, 'CLUJ-NAPOCA', 'ROMANIA', 'CLUJ', 'Brassai', 'MICROSOFT CLUJ');

INSERT INTO LOCATION(LOCATION_ID, CITY, COUNTRY, COUNTY, STREET_ADDRESS, NAME)
	VALUES(3, 'CLUJ-NAPOCA', 'ROMANIA', 'CLUJ', 'Brassai', 'SAMSUNG CLUJ');

--Stock Table
INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 1, 1);
INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 1, 4);
INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 1, 7);


INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 2, 2);
INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 2, 5);
INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 2, 8);


INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 3, 3);
INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 3, 6);
INSERT INTO STOCK(QUANTITY, LOCATION_ID, PRODUCT_ID)
	VALUES(100, 3, 9);




/*
 * Copyright (c) 2016 SnappyData, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */
package io.snappydata.hydra

import io.snappydata.SnappyFunSuite

object NWQueries extends SnappyFunSuite {

  val Q1: String = "SELECT * FROM Categories"
  val Q2: String = "SELECT * FROM Customers"
  val Q3: String = "SELECT * FROM Orders"
  // SELECTing Specific Columns
  val Q4: String = "SELECT FirstName, LastName FROM Employees"
  // Sorting By Multiple Columns
  val Q5: String = "SELECT FirstName, LastName" +
    " FROM Employees" +
    " ORDER BY LastName"

  // Sorting By Column Position
  val Q6: String = "SELECT Title, FirstName, LastName" +
    " FROM Employees" +
    " ORDER BY 1,3"

  // Ascending and Descending Sorts
  val Q7: String = "SELECT Title, FirstName, LastName" +
    " FROM Employees " +
    " ORDER BY Title ASC, LastName DESC"

  // Checking for Equality
  val Q8: String = "SELECT Title, FirstName, LastName" +
    " FROM Employees " +
    " WHERE Title = 'Sales Representative'"

  // Checking for Inequality
  val Q9: String = "SELECT FirstName, LastName" +
    " FROM Employees" +
    " WHERE Title <> 'Sales Representative'"

  // Checking for Greater or Less Than
  val Q10: String = "SELECT FirstName, LastName" +
    " FROM Employees " +
    " WHERE LastName >= 'N'"

  // Checking for NULL
  val Q11: String = "SELECT FirstName, LastName" +
    " FROM Employees " +
    " WHERE Region IS NULL"

  // WHERE and ORDER BY
  val Q12: String = "SELECT FirstName, LastName" +
    " FROM Employees" +
    " WHERE LastName >= 'N'" +
    " ORDER BY LastName DESC"

  // Using the WHERE clause to check for equality or inequality
  val Q13: String = "SELECT OrderDate, ShippedDate, CustomerID, Freight" +
    " FROM Orders " +
    " WHERE OrderDate = '19-May-1997'"

  // Using WHERE and ORDER BY Together
  val Q14: String = "SELECT CompanyName, ContactName, Fax" +
    " FROM Customers" +
    " WHERE Fax IS NOT NULL" +
    " ORDER BY CompanyName"

  // The IN Operator
  val Q15: String = "SELECT TitleOfCourtesy, FirstName, LastName" +
    " FROM Employees" +
    " WHERE TitleOfCourtesy IN ('Ms.','Mrs.')"

  // The LIKE Operator
  val Q16: String = "SELECT TitleOfCourtesy, FirstName, LastName" +
    " FROM Employees" +
    " WHERE TitleOfCourtesy LIKE 'M%'"

  val Q17: String = "SELECT FirstName, LastName, BirthDate" +
    " FROM Employees" +
    " WHERE BirthDate BETWEEN '1950-01-01' AND '1959-12-31 23:59:59'"

  val Q18: String = "SELECT CONCAT(FirstName, ' ', LastName)" +
    " FROM Employees"

  val Q19: String = "SELECT OrderID, Freight, Freight * 1.1 AS FreightTotal" +
    " FROM Orders" +
    " WHERE Freight >= 500"

  val Q20: String = "SELECT SUM(Quantity) AS TotalUnits" +
    " FROM Order_Details" +
    " WHERE ProductID=3"

  val Q21: String = "SELECT MIN(HireDate) AS FirstHireDate," +
    " MAX(HireDate) AS LastHireDate" +
    " FROM Employees"

  val Q22: String = "SELECT City, COUNT(EmployeeID) AS NumEmployees" +
    " FROM Employees " +
    " WHERE Title = 'Sales Representative'" +
    " GROUP BY City" +
    " HAVING COUNT(EmployeeID) > 1" +
    " ORDER BY NumEmployees"

  val Q23: String = "SELECT COUNT(DISTINCT City) AS NumCities" +
    " FROM Employees"

  val Q24: String = "SELECT ProductID, AVG(UnitPrice) AS AveragePrice" +
    " FROM Products " +
    " GROUP BY ProductID " +
    " HAVING AVG(UnitPrice) > 70" +
    " ORDER BY AveragePrice"

  val Q25: String = "SELECT CompanyName FROM Customers WHERE CustomerID = " +
    "(SELECT CustomerID FROM Orders WHERE OrderID = 10290)"

  val Q26: String = "SELECT CompanyName FROM Customers  WHERE CustomerID IN (SELECT CustomerID " +
    "FROM Orders WHERE OrderDate BETWEEN '1-Jan-1997' AND '31-Dec-1997')"

  val Q27: String = "SELECT ProductName, SupplierID FROM Products WHERE SupplierID" +
    " IN (SELECT SupplierID FROM Suppliers WHERE CompanyName IN" +
    "('Exotic Liquids', 'Grandma Kelly''s Homestead', 'Tokyo Traders'))"

  val Q28: String = "SELECT ProductName FROM Products WHERE CategoryID = (SELECT " +
    "CategoryID FROM Categories WHERE CategoryName = 'Seafood')"

  val Q29: String = "SELECT CompanyName  FROM Suppliers WHERE SupplierID IN " +
    "(SELECT SupplierID FROM Products WHERE CategoryID = 8)"

  val Q30: String = "SELECT CompanyName  FROM Suppliers WHERE SupplierID IN (SELECT SupplierID" +
    " FROM Products  WHERE CategoryID = (SELECT CategoryID FROM Categories" +
    " WHERE CategoryName = 'Seafood'))"

  val Q31: String = "SELECT Employees.EmployeeID, Employees.FirstName," +
    " Employees.LastName, Orders.OrderID, Orders.OrderDate" +
    " FROM Employees JOIN Orders ON" +
    " (Employees.EmployeeID = Orders.EmployeeID)" +
    " ORDER BY Orders.OrderDate"

  val Q32: String = "SELECT o.OrderID, c.CompanyName, e.FirstName, e.LastName" +
    " FROM Orders o" +
    " JOIN Employees e ON (e.EmployeeID = o.EmployeeID)" +
    " JOIN Customers c ON (c.CustomerID = o.CustomerID)" +
    " WHERE o.ShippedDate > o.RequiredDate AND o.OrderDate > '1-Jan-1998'" +
    " ORDER BY c.CompanyName"

  val Q33: String = "SELECT e.FirstName, e.LastName, o.OrderID" +
    " FROM Employees e JOIN Orders o ON" +
    " (e.EmployeeID = o.EmployeeID)" +
    " WHERE o.RequiredDate < o.ShippedDate" +
    " ORDER BY e.LastName, e.FirstName"

  val Q34: String = "SELECT p.ProductName, SUM(od.Quantity) AS TotalUnits" +
    " FROM Order_Details od JOIN Products p ON" +
    " (p.ProductID = od.ProductID)" +
    " GROUP BY p.ProductName" +
    " HAVING SUM(Quantity) < 200"

  val Q35: String = "SELECT COUNT(DISTINCT e.EmployeeID) AS numEmployees," +
    " COUNT(DISTINCT c.CustomerID) AS numCompanies," +
    " e.City, c.City" +
    " FROM Employees e JOIN Customers c ON" +
    " (e.City = c.City)" +
    " GROUP BY e.City, c.City " +
    " ORDER BY numEmployees DESC"

  val Q36: String = "SELECT COUNT(DISTINCT e.EmployeeID) AS numEmployees," +
    " COUNT(DISTINCT c.CustomerID) AS numCompanies," +
    " e.City, c.City" +
    " FROM Employees e LEFT JOIN Customers c ON" +
    " (e.City = c.City) " +
    " GROUP BY e.City, c.City " +
    " ORDER BY numEmployees DESC"

  val Q37: String = "SELECT COUNT(DISTINCT e.EmployeeID) AS numEmployees," +
    " COUNT(DISTINCT c.CustomerID) AS numCompanies," +
    " e.City, c.City " +
    " FROM Employees e RIGHT JOIN Customers c ON" +
    " (e.City = c.City) " +
    " GROUP BY e.City, c.City" +
    " ORDER BY numEmployees DESC"

  val Q38: String = "SELECT COUNT(DISTINCT e.EmployeeID) AS numEmployees," +
    " COUNT(DISTINCT c.CustomerID) AS numCompanies," +
    " e.City, c.City" +
    " FROM Employees e FULL JOIN Customers c ON" +
    " (e.City = c.City) " +
    " GROUP BY e.City, c.City " +
    " ORDER BY numEmployees DESC"

  val Q39: String = "select s.supplierid,s.companyname,p.productid,p.productname " +
    "from suppliers s join products p on(s.supplierid= p.supplierid) and" +
    " s.companyname IN('Grandma Kelly''s Homestead','Tokyo Traders','Exotic Liquids')"

  val Q40: String = "SELECT c.customerID, o.orderID FROM customers c INNER JOIN orders o " +
    "ON c.CustomerID = o.CustomerID"

  val Q41: String = "SELECT order_details.OrderID,ShipCountry,UnitPrice,Quantity,Discount" +
    " FROM orders INNER JOIN Order_Details ON Orders.OrderID = Order_Details.OrderID"

  val Q42: String = "SELECT ShipCountry," +
    " Sum(Order_Details.UnitPrice * Quantity * Discount)" +
    " AS ProductSales FROM Orders INNER JOIN Order_Details ON" +
    " Orders.OrderID = Order_Details.OrderID GROUP BY ShipCountry"

  val Q43: String = "SELECT * FROM orders LEFT SEMI JOIN order_details " +
    "ON orders.OrderID = order_details.OrderId"

  val Q44: String = "SELECT * FROM orders LEFT SEMI JOIN order_details"

  val Q45: String = "SELECT * FROM orders JOIN order_details"
  val Q46: String = "SELECT * FROM orders LEFT JOIN order_details"
  val Q47: String = "SELECT * FROM orders RIGHT JOIN order_details"
  val Q48: String = "SELECT * FROM orders FULL OUTER JOIN order_details"
  val Q49: String = "SELECT * FROM orders FULL JOIN order_details"

  val Q50: String = "SELECT * FROM orders JOIN order_details" +
    " ON Orders.OrderID = Order_Details.OrderID"
  val Q51: String = "SELECT * FROM orders LEFT JOIN order_details" +
    " ON Orders.OrderID = Order_Details.OrderID"
  val Q52: String = "SELECT * FROM orders RIGHT JOIN order_details" +
    " ON Orders.OrderID = Order_Details.OrderID"
  val Q53: String = "SELECT * FROM orders FULL OUTER JOIN order_details" +
    " ON Orders.OrderID = Order_Details.OrderID"
  val Q54: String = "SELECT * FROM orders FULL JOIN order_details" +
    " ON Orders.OrderID = Order_Details.OrderID"

  val queries = Map(
    "Q1" -> Q1,
    "Q2" -> Q2,
    "Q3" -> Q3,
    "Q4" -> Q4,
    "Q5" -> Q5,
    "Q6" -> Q6,
    "Q7" -> Q7,
    "Q8" -> Q8,
    "Q9" -> Q9,
    "Q10" -> Q10,
    "Q11" -> Q11,
    "Q12" -> Q12,
    "Q13" -> Q13,
    "Q14" -> Q14,
    "Q15" -> Q15,
    "Q16" -> Q16,
    "Q17" -> Q17,
    "Q18" -> Q18,
    "Q19" -> Q19,
    "Q20" -> Q20,
    "Q21" -> Q21,
    "Q22" -> Q22,
    "Q23" -> Q23,
    "Q24" -> Q24,
    "Q25" -> Q25,
    "Q26" -> Q26,
    "Q27" -> Q27,
    "Q28" -> Q28,
    "Q29" -> Q29,
    "Q30" -> Q30,
    "Q31" -> Q31,
    "Q32" -> Q32,
    "Q33" -> Q33,
    "Q34" -> Q34,
    "Q35" -> Q35,
    "Q36" -> Q36,
    "Q37" -> Q37,
    "Q38" -> Q38,
    "Q39" -> Q39,
    "Q40" -> Q40,
    "Q41" -> Q41,
    "Q42" -> Q42,
    "Q43" -> Q43,
    "Q44" -> Q44,
    "Q45" -> Q45,
    "Q46" -> Q46,
    "Q47" -> Q47,
    "Q48" -> Q48,
    "Q49" -> Q49,
    "Q50" -> Q50,
    "Q51" -> Q51,
    "Q52" -> Q52,
    "Q53" -> Q53,
    "Q54" -> Q54
  )

  val regions = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/regions.csv").getPath)
  val regions_table = "create table regions (" +
    "RegionID int, " +
    "RegionDescription string)"

  val categories = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/categories.csv").getPath)
  val categories_table = "create table categories (" +
    "CategoryID int, " +
    "CategoryName string, " +
    "Description string, " +
    "Picture blob)"

  val shippers = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/shippers.csv").getPath)
  val shippers_table = "create table shippers (" +
    "ShipperID int not null, " +
    "CompanyName string not null, " +
    "Phone string)"

  val employees = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/employees.csv").getPath)
  val employees_table = "create table employees(" +
    //    "EmployeeID int not null , " +
    //    "LastName string not null, " +
    //    "FirstName string not null, " +
    "EmployeeID int, " +
    "LastName string,  " +
    "FirstName string, " +
    "Title string, " +
    "TitleOfCourtesy string, " +
    "BirthDate timestamp, " +
    "HireDate timestamp, " +
    "Address string, " +
    "City string, " +
    "Region string, " +
    "PostalCode string, " +
    "Country string, " +
    "HomePhone string, " +
    "Extension string, " +
    "Photo blob, " +
    "Notes string, " +
    "ReportsTo int, " +
    "PhotoPath string)"

  val customers = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/customers.csv").getPath)
  val customers_table = "create table customers(" +
    //    "CustomerID string not null, " +
    //    "CompanyName string not null, " +
    "CustomerID string, " +
    "CompanyName string, " +
    "ContactName string, " +
    "ContactTitle string, " +
    "Address string, " +
    "City string, " +
    "Region string, " +
    "PostalCode string, " +
    "Country string, " +
    "Phone string, " +
    "Fax string)"

  val orders = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/orders.csv").getPath)
  val orders_table = "create table orders (" +
    //"OrderID int not null, " +
    "OrderID int, " +
    "CustomerID string, " +
    "EmployeeID int, " +
    "OrderDate timestamp, " +
    "RequiredDate timestamp, " +
    "ShippedDate timestamp, " +
    "ShipVia int, " +
    "Freight double, " +
    "ShipName string, " +
    "ShipAddress string, " +
    "ShipCity string, " +
    "ShipRegion string, " +
    "ShipPostalCode string, " +
    "ShipCountry string)"

  val order_details = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/order-details.csv").getPath)
  val order_details_table = "create table order_details (" +
    //    "OrderID int not null, " +
    //    "ProductID int not null, " +
    //    "UnitPrice double not null, " +
    //    "Quantity smallint not null, " +
    //    "Discount double not null)"
    "OrderID int, " +
    "ProductID int, " +
    "UnitPrice double, " +
    "Quantity smallint, " +
    "Discount double)"

  val products = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/products.csv").getPath)
  val products_table = "create table products(" +
    //"ProductID int not null, " +
    "ProductID int, " +
    "ProductName string, " +
    //    "SupplierID int not null, " +
    //    "CategoryID int not null," +
    "SupplierID int, " +
    "CategoryID int," +
    "QuantityPerUnit string, " +
    "UnitPrice double, " +
    "UnitsInStock smallint, " +
    "UnitsOnOrder smallint," +
    "ReorderLevel smallint, " +
    "Discontinued smallint) "

  val suppliers = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/suppliers.csv").getPath)
  val suppliers_table = "create table suppliers(" +
    //    "SupplierID int not null, " +
    //    "CompanyName string not null, " +
    "SupplierID int, " +
    "CompanyName string, " +
    "ContactName string, " +
    "ContactTitle string, " +
    "Address string, " +
    "City string, " +
    "Region string, " +
    "PostalCode string, " +
    "Country string, " +
    "Phone string, " +
    "Fax string, " +
    "HomePage string) "

  val territories = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/territories.csv").getPath)
  val territories_table = "create table territories(" +
    //    "TerritoryID string not null, " +
    //    "TerritoryDescription string not null, " +
    //    "RegionID string not null)"
    "TerritoryID string, " +
    "TerritoryDescription string, " +
    "RegionID string)"

  val employee_territories = snc.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .load(getClass.getResource("/northwind/employee-territories.csv").getPath)
  val employee_territories_table = "create table employee_territories(" +
    //    "EmployeeID int not null, " +
    //    "TerritoryID int not null)"
    "EmployeeID int, " +
    //"TerritoryID int)"
    "TerritoryID string)"
}
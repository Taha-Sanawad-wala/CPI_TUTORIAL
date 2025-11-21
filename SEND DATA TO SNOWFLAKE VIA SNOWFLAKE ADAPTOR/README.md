# ❄️ SEND DATA TO SNOWFLAKE VIA SNOWFLAKE ADAPTOR

This guide walks you through creating a Snowflake trial account, setting up a database and table, configuring the Snowflake adaptor in SAP CPI, and sending customer data from the Northwind OData API into Snowflake.

---

## 📝 Prerequisites
- A **Snowflake Trial Account**
- Access to **SAP CPI (Cloud Platform Integration)**
- Northwind OData API endpoint

---

## 🔑 Step 1: Create Snowflake Trial Account
1. Sign up for a **Snowflake Trial**.
2. Log in and click on the **➕ icon** → select **SQL Worksheet**.
<img width="940" height="379" alt="image" src="https://github.com/user-attachments/assets/61e8c248-2ffb-4ede-9c5e-179b2d08002a" />

---

## 🗄️ Step 2: Create Database
Open the SQL Worksheet and run:
```sql
CREATE DATABASE DB_TEST_CPI;
```

<img width="940" height="452" alt="image" src="https://github.com/user-attachments/assets/d7f50a42-78cc-4311-8f3d-c3a7c6240135" />
👉 From the dropdown, select the newly created database DB_TEST_CPI.

---

## 📋 Step 3: Create Table
Run the following query to create the Customers_Northwind table:
```CREATE OR REPLACE TABLE Customers_Northwind (
    CustomerID   VARCHAR(10)   NOT NULL,
    CompanyName  VARCHAR(100),
    ContactName  VARCHAR(100),
    ContactTitle VARCHAR(100),
    Address      VARCHAR(200),
    City         VARCHAR(100),
    Region       VARCHAR(100),
    PostalCode   VARCHAR(20),
    Country      VARCHAR(100),
    Phone        VARCHAR(50),
    Fax          VARCHAR(50)
);
```

<img width="940" height="452" alt="image" src="https://github.com/user-attachments/assets/1861d10c-6f8c-4519-a1af-1425f5c24106" />


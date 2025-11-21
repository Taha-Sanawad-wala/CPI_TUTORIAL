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


<img width="940" height="452" alt="image" src="https://github.com/user-attachments/assets/144e8cc2-dd32-4090-b571-905f4fab4842" />
👉 From the dropdown, select the newly created database DB_TEST_CPI.

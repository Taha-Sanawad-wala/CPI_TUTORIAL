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

---

## 🔌 Step 4: Connect a Tool to Snowflake
Click on the bottom-left icon in Snowflake and select Connect a tool to Snowflake.
<img width="940" height="450" alt="image" src="https://github.com/user-attachments/assets/d9af3c16-6e1c-416a-95ac-72872f6ef4a8" />

---

## ⚙️ Step 5: Configure Snowflake Adaptor in CPI
Use the following configuration details:
```account   = <account>
user      = <your snowflake account username>
password  = <your snowflake account password>
warehouse = "COMPUTE_WH"
database  = "DB_TEST_CPI"
schema    = "PUBLIC"
address   = jdbc:snowflake://<account>.snowflakecomputing.com
```

<img width="940" height="451" alt="image" src="https://github.com/user-attachments/assets/0662dc6c-03cb-48cc-8e04-17eac5493905" />

---

## 🔐 Step 6: Create Security Material in CPI
1. Go to Monitor tab → Security Material.
<img width="940" height="287" alt="image" src="https://github.com/user-attachments/assets/6bbc5552-9c4c-4cf0-8f29-ec0f29dde8a0" />
2. Create User Credentials with your Snowflake username and password.
3. Click Deploy.
<img width="940" height="363" alt="image" src="https://github.com/user-attachments/assets/9d83e308-5bed-4fe9-b270-0890953f4e7f" />

---

🛠️ Step 7: Configure I-Flow
1. Go to the deployed I-Flow.
2. Enter the Snowflake credentials created earlier.
<img width="940" height="445" alt="image" src="https://github.com/user-attachments/assets/635222d8-3609-4e6b-85a7-ce0609164d71" />
3. In the Processing Tab, set:
```Operation = Insert
Table = Customers_Northwind
```
<img width="940" height="428" alt="image" src="https://github.com/user-attachments/assets/e4758c34-b204-4b54-bef3-0a8df858e275" />

---

## 🚀 Step 8: Deploy I-Flow
a. Deploy the I-Flow.

b. Since we are using a timer, it will start immediately after deployment.
Steps performed:
1. Fetch customer data from Northwind OData API.
2. Use a Groovy script to transform data into Snowflake-compatible format.
3. Use another Groovy script to log the data being sent.
4. Send the data to Snowflake via the Snowflake adaptor.
<img width="940" height="528" alt="image" src="https://github.com/user-attachments/assets/e8cda9ee-e8e0-4e75-9e42-3907e61b3ac5" />
5. Data Sent to Snowflake → Transformed customer records from Northwind API
<img width="940" height="485" alt="image" src="https://github.com/user-attachments/assets/1e061b22-a3d3-4429-8fca-55b5b08460e8" />
6. Data Inserted in Snowflake → Stored in Customers_Northwind table
<img width="940" height="444" alt="image" src="https://github.com/user-attachments/assets/d5f2b82c-4ef4-4be4-af02-5100e2883b65" />

---

## ✅ Outcome
You now have a working integration where Northwind customer data flows into Snowflake automatically via CPI.

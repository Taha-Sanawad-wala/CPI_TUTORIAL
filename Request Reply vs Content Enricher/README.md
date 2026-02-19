# CPI Tutorial – Request Reply & Content Enricher

## 📖 Introduction
This tutorial demonstrates how to design and implement different integration patterns in **SAP Cloud Platform Integration (CPI)**.  
The focus is on:
- **Request Reply**  
- **Content Enricher (Combine method)**  
- **Content Enricher (Enrich/Aggregation method)**  

By the end, you’ll understand how routing, enrichment, and request-reply behave in CPI, and why enrichment is critical to avoid data loss.

---

## 🔄 Flow Overview
The CPI flow is structured as follows:

<img width="1531" height="683" alt="image" src="https://github.com/user-attachments/assets/c2a498fb-09c4-449a-9f9f-11ede0460aea" />


1. **Receiver (HTTPS)**  
   - Accepts incoming requests via HTTPS.

2. **Router**  
   - Routes messages based on a header property.  
   - Directs the request to one of three local integration processes.

3. **Local Integration Processes**
   - **Content Enricher (Combine method)**  
     - Combines payloads from multiple sources into a single enriched message.  
   - **Content Enricher (Enrich/Aggregation method)**  
     - Aggregates additional data into the original payload.  
   - **Request Reply**  
     - Calls an external service directly.  
     - Demonstrates how the original payload is lost if enrichment is not used.

---

## 🗂 OData Services Used
Two OData services are used to simulate real-world scenarios:

| OData Service | Purpose | Example Data |
|---------------|---------|--------------|
| **Orders Service** | Retrieves order details | Order ID, Product, Quantity |
| **Customer Service** | Retrieves customer details | Customer ID, Name, Address |

---

## 📂 Example Payloads

### Enriched Payload (Combine Method)
```xml
<?xml version='1.0' encoding='UTF-8'?><multimap:Messages xmlns:multimap="http://sap.com/xi/XI/SplitAndMerge"><multimap:Message1><Orders xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <Order>
      <RequiredDate>1996-08-01T00:00:00Z</RequiredDate>
      <ShipName>Vins et alcools Chevalier</ShipName>
      <ShippedDate>1996-07-16T00:00:00Z</ShippedDate>
      <ShipCity>Reims</ShipCity>
      <CustomerID>VINET</CustomerID>
      <ShipVia>3</ShipVia>
      <ShipPostalCode>51100</ShipPostalCode>
      <OrderID>10248</OrderID>
      <OrderDate>1996-07-04T00:00:00Z</OrderDate>
      <ShipRegion xsi:nil="true"/>
      <ShipAddress>59 rue de l'Abbaye</ShipAddress>
      <ShipCountry>France</ShipCountry>
      <EmployeeID>5</EmployeeID>
      <Freight>32.38</Freight>
    </Order>
  </Orders></multimap:Message1><multimap:Message2>
  <Customers xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <Customer>
      <CompanyName>Vins et alcools Chevalier</CompanyName>
      <Address>59 rue de l'Abbaye</Address>
      <Phone>26.47.15.10</Phone>
      <Region xsi:nil="true"/>
      <PostalCode>51100</PostalCode>
      <Country>France</Country>
      <CustomerID>VINET</CustomerID>
      <City>Reims</City>
      <Fax>26.47.15.11</Fax>
      <ContactName>Paul Henriot</ContactName>
      <ContactTitle>Accounting Manager</ContactTitle>
    </Customer>
  </Customers>
</multimap:Message2></multimap:Messages>
```

### Enriched Payload (Enrich Method)
```xml
<Orders>
    <Order>
      <RequiredDate>1996-08-01T00:00:00.000</RequiredDate>
      <ShipName>Vins et alcools Chevalier</ShipName>
      <ShippedDate>1996-07-16T00:00:00.000</ShippedDate>
      <ShipCity>Reims</ShipCity>
      <CustomerID>VINET</CustomerID>
   <Customer>
      <CompanyName>Vins et alcools Chevalier</CompanyName>
      <Address>59 rue de l'Abbaye</Address>
      <Phone>26.47.15.10</Phone>
      <Region/>
      <PostalCode>51100</PostalCode>
      <Country>France</Country>
      <CustomerID>VINET</CustomerID>
      <City>Reims</City>
      <Fax>26.47.15.11</Fax>
      <ContactName>Paul Henriot</ContactName>
      <ContactTitle>Accounting Manager</ContactTitle>
   </Customer>
      <ShipVia>3</ShipVia>
      <ShipPostalCode>51100</ShipPostalCode>
      <OrderID>10248</OrderID>
      <OrderDate>1996-07-04T00:00:00.000</OrderDate>
      <ShipRegion/>
      <ShipAddress>59 rue de l'Abbaye</ShipAddress>
      <ShipCountry>France</ShipCountry>
      <EmployeeID>5</EmployeeID>
      <Freight>32.3800</Freight>
    </Order>
</Orders>
```

### Request Reply (Without Enrichment)
```xml
<Customers xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <Customer>
      <CompanyName>Vins et alcools Chevalier</CompanyName>
      <Address>59 rue de l&apos;Abbaye</Address>
      <Phone>26.47.15.10</Phone>
      <Region xsi:nil="true"/>
      <PostalCode>51100</PostalCode>
      <Country>France</Country>
      <CustomerID>VINET</CustomerID>
      <City>Reims</City>
      <Fax>26.47.15.11</Fax>
      <ContactName>Paul Henriot</ContactName>
      <ContactTitle>Accounting Manager</ContactTitle>
    </Customer>
</Customers>
```
*(Notice how the original order details are lost.)*

---

## ⚙️ Step‑by‑Step Execution Guide

### 1. Configure HTTPS Receiver
- Add an **HTTPS sender adapter** to receive incoming requests.  
- Define the endpoint URL for testing.  
- Ensure authentication is set up if required.  
---

### 2. Create Router
- Insert a **Router** step after the receiver.  
- Define routing conditions based on a **header property** (e.g., `RouteType`).  
- Example conditions:  
  - `reqtype = Default` → Content Enricher (Combine)  
  - `reqtype = "enrich"` → Content Enricher (Aggregation)  
  - `reqtype = "reqrply"` → Request Reply  
---

### 3. Local Integration Process – Content Enricher (Combine)
- Add a **Content Enricher** step.  
- Configure the **Combine method**.  
- Call both OData services:  
  - Orders Service → fetch order details.  
  - Customer Service → fetch customer details.  
- Combine the payloads into one unified message.

<img width="916" height="238" alt="image" src="https://github.com/user-attachments/assets/351ec64f-745b-47f9-8d70-0b327013d0cc" />

 
---

### 4. Local Integration Process – Content Enricher (Enrich)
- Add a **Content Enricher** step.  
- Configure the **Enrich (Aggregation) method**.  
- Call the Customer Service.  
- Embed customer details into the existing order payload.

<img width="902" height="231" alt="image" src="https://github.com/user-attachments/assets/fe9c0f75-9dfd-48c4-8079-20bf7950d843" />


---

### 5. Local Integration Process – Request Reply
- Add a **Request Reply** step.  
- Call the Customer Service directly.  
- Observe how the original order payload is replaced by the customer response.  
---

### 6. Testing
- Send requests with different header values (blank,'reqrply','enrich').  
- Compare the output payloads to see how enrichment preserves or merges data, while request-reply alone overwrites it.  

<img width="1393" height="597" alt="image" src="https://github.com/user-attachments/assets/5a2f76bd-e561-4d49-9e8c-1b98fc475a70" />


---

## 📊 Flow Visualization (ASCII Diagram)

```
[HTTPS Receiver]
       |
     [Router]
       |
  -------------------------
  |           |           |
[Combine]   [Aggregation] [Request Reply]
   |            |             |
 Orders +    Orders +      Customer
 Customer    Customer      (Order lost)
```

---

## 🎯 Key Learnings
- **Router** enables conditional routing based on headers.  
- **Content Enricher (Combine)** merges multiple payloads into one.  
- **Content Enricher (Aggregation)** embeds additional data into the existing payload.  
- **Request Reply** alone can overwrite payloads, leading to data loss.  
- Using **OData services** provides a practical way to demonstrate enrichment scenarios.

---
## 👤 **Author**

**Taha Sanawad**\
SAP Integration Consultant

------------------------------------------------------------------------

## 📜 **License**

Open-source for educational use.

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

### Request Payload
```json
{
  "OrderID": "1001"
}
```

### Enriched Payload (Combine Method)
```json
{
  "OrderID": "1001",
  "Product": "Laptop",
  "Quantity": 2,
  "CustomerID": "C001",
  "CustomerName": "John Doe",
  "Address": "New York"
}
```

### Enriched Payload (Aggregation Method)
```json
{
  "OrderID": "1001",
  "Product": "Laptop",
  "Quantity": 2,
  "Customer": {
    "CustomerID": "C001",
    "CustomerName": "John Doe",
    "Address": "New York"
  }
}
```

### Request Reply (Without Enrichment)
```json
{
  "CustomerID": "C001",
  "CustomerName": "John Doe",
  "Address": "New York"
}
```
*(Notice how the original order details are lost.)*

---

## ⚙️ Step‑by‑Step Execution Guide

### 1. Configure HTTPS Receiver
- Add an **HTTPS sender adapter** to receive incoming requests.  
- Define the endpoint URL for testing.  
- Ensure authentication is set up if required.  

📸 *Insert Screenshot: HTTPS Receiver configuration*

---

### 2. Create Router
- Insert a **Router** step after the receiver.  
- Define routing conditions based on a **header property** (e.g., `RouteType`).  
- Example conditions:  
  - `RouteType = "Combine"` → Content Enricher (Combine)  
  - `RouteType = "Aggregation"` → Content Enricher (Aggregation)  
  - `RouteType = "RequestReply"` → Request Reply  

📸 *Insert Screenshot: Router conditions setup*

---

### 3. Local Integration Process – Content Enricher (Combine)
- Add a **Content Enricher** step.  
- Configure the **Combine method**.  
- Call both OData services:  
  - Orders Service → fetch order details.  
  - Customer Service → fetch customer details.  
- Combine the payloads into one unified message.  

📸 *Insert Screenshot: Content Enricher (Combine) configuration*

---

### 4. Local Integration Process – Content Enricher (Aggregation)
- Add a **Content Enricher** step.  
- Configure the **Enrich (Aggregation) method**.  
- Call the Customer Service.  
- Embed customer details into the existing order payload.  

📸 *Insert Screenshot: Content Enricher (Aggregation) configuration*

---

### 5. Local Integration Process – Request Reply
- Add a **Request Reply** step.  
- Call the Customer Service directly.  
- Observe how the original order payload is replaced by the customer response.  

📸 *Insert Screenshot: Request Reply configuration*

---

### 6. Testing
- Send requests with different header values (`Combine`, `Aggregation`, `RequestReply`).  
- Compare the output payloads to see how enrichment preserves or merges data, while request-reply alone overwrites it.  

📸 *Insert Screenshot: Example test payloads and responses*

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

👉 With these placeholders, you can now insert screenshots of your iFlow at each step. That way, learners will not only read the explanation but also visually connect it to the CPI interface.  

Would you like me to also create a **sample test case table** (input headers vs. expected output payloads) so readers can quickly validate their setup?

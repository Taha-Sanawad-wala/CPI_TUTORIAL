# **README -- General Splitter vs Iterating Splitter (SAP CPI)**

## 📌 **Overview**

This repository contains an SAP Cloud Platform Integration (CPI) iFlow
designed to compare the behavior of **General Splitter** and **Iterating
Splitter**.\
The iFlow demonstrates how CPI processes split messages differently
under each splitter mechanism and how Groovy can be used to log that output as attachments.

The goal is to provide a clear, hands-on example that can be used for
learning, demonstrations, and interviews.

------------------------------------------------------------------------

## 📁 **Repository Structure**

    / (root)
    │-- iflow/
    │     └── General_vs_Iterating_Splitter.zip
    │
    │-- scripts/
    │     └── Logging.groovy
    │
    │-- samples/
    │     └── sample.json
    │
    └── README.md

------------------------------------------------------------------------

## 🚀 **What This iFlow Demonstrates**

### **1️⃣ HTTP-Based Input**

The flow begins with an **HTTP Sender adapter**, allowing you to POST
any JSON/XML payload.

### **2️⃣ JSON → XML Conversion**

If JSON is sent, CPI automatically converts it to XML using the **JSON
to XML Converter**.

### **3️⃣ Dynamic Routing Based on Header**

You choose the splitter through an HTTP request header:

  -----------------------------------------------------------------------
  Header                Value                 Behavior
  --------------------- --------------------- ---------------------------
  `splitterType`        `gen`             Routes message to General
                                              Splitter branch

  `splitterType`        `itr`           Routes message to Iterating
                                              Splitter branch
  -----------------------------------------------------------------------

### **4️⃣ General Splitter Branch**

-   Splits XML elements\
-   Processes all fragments\
-   create splitted fragements as attachement\

### **5️⃣ Iterating Splitter Branch**

-   Processes each fragment **one-by-one**\
-   Each fragment becomes an independent message\
-   Uses **Groovy script to log all outputs**

------------------------------------------------------------------------


## 🧪 **How to Test Using Postman**

### **URL**

    POST https://<your-cpi-tenant>/http/splitterDem

### **Headers**

    Content-Type: application/json
    splitterType: gen /itr

------------------------------------------------------------------------

## 📄 **High-Level Flow**

    HTTP Sender
         ↓
    JSON → XML Converter
         ↓
         Router (splitterType)
           ├── general → General Splitter → Groovy → End
           └── iterating → Iterating Splitter → Groovy → End

------------------------------------------------------------------------

## 👤 **Author**

**Taha Sanawad**\
SAP Integration Consultant

------------------------------------------------------------------------

## 📜 **License**

Open-source for educational use.

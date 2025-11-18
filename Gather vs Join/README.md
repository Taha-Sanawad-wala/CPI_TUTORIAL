# **README -- Gather vs Join (SAP CPI)**

## 📌 **Overview**
This repository showcases an SAP CPI Integration Flow (iFlow) that demonstrates the difference between Gather and Join steps. It provides a hands‑on example of how payloads behave when processed through different routing and aggregation strategies.

## 🚀 **Overview**
The iFlow starts with an HTTPS Adapter request and flows through multiple steps to illustrate how Gather and Join work:

🔗 HTTPS Adapter – Entry point for the request.

📝 Content Modifier – Declares a number range in the exchange property for later use.

🔄 JSON → XML Converter – Converts incoming JSON (GSYNC format) into XML.

🛣️ Router – Routes messages based on header conditions into three different paths:

Route 1: Splitter Path

Route 2: Join Path

Route 3: Gather Path

## 🛠️ **Flow Details**
🔹 Route 1: Splitter Path
Calls a Local Integration Process.

Steps:

General Splitter

Groovy Script → attaches payload as an attachment

🔹 Route 2: Join Path
Condition: type = join

Calls a Local Subprocess with:

Parallel Multicast (2 branches)

Branch 1 → Empty

Branch 2 → Content Modifier (dummy data + number range)

Join → synchronizes branches

Gather → aggregates data

Groovy Script → attaches payload

🔹 Route 3: Gather Path
Condition: type = gather

Calls a Local Integration Subprocess with:

General Splitter

XML Modifier → removes XML declaration

Gather → aggregates all split data

Groovy Script → attaches payload

## 📊 **Difference Between Gather vs Join**
Feature	Gather	Join
Purpose	Aggregates multiple messages into one	Synchronizes multiple branches in parallel multicast
Usage	After Splitter or multiple messages	After Parallel Multicast
Result	Single aggregated payload	Single payload (includes hardcoded entry if modifier used)
Without Gather	Multiple attachments (one per record)	Not applicable
## 🖼️ **iFlow Image**
<img width="1833" height="691" alt="image" src="https://github.com/user-attachments/assets/e4f38017-d123-40a7-a2ae-779e467b506f" />

## 📚 **Key Learnings**
Gather is best used when you need to aggregate split messages into one.

Join is used when you have parallel branches and want to synchronize them.

Using Groovy scripts to attach payloads makes the difference between Gather and Join visible and testable.

## 🧑‍💻 **How to Use**
Import the iFlow into your SAP CPI tenant.

Deploy and trigger via HTTPS Adapter.

Experiment with different type values (splitter, join, gather) in the header.

Observe payload attachments to understand the behavior.

## 🎯 **Conclusion**
This repo provides a clear, practical demonstration of how Gather and Join differ in SAP CPI. By experimenting with the provided iFlow, you’ll gain a deeper understanding of message aggregation and synchronisation patterns.

## 👤 **Author**

**Taha Sanawad**\
SAP Integration Consultant

------------------------------------------------------------------------

## 📜 **License**

Open-source for educational use.

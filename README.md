# CPI_TUTORIAL
integration suite examples

## 📘 SAP CPI – Hands-On Examples & Tutorials
**Welcome to the SAP Cloud Platform Integration (CPI) examples repository!**

This repository contains multiple ready-to-use integration flows, Groovy scripts, payload samples, and scenario-based demos designed to help learners and consultants practice CPI hands-on.
Each example is packaged as an iFlow ZIP so you can easily download, import, configure, and deploy it in your own CPI tenant.

🚀 Getting Started
Follow these simple steps to use any iFlow from this repository.

📥 1. Download the iFlow Package
Navigate to the folder containing the desired example.
Locate the .zip file.
Download the file using GitHub’s Download raw file option.

📤 2. Import the iFlow into SAP CPI
Log in to your SAP Cloud Integration tenant.
Go to Design → Integration Packages.
Create a new package or open an existing one.
Click Artifacts → Add → Integration Flow.
Select Upload and choose the downloaded .zip.

🛠️ 3. Open & Review the iFlow
Once imported:
Review adapters, Groovy scripts, mappings, and flow logic.
Update any required external parameters such as:
Endpoints
Credentials
Security material
Sender/receiver configurations

▶️ 4. Deploy the iFlow
Open the iFlow.
Click Deploy.
Monitor the deployment under Monitor → Manage Integration Content.
Wait until the status shows Started.

🧪 5. Test the iFlow
Depending on the example, you can test it using:
Postman / ARC (HTTP flows)
SOAP UI (SOAP flows)
Timer (scheduled flows)
SFTP/OData/Other adapters (based on scenario)
Sample JSON/XML payloads are included in each example folder.

📂 Repository Structure

/Example1-SplitterComparison

|-- SplitterComparison.zip
  
|-- sample-input.json
 
/Example2-HTTPDemo

|-- HTTPDemo.zip
  
|-- sample-payload.json
 
/Example3-GroovyScripts

|-- UtilityScript1.groovy
    
|-- UtilityScript2.groovy
    
🎯 Purpose of This Repository

Help beginners understand CPI with practical, real-world examples

Provide ready-to-run iFlows for quick testing and PoCs

Share reusable Groovy functions, adapter configurations, and patterns

Demonstrate common scenarios: splitters, routing, exception handling, and more

⭐ Support
If you find this repo helpful, consider starring ⭐ it on GitHub to help others discover it.

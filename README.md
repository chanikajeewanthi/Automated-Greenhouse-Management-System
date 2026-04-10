# 🌱 Automated Greenhouse Management System (AGMS)

## 📌 Project Overview

The **Automated Greenhouse Management System (AGMS)** is a cloud-native, microservice-based application designed to automate greenhouse monitoring and environmental control using real-time IoT data.

This system replaces manual monitoring with an intelligent architecture that:

* Collects live temperature and humidity data from an external IoT API
* Processes the data using a rule engine
* Automatically triggers actions to maintain optimal plant conditions

---

## 🏗️ System Architecture

This project follows a **Microservices Architecture** with a **Polyglot approach**, meaning different technologies are used for different services.

### 🔧 Infrastructure Services (Spring Cloud)

* **Config Server** – Centralized configuration management
* **Eureka Server** – Service discovery and registration
* **API Gateway** – Single entry point with routing and JWT security

---

### ⚙️ Domain Microservices

#### 🌿 Zone Service (Spring Boot)

* Manages greenhouse zones
* Stores temperature thresholds (minTemp, maxTemp)
* Integrates with external IoT API to register devices

#### 📡 Sensor Service (Python - Flask)

* Fetches real-time telemetry data from external IoT API
* Runs every 10 seconds using a scheduler
* Sends data to Automation Service

#### 🤖 Automation Service (Node.js)

* Acts as the system's rule engine
* Processes incoming sensor data
* Triggers actions such as:

  * TURN_FAN_ON
  * TURN_HEATER_ON
* Stores logs for monitoring

#### 🌱 Crop Service (Node.js)

* Manages crop batches
* Tracks lifecycle stages:

  * SEEDLING
  * VEGETATIVE
  * HARVESTED

---

## 🔄 System Workflow

```text
External IoT API
        ↓
Sensor Service (Python)
        ↓
Automation Service (Node.js)
        ↓
Action Logs Generated
        ↓
Farmer Monitoring via API
```

---

## 🔐 Security

* Implemented at API Gateway level
* Uses JWT-based authentication
* All incoming requests must include:

```text
Authorization: Bearer <token>
```

* Unauthorized requests are rejected with **401 Unauthorized**

---

## 🚀 How to Run the System

### 🔹 Step 1: Start Infrastructure Services

1. Config Server → Port 8888
2. Eureka Server → Port 8761
3. API Gateway → Port 8080

---

### 🔹 Step 2: Start Microservices

4. Zone Service → Port 8081
5. Automation Service → Port 8083
6. Sensor Service → Port 8082
7. Crop Service → Port 8084

---

## 🧪 API Testing

All APIs are tested using **Postman**.

### Included APIs:

* Zone Management APIs
* Sensor API (`/api/sensors/latest`)
* Automation APIs (`/api/automation/process`, `/logs`)
* Crop APIs

👉 The Postman collection is available in:

```text
postman-collection/AGMS.postman_collection.json
```

---

📸 Eureka Dashboard

All services are registered and monitored using Eureka.

👉 Access:

http://localhost:8761

📷 Screenshot included in:

docs/eureka-dashboard.png

## 🧠 Key Features

* Microservices architecture with service isolation
* Real-time IoT data integration
* Automated rule-based decision system
* Centralized configuration management
* Secure API Gateway with JWT authentication
* Multi-language services (Java, Python, Node.js)

---

## 🧾 Technologies Used

| Component         | Technology           |
| ----------------- | -------------------- |
| Backend Framework | Spring Boot          |
| Service Discovery | Eureka               |
| API Gateway       | Spring Cloud Gateway |
| Config Management | Spring Cloud Config  |
| Sensor Service    | Python (Flask)       |
| Automation & Crop | Node.js (Express)    |
| API Testing       | Postman              |

---

## 🏁 Conclusion

AGMS demonstrates how modern microservices architecture can be used to build scalable, maintainable, and intelligent systems.

The integration of real-time IoT data with automated decision-making provides a strong foundation for future smart agriculture solutions.

---

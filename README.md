# 🌱 Automated Greenhouse Management System (AGMS)

## 📌 Overview

AGMS is a microservice-based system that automates greenhouse monitoring using real-time IoT data.
It collects sensor data, processes it, and triggers actions to maintain optimal conditions.

---

## 🏗️ Architecture

### 🔧 Infrastructure

* Config Server (Spring Boot)
* Eureka Server (Service Registry)
* API Gateway (Routing + JWT Security)

### ⚙️ Microservices

* Zone Service (Spring Boot)
* Sensor Service (Python Flask)
* Automation Service (Node.js)
* Crop Service (Node.js)

---

## 🔄 Workflow

```text
Sensor Service → Automation Service → Action Logs
Zone Service → Device Registration → Sensor Data
```

---

## 🚀 How to Run

### Start Infrastructure

1. Config Server (8888)
2. Eureka Server (8761)
3. API Gateway (8080)

### Start Services

4. Zone Service (8081)
5. Automation Service (8083)
6. Sensor Service (8082)
7. Crop Service (8084)

---

## 🧪 Testing

Use **Postman** to test all APIs.
Postman collection included in the repository.

---

## 📸 Screenshot

Eureka Dashboard: `docs/eureka-dashboard.png`

---

## 🧠 Features

* Microservices architecture
* Real-time IoT integration
* Rule-based automation
* JWT secured API Gateway

---

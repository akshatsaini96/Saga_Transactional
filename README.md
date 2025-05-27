# 🧩 Saga Pattern - Order Management Microservice

This project demonstrates a distributed order management system using the **Saga Pattern**, **Kafka**, and **Outbox Pattern**. It includes modular microservices and a shared communication layer for event-driven architecture.

---

## 📦 Modules Overview

### 🔁 `commondto/`
Shared library containing:
- **DTOs:** `OrderRequest`, `OrderResponse`, `PaymentRequestDto`
- **Events:** `OrderEvent`, `PaymentStatus`, `OrderStatus`, `Event`

### 📦 `orderservice/`
Microservice responsible for:
- Receiving order requests
- Saving orders
- Creating outbox events
- Polling and sending Kafka events

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Kafka
- Spring Data JPA
- Maven
- H2/MySQL (pluggable)
- Lombok

---

## 🚀 Flow Summary

1. `OrderController` accepts an API call with `OrderRequest`.
2. The order is saved and an event is inserted into the `Outbox` table.
3. `OrderPollerService` picks the event and publishes it to Kafka.
4. Downstream services (e.g., Payment) listen to Kafka events and respond.
5. Order status is updated based on event responses.

---

## 🧠 Key Concepts

- **Saga Pattern:** To manage distributed transactions
- **Outbox Pattern:** For reliable event publishing
- **Kafka Integration:** Event choreography across services

---

## 📂 Directory Structure


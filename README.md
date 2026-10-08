# 📚 Book Store (Monolith)

A full-stack, monolithic e-commerce application built with Java and Spring Boot. This project provides a complete platform for users to view different books, manage their shopping cart, and seamlessly place orders, all contained within a single, easy-to-deploy backend architecture.

## 🚀 Key Features

* **Book Catalog:** Users can browse, search, and view detailed information about various books, including authors, categories, and pricing.
* **Shopping Cart Management:** Users can add books to their cart, update quantities, and remove items before checkout.
* **Order Processing:** A streamlined checkout process allowing users to place orders, track status, and view their order history.
* **Payment Integration:** Secure payment handling, including payment initiation, transaction verification, and refund processing.
* **Shipping & Fulfillment:** Manage shipping addresses, generate shipments, and provide order tracking.
* **Review System:** Allows users to leave star ratings and detailed written reviews for books they have purchased.
* **User Authentication:** Secure registration, login, and token management using Spring Security and JWT.
* **Inventory Tracking:** Basic stock management, including stock reservation during checkout and automated adjustments.
* **Admin Controls:** Admin dashboard capabilities to manage books, users, inventory, orders, and generate reports.

## 🛠️ Technology Stack

| Layer | Technology |
| :--- | :--- |
| **Language** | Java 17 / 21 |
| **Framework** | Spring Boot 3 |
| **Security** | Spring Security + JWT |
| **Database** | PostgreSQL / MySQL |
| **ORM & Migration** | Spring Data JPA / Hibernate, Flyway |
| **Testing** | JUnit 5, Mockito |
| **Build Tool** | Maven |

## 🏗️ Architecture Overview

The application follows a classic, standard monolithic architecture. All core business logic—including user authentication, catalog management, cart operations, payments, and order processing—runs within a single application process and interacts with a single relational database. 

### Core User Flow
1. **Browse:** User searches or filters the book catalog.
2. **Select & Review:** User views specific book details, reads user reviews, and adds the item to their cart.
3. **Checkout:** User places the order, which reserves stock and calculates the total.
4. **Payment & Shipping:** User completes payment verification, generating a shipment record and tracking number.
5. **Post-Purchase:** User receives the book and submits a rating and review.

## 📂 Project Structure

The codebase is organized into domain-specific packages to keep the monolith clean and maintainable, mapping directly to the core business features:

```text
src/main/java/com/bookstore/
├── auth/          # Registration, login, JWT generation, refresh tokens, and roles
├── user/          # User profiles, address book, and wishlists
├── book/          # Book catalog, authors, categories, publishers, and ISBN details
├── inventory/     # Stock tracking, soft reservations, and stock adjustments
├── cart/          # Cart item additions, removals, and quantity updates
├── order/         # Order creation, line items, status tracking, and order history
├── payment/       # Payment initiation, transaction verification, and refunds
├── shipping/      # Shipping addresses, shipment generation, and tracking status
├── review/        # User ratings and written reviews
├── admin/         # Admin controls for books, users, inventory, orders, and reporting
└── config/        # Global exception handling, configurations, and shared utilities
```

## ⚙️ Local Development Setup

### 1. Prerequisites

Make sure the following are installed on your machine:

- Java 17 or higher
- Maven
- MySQL
- Git

### 2. Clone the Repository

```bash
https://github.com/manasranjanmohanta/book-store.git
cd book-store
```

### 3. Configure the Database

Update the MySQL database configuration in:

```bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bookstore
    username: root
    password: your_password
```

### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

### 5. Access the Application

Once the application starts successfully, the API will be available at:

```bash
http://localhost:8080
```

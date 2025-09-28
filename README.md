# 🛍️ Xisu Fashion Backend

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-orange.svg)
![JWT](https://img.shields.io/badge/JWT-Authentication-red.svg)

> **A secure Spring Boot REST API for fashion e-commerce with JWT authentication and role-based authorization.**

## 🚀 Technologies

- **Spring Boot 3.x** - Main framework
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Data persistence
- **MySQL** - Database
- **JWT** - Token-based authentication
- **Maven** - Build tool

## ✨ Key Features

- ✅ JWT-based authentication with refresh token
- ✅ Role-based authorization (USER/ADMIN)
- ✅ Complete CRUD operations for Users, Categories, Products
- ✅ Password encryption with BCrypt
- ✅ Global exception handling
- ✅ Input validation and sanitization

## 🔧 Quick Start

### 1. Clone & Setup
git clone https://github.com/phvhieeus/XisuFashion.git
cd xisu-fashion-backend

### 2. Database Setup
CREATE DATABASE xisufashion;

### 3. Configure Environment
# Set these environment variables
DB_URL=jdbc:mysql://localhost:3306/xisufashion
DB_USERNAME=root
DB_PASSWORD=your_password
JWT_SIGNER_KEY=your-secret-key
JWT_EXPIRATION=3600

### 4. Run Application
mvn spring-boot:run

Application starts at `http://localhost:8080`

## 📚 API Endpoints

### 🔐 Authentication
- `POST /auth/register` - Register user
- `POST /auth/login` - Login
- `POST /auth/refresh` - Refresh token
- `POST /auth/logout` - Logout

### 👥 Users
- `GET /users` - Get all users (Public)
- `GET /users/{id}` - Get user by ID (Public)
- `PUT /users/{id}` - Update user (Authenticated)
- `DELETE /users/{id}` - Delete user (Admin only)

### 📂 Categories
- `GET /categories` - Get all categories (Public)
- `POST /categories` - Create category (Admin only)
- `PUT /categories/{id}` - Update category (Admin only)
- `DELETE /categories/{id}` - Delete category (Admin only)

### 🛍️ Products
- `GET /products` - Get all products (Public)
- `POST /products` - Create product (Admin only)
- `PUT /products/{id}` - Update product (Admin only)
- `DELETE /products/{id}` - Delete product (Admin only)

## 🔐 Authentication Flow

- **Register/Login** → Get JWT access & refresh tokens
- **API Requests** → Include `Authorization: Bearer {token}`
- **Token Validation** → Extract user role for authorization
- **Refresh Token** → Get new access token when expired

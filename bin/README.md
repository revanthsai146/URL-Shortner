# **URL Shortener**

This project is a URL shortening service designed to generate shortened, unique aliases for long URLs. It provides a web API for shortening URLs, updating them, and redirecting users to the original URLs. URLs generated by this service expire after a default period of 10 months.

## **Features**

- **Shorten URL:** Given a long URL, the service generates a shorter and unique alias.
- **Update URL:** Allows updating the destination URL associated with a short URL.
- **Redirect:** Users are redirected to the original long URL when they visit the short URL.
- **Expiry:** Shortened URLs expire after 10 months by default.
- **High Availability:** The system is designed to be highly available to ensure minimal downtime and reliable URL redirection.
- **Security:** Shortened URLs are designed to be unpredictable to enhance security.

## **API Endpoints**

- **POST /shorten:** Shorten a URL.
- **POST /update:** Update the destination URL for a short URL.
- **GET /{shortUrl}:** Redirect to the original URL associated with the short URL.
- **POST /update_expiry:** Update the expiry duration of a short URL.

## **Technologies Used**

- **Java:** Backend development using Java.
- **Spring Boot:** Framework for creating web applications.
- **MySQL:** Database management system for storing URLs.
- **Apache Commons Lang:** Library for generating random strings.
- **JUnit:** Unit testing framework for Java.

## **Getting Started**

- **Clone the Repository:**
  ```bash
  git clone https://github.com/revanthsai146/URL-Shortner.git
  cd url-shortener

- **Configure Database:**
Set up PostgreSQL and configure database settings in application.properties.

- **Build and Run:**
 mvn spring-boot:run

- **API Usage:**
Use tools like Postman or cURL to interact with the API endpoints.
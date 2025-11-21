Coupon Management System Documentation
Author: Ramesh Vadde
Date: 21-Nov-2025
Version: 1.0
________________________________________
Table of Contents
1.	Introduction
2.	System Architecture
3.	Database Design
4.	Functional Flow
5.	API Documentation
6.	Business Logic
7.	Testing Strategy
8.	Diagrams & Visuals
9.	Deployment & Setup
10.	Future Enhancements
11.	Appendices
________________________________________
Chapter 1: Introduction
1.1 Project Overview
The Coupon Management System is designed to streamline the creation, management, and redemption of coupons for users. It enables efficient tracking, validation, and selection of the best available coupon for customers while providing administrative control over coupon campaigns.
1.2 Goals and Objectives
•	Simplify coupon distribution.
•	Ensure correct eligibility and validation rules.
•	Improve user engagement through automated discount management.
•	Provide administrative insights on coupon usage.
1.3 Scope
•	Functional: Coupon creation, user eligibility checks, cart eligibility checks, best coupon calculation, discount application.
•	Non-functional: logging, scalability, maintainability.
1.4 Target Users
•	Coupon: Create, Update, manage coupons.
•	Customers: Apply eligible coupons to their carts and receive discounts.
________________________________________
Chapter 2: System Architecture
2.1 High-Level Architecture
•	Component-based architecture using Spring Boot microservices.
•	Key modules: Coupon Service.
•	Diagram: System components and interactions (ERD + Component Diagram).
2.2 Technology Stack
•	Backend: Java 17, Spring Boot, Spring Data JPA
•	Database: MySQL / PostgreSQL
•	API Layer: REST APIs with JSON format
•	Tools: Maven, Git, Postman
2.3 Module Descriptions
•	Coupon Service: Manages coupon creation, updates, validation.
•	Cart Service: Calculates discounts and best coupon selection.
________________________________________
Chapter 3: Database Design
3.1 Entity-Relationship Diagram (ERD)
•	Shows relationships between User, Coupon, Cart, Product entities.
3.2 Table Structures
•	Include table names, fields, types, constraints, and indexes.

+-----------+        +---------+       +----------------+
|   User    |1      M|  Cart   |1     M|    Product     |
+-----------+        +---------+       +----------------+
| id        |--------| id      |-------| id             |
| name      |        | total   |       | name           |
| userTier  |        +---------+       | unitPrice      |
| country   |                        | quantity       |
| lifeTimeSpend|                     | category       |
| totalOrders |                     +----------------+
+-----------+
       |
       M
       |
+--------+
| Coupon |
+--------+
| id                  |
| code (unique)       |
| description         |
| discountType        |
| discountValue       |
| maxDiscountAmount   |
| startDate           |
| endDate             |
| usageLimitPerUser   |
| allowedUserTiers    |
| minLifetimeSpend    |
| minOrdersPlaced     |
| firstOrderOnly      |
| allowedCountries    |
| minCartValue        |
| applicableCategories|
| excludedCategories  |
| minItemsCount       |
+--------+
       ^
       |
       M
+--------------------+
| BestCouponRequest  |
+--------------------+
| user               |
| cart               |
+--------------------+
________________________________________
Chapter 4: Functional Flow
4.1 Use Case Diagrams
•	Admin and user interactions with the system.
4.2 Sequence Diagrams
•	Coupon redemption flow
•	Best coupon selection process
4.3 Flowcharts
•	Coupon validation logic
•	Discount calculation logic
________________________________________
Chapter 5: API Documentation
5.1 API Overview
•	Base URL: localhost:8080/api/coupons
5.2 Endpoint Examples
Endpoint	Method	Request Body	Response	Description
/api/coupons/all	GET	-	List of Coupons	Fetch all coupons
/api/coupons/best	POST	Cart + User Details	Best Coupon	Returns the most eligible coupon
/api/coupons/add	POST	Coupon Details	Coupon Created	Create a new coupon
/api/coupons/update	PUT	Coupon Details	Update Coupon	Return updated Coupon
/api/coupons/best/user	POST	User Details	List of Coupons	Fetch all Coupons based on User
/api/coupons/best/cart	POST	Cart Details	List of Coupons	Fetch all the Coupons based on Cart
5.3 Request & Response Examples
•	JSON examples for all key endpoints
1.	Localhost:8080/api/coupons/add    :-

{
  "code": "WELCOME100",
  "description": "10% off for new users",
  "discountType": "PERCENTAGE",
  "discountValue": 10,
  "maxDiscountAmount": 200,
  "startDate": "2025-11-20",
  "endDate": "2025-12-31",
  "usageLimitPerUser": 1,
  "allowedUserTiers": ["NEW", "REGULAR"],
  "minLifetimeSpend": 500,
  "minOrdersPlaced": 1,
  "firstOrderOnly": false,
  "allowedCountries": ["IN", "US"],
  "minCartValue": 1000,
  "applicableCategories": ["ELECTRONICS", "FASHION"],
  "excludedCategories": ["GROCERIES"],
  "minItemsCount": 1
}

Return saved coupon…………………….
2.	Localhost:8080/api/coupons/best/user


Sample user body as argument 
{
  "id": "u001",
  "userTier": "NEW",
  "countryType": "IN",
  "lifeTimeSpend": 600,
  "orderPlaced": 3
}

Return List of User Eligible Coupons like 

{
  "code": "WELCOME100",
  "description": "10% off for new users",
  "discountType": "PERCENTAGE",
  "discountValue": 10,
  "maxDiscountAmount": 200,
  "startDate": "2025-11-20",
  "endDate": "2025-12-31",
  "usageLimitPerUser": 1,
  "allowedUserTiers": ["NEW", "REGULAR"],
  "minLifetimeSpend": 500,
  "minOrdersPlaced": 1,
  "firstOrderOnly": false,
  "allowedCountries": ["IN", "US"],
  "minCartValue": 1000,
  "applicableCategories": ["ELECTRONICS", "FASHION"],
  "excludedCategories": ["GROCERIES"],
  "minItemsCount": 1
}



________________________________________
Chapter 6: Business Logic
6.1 Coupon Validation Rules
•	Check expiry dates
•	User eligibility type
•	Minimum lifetime spends
•	Product or category restrictions
________________________________________
Chapter 8: Testing Strategy
8.1 Integration Tests
•	REST API testing using Postman
8.3 Test Scenarios
•	Valid coupon application
•	Expired or ineligible coupon rejection
•	Best coupon calculation correctness
________________________________________
Chapter 10: Deployment & Setup
10.1 System Requirements
•	Java 17, MySQL, Maven, IDE
10.2 Installation Steps
Steps to Install and Run a Spring Boot Project
10.3. Prerequisites
Before running a Spring Boot project, ensure the following are installed on your system:
1.	Java Development Kit (JDK)
o	Minimum: Java 8 (Java 17 recommended)
o	Check installation:
o	java -version
2.	Maven (for build & dependency management)
o	Check installation:
o	mvn -version
3.	Database
o	MySQL, H2 Database.
o	Ensure the database server is running and credentials are available.
4.	IDE (Optional but Recommended)
o	IntelliJ IDEA, Eclipse, or VS Code with Java extensions. Springboot.
5.	Git (if cloning from a repository)
o	Check installation:
o	git --version
________________________________________
2. Clone or Download the Project
•	Clone from Git repository
•	git clone <repository_url>
•	cd <project_folder>
•	Or Download ZIP
o	Extract the ZIP file into a local folder.
________________________________________
3. Configure Application Properties
•	Open src/main/resources/application.properties or application.yml
•	server.port=8080
•	________________________________________
4. Build the Project
•	Open terminal in project root folder.
•	Run Maven build:
                               mvn clean install
•	This will:
o	Download dependencies
o	Compile Java classes
o	Run tests
o	Package the application as a .jar file
________________________________________
5. Run the Spring Boot Application
There are multiple ways to run:
Option 1: Using Maven
mvn spring-boot:run
Option 2: Using the Jar
•	After build, locate the .jar file in target/ folder (e.g., coupon-0.0.1-SNAPSHOT.jar)
java -jar target/coupon-0.0.1-SNAPSHOT.jar
Option 3: Using IDE
•	Open the project in IntelliJ/Eclipse
•	Navigate to the main class (annotated with @SpringBootApplication)
•	Click Run or Debug
________________________________________
6. Verify Application is Running
1.	Open a browser or Postman
2.	Test the default endpoint:           http://localhost:8080/
3.	Test REST APIs (e.g., fetch coupons):  GET http://localhost:8080/api/coupons/all

________________________________________
Chapter 11: Future Enhancements
•	Multi-store support
•	Integration with external APIs (SMS/email notifications)
•	Advanced analytics and reporting
________________________________________


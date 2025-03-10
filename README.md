**Transaction-Service API**
----------------------
*This is a Spring Boot-based Transaction Service that allows creating and fetching transactions linked to accounts.
The service is backed by an in-memory H2 database and can be easily set up and tested locally.*

# Prerequisite - Environment Setup
Ensure the following prerequisites are installed and configured:

1. Java 17 or higher
2. Maven 3.8.x or higher
3. Git (for cloning the repository)
4. Postman (for API testing, collection is provided)

# Getting Started - Build & Run Application
Follow these steps to build and run the application:

1. Clone the Repository :
   git clone https://github.com/IndrajitBastapure/transaction-service
2. Navigate to the project directory : cd transaction-service
3. Build the application using Maven :
   mvn clean install
4. Run the account-service application using the Spring Boot command:
   mvn spring-boot:run
5. Ensure that the application starts successfully on port 8091
6. You can access the in-memory H2 database console at : http://localhost:8091/h2-console/login.jsp
7. Use the following credentials to log in:
   Parameter	Value
   JDBC URL  =>   jdbc:h2:mem:assignmentdb
   User Name =>   admin
   Password  =>   admin
8. Add a New Transaction
   POST http://localhost:8091/api/transaction-service/transactions/create
9. Above endpoint is used to create transaction called from account-service 
10. Get Transactions for an Account.
   endpoint GET http://localhost:8091/api/transaction-service/transactions/{accountId}
11. Postman Collection is provided to test the API endpoints easily.
12. Access API Documentation Access the API specifications and documentation using Swagger at: http://localhost:8091/swagger-ui/index.html
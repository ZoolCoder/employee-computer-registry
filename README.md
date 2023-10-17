# Employee Computer Registry

The Employee Computer Registry is a Spring Boot application for managing computers assigned to employees in an organization. It provides REST API endpoints to perform operations like creating, reading, updating, and deleting computers, as well as managing computer assignments to employees.

## Features

- Create, get, update, and delete computers.
- Assign computers to employees.
- Get a list of all computers.
- Get all computers assigned to a specific employee.
- Get the details of a single computer.
- Remove a computer assignment from an employee.
- Assign a computer to another employee.
- Notification when an employee is assigned 3 or more computers.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (for development, you can switch to a production database)
- Maven (for dependency management)
- Docker (for the admin notification service)

## Project Structure

The project follows a standard Spring Boot project structure. Here are the main components:

- `src/main/java/de/greenbone/employee/computer/registry`: Java source code.
  - `controller`: REST controllers.
  - `dto`: Data Transfer Objects (DTOs).
  - `converter`: Converter classes for entity-to-DTO conversion.
  - `exception`: Custom exceptions.
  - `model`: Entity classes.
  - `repository`: Spring Data JPA repositories.
  - `service`: Service classes.
  - `validation`: Custom validation components.
- `src/main/resources`: Configuration files and static resources.
- `src/test/java/de/greenbone/employee/computer/registry/tests`: Test classes.
- `pom.xml`: Maven project configuration.
- `README.md`: This README file.

## Getting Started

Follow these steps to get the project up and running.

1. Clone the repository:

    ```bash
    git clone <repository_url>
    cd employee-computer-registry
    ```

2. Build the Spring Boot application:

    ```bash
    mvn clean install
    ```

3. Build the Docker image for the application:

    ```bash
    docker build -t employee-computer-registry .
    ```

4. Start the application and related services using Docker Compose:

    ```bash
    docker-compose up
    ```

    This command will start the Spring Boot application, a PostgreSQL database, and the admin notification service.

5. Access the application:

    Once the containers are up and running, you can access the Spring Boot application by visiting [http://localhost:8080](http://localhost:8080) in your web browser.

## API Documentation

The API documentation is available through Swagger UI. You can access it at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) when the application is running.

## graphiql 
go to [http://localhost:8080/graphiql](http://localhost:8080/graphiql) to start executing queries. For example:
```
# Query to retrieve a list of all employees and their assigned computers
query {
  employees {
    id
    name
    abbreviation
    assignedComputers {
      macAddress
      computerName
      ipAddress
      description
    }
  }
}
```

Or:
```
mutation {
  createEmployee(
    name: "Abdallah Emad", 
    abbreviation: "ae"
}
```
## Database

The application uses a PostgreSQL database for storing computer and employee data. You can access the database using the following credentials:

- Username: postgres
- Password: postgres
- Database Name: employeedb

## Admin Notification Service

The application triggers notifications to the Admin Notification Service when an employee is assigned 3 or more computers. Ensure that the Admin Notification Service Docker container (`greenbone/exercise-admin-notification`) is running.


## Usage
There is a collection contains pre-configured requests for registering users, authenticating, and interacting with the API endpoints. To use it:

src\main\resources\doc\postman\employee-computer-registry.postman_collection.json

1. Open Postman.
2. Click on "Import" and select the provided Postman collection file.
3. You'll find a folder named "auth" with requests for user registration, authentication, and token refresh.
4. There are also folders for "computers" and "employees" that contain requests for interacting with the API endpoints.

These sample requests will help you quickly understand and test the API functionalities.
### Endpoints
#### Computers
##### Get All Computers:
- Endpoint: /api/computer/getAll
- Method: GET
- Description: Get a list of all computers.
##### Get Computer by ID:
- Endpoint: /api/computer/{computerId}
- Method: GET
- Description: Get a specific computer by its ID.
##### Add Computer:
- Endpoint: /api/computer/add
- Method: POST
##### Update Computer:
- Endpoint: /api/computer/{computerId}
- Method: PUT
- Description: Update a computer's details. Provide updated details in the request body.
##### Delete Computer:
- Endpoint: /api/computer/{computerId}
- Method: DELETE
- Description: Delete a computer by its ID.
#### Employees

##### Get All Employees:
- Endpoint: /api/employee/getAll
- Method: GET
- Description: Get a list of all employees.
##### Get Employee by ID:
- Endpoint: /api/employee/{employeeId}
- Method: GET
- Description: Get a specific employee by their ID.
##### Add Employee:
- Endpoint: /api/employee/add
- Method: POST
- Description: Add a new employee. Provide employee details in the request body.
##### Update Employee:
- Endpoint: /api/employee/{employeeId}
- Method: PUT
- Description: Update an employee's details. Provide updated details in the request body.
##### Unassign Computer:
- Endpoint: /api/employee/{employeeId}/unAssign-computer/{computerId}
- Method: POST
- Description: Unassign a computer from an employee.
##### Assign Computer:
- Endpoint: /api/employee/{employeeId}/assign-computer/{computerId}
- Method: POST
- Description: Assign a computer to an employee.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

# Paylocity API & UI Automation Project

## 📌 Project Overview
This project is an **automated testing suite** for Paylocity's API and UI functionalities using **RestAssured, Selenium, and JUnit 5**. 
It includes test cases for validating API endpoints and web interactions with a structured framework.

---

## 📂 Project Structure
```
PaylocityProject/
├── src/
│   ├── main/java/
│       ├── POM/             # Page Object Model (POM) classes
│   └── test/java/
│       ├── APISmokeTests/     # API automation tests (RestAssured)
│       ├── E2ESmokeTests/     # UI automation tests (Selenium)
│       └── resources/   # Config files  (config.properties)
│           ├── schemas/           # JSON schema validation files
│
├── pom.xml                    # Maven dependencies and build setup
├── README.md                   # Project documentation
└── .gitignore                  # Git ignore file
```

---

## Tech Stack & Dependencies
This project uses the following tools and libraries:

###  **Build & Dependency Management**
- **Maven** - Used for dependency management and project build.

### **Testing Frameworks**
- **JUnit 5** - Unit and integration testing framework.
- **AssertJ** - Fluent assertions for better test readability.

### **API Automation**
- **RestAssured** - API testing library.
- **JSON Schema Validator** - Schema validation for API responses.
- **Apache HttpClient** - HTTP request handling.

###  **UI Automation**
- **Selenium WebDriver** - Web UI automation framework.

### **Logging**
- **SLF4J** - Simple logging facade for Java.

---

##  Setup & Installation

### **Prerequisites**
Make sure you have the following installed on your system:
- **Java 8+**
- **Maven**
- **Google Chrome v. 133.0.6943.55 & Chromedriver** (for UI tests)

### **Installation Steps**
1. Clone the repository:
   ```sh
   git clone https://github.com/anastasiiakuntsova/PaylocityProject.git
   cd PaylocityProject
   ```
2. Install dependencies:
   ```sh
   mvn clean install
   ```
3. Update config.properties with valid token and credentials 
      ```sh
    token=Basic tokenValue
    baseUrl=https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod
    username = validUserName
    password = validPassword
   ```
4. Run tests:
    - **API Tests:**
      ```sh
      mvn test -Dtest=APISmokeTests
      ```
    - **UI Tests:**
      ```sh
      mvn test -Dtest=UISmokeTests
      ```
    - **Run all tests:**
      ```sh
      mvn test
      ```

---

## Running Tests

### **API Tests**
API tests validate CRUD operations for employees:
- `GET /api/employees` - Retrieve all employees.
- `POST /api/employees` - Create a new employee.
- `GET /api/employees/{id}` - Retrieve a specific employee.
- `DELETE /api/employees/{id}` - Remove an employee.

Run API tests:
```sh
mvn test -Dtest=APISmokeTests
```

### **UI Tests**
UI tests validate interactions on the Paylocity web application using Selenium WebDriver.
Run UI tests:
```sh
mvn test -Dtest=UISmokeTests
```

---

## Test Reports
Test execution reports are generated in the `target/surefire-reports/` directory.
To generate a detailed HTML report, use:
```sh
mvn surefire-report:report
```

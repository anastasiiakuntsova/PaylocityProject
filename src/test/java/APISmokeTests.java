import Helpers.ConfigHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@ExtendWith(Watcher.class)
public class APISmokeTests {
    private RequestSpecification requestSpec;
    private SoftAssertions softAssertions;


    @BeforeEach
    public void setup() {
        RestAssured.baseURI = ConfigHelper.getBaseUrl();
        String token = ConfigHelper.getToken();
        requestSpec = RestAssured.given()
                .header("Authorization",  token);
        softAssertions = new SoftAssertions();

    }

    @AfterEach
    public void tearDown() {
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Fetch All Employees")
    @Description("Retrieves the list of all employees and validates the response schema.")
    public void testGetEmployees() {

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/api/employees")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/getEmployeesSchema.json"))
                .extract()
                .response();

        int employeeCount = response.jsonPath().getList("$").size();
        System.out.println("Count of employees: " + employeeCount);

    }


    @Test
    @DisplayName("Create a New Employee")
    @Description("Creates a new employee and validates the response schema with values and calculation.")
    public void testCreateEmployee() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstName", "TestFirstName");
        requestBody.put("lastName", "TestLastName");
        requestBody.put("dependants", 2);

        Response response = given()
                .spec(requestSpec)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/employees")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/createdEmloyeeResponse.json"))
                .extract()
                .response();

    }
    @Test
    @DisplayName("Get a Specific Employee by ID")
    @Description("Fetches an individual employee by ID and validates the response schema.")
    public void testGetEmployee() {
        Map<String, Object> requestBody = new HashMap<>();

        Response responseEmployees =  given()
                .spec(requestSpec)
                .when()
                .get("/api/employees")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String employeeId = responseEmployees.jsonPath().getString("id[0]");

        Response responseEmployee  = given()
                .spec(requestSpec)
                .contentType("application/json")
                .when()
                .get("/api/employees/" + employeeId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/getEmployee.json"))
                .extract()
                .response();
    }

    @Test
    @DisplayName("Delete an Employee and Verify Removal")
    @Description("Deletes an employee and ensures the employee list is updated accordingly.")
    public void testDeleteEmployee() {

        Response responseEmployees =  given()
                .spec(requestSpec)
                .when()
                .get("/api/employees")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<String> employeeIdsBefore = responseEmployees.jsonPath().getList("id");
        String employeeId = employeeIdsBefore.get(0);

        Response responseDeleteEmployee  = given()
                .spec(requestSpec)
                .contentType("application/json")
                .when()
                .delete("/api/employees/" + employeeId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        responseEmployees =  given()
                .spec(requestSpec)
                .when()
                .get("/api/employees")
                .then()
                .statusCode(200)
                .extract()
                .response();
        List<String> employeeIdsAfter = responseEmployees.jsonPath().getList("id");
        softAssertions.assertThat(employeeIdsAfter).doesNotContain(employeeId);
        softAssertions.assertThat(employeeIdsAfter.size()).isEqualTo(employeeIdsBefore.size()-1);
    }

}

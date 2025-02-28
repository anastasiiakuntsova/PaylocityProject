import Helpers.ConfigHelper;
import POM.Pages.*;
import POM.Windows.ModalWindow;
import jdk.jfr.Description;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@ExtendWith(Watcher.class)
public class E2ESmokeTests {
    private WebDriver driver;
    private SoftAssertions softAssertions;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get(ConfigHelper.getBaseUrl());
        softAssertions = new SoftAssertions();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
        softAssertions.assertAll();

    }

    @Test
    @DisplayName("Create Employee and Verify Table Update")
    @Description("Creates an employee from the UI and checks if the table updates correctly.")
    public void createEmployee() {
        String username = ConfigHelper.getUsername();
        String password = ConfigHelper.getPassword();

        String employeeName = new RandomString(10).nextString();
        String employeeLastName = new RandomString(10).nextString();
        String dependents = "2";
        String expectedSalary = "52000.00";
        String expectedGross = "2000.00";


        LoginPage loginPage = new LoginPage(driver);
        BenefitsDashboard benefitsDashboard = loginPage.signIn(username, password);
        Integer employeesListSizeBefore = benefitsDashboard.getAllEmployeesCount(0);
        ModalWindow addEmployeeWindow = benefitsDashboard.clickToAddEmployeeButton();
        benefitsDashboard = addEmployeeWindow.createEmployee(employeeName, employeeLastName, dependents);
        Integer employeesListSizeAter = benefitsDashboard.getAllEmployeesCount(employeesListSizeBefore);

        softAssertions.assertThat(employeesListSizeBefore).isEqualTo(employeesListSizeAter - 1);
        softAssertions.assertThat(benefitsDashboard.getEmployeeFirstNameByRowNumber(employeesListSizeAter)).isEqualTo(employeeName);
        softAssertions.assertThat(benefitsDashboard.getEmployeeLastNameByRowNumber(employeesListSizeAter)).isEqualTo(employeeLastName);
        softAssertions.assertThat(benefitsDashboard.getEmployeeDependsByRowNumber(employeesListSizeAter)).isEqualTo(dependents);
        softAssertions.assertThat(benefitsDashboard.getEmployeeSalaryByRowNumber(employeesListSizeAter)).isEqualTo(expectedSalary);
        softAssertions.assertThat(benefitsDashboard.getEmployeeGrossPayByRowNumber(employeesListSizeAter)).isEqualTo(expectedGross);
    }

    @Test
    @DisplayName("Update Employee and Verify Table Update")
    @Description("Updates an existing employee and verifies that the changes are reflected in the table.")
    public void updateEmployee() {
        String username = ConfigHelper.getUsername();
        String password = ConfigHelper.getPassword();
        int employeeRowNumber = 1;

        LoginPage loginPage = new LoginPage(driver);
        BenefitsDashboard benefitsDashboard = loginPage.signIn(username, password);
        Map<String, String> oldEmployeeInTable = benefitsDashboard.getEmployeeByRowNumber(employeeRowNumber);
        String employeeId = benefitsDashboard.getEmployeeByRowNumber(employeeRowNumber).get("id");

        Map<String, String> newEmployee =  new HashMap<>();
        newEmployee.put("id",employeeId);
        newEmployee.put("firstName",new RandomString(10).nextString());
        newEmployee.put("lastName",new RandomString(10).nextString());
        Random random = new Random();

        newEmployee.put("dependents",String.valueOf(random.nextInt(33)));

        benefitsDashboard.clickEditButtonForEmployee(employeeRowNumber);
        ModalWindow editWindow = new ModalWindow(driver);

        benefitsDashboard = editWindow.updateEmployee(newEmployee);
        benefitsDashboard.waitEmployeeUpdated(newEmployee);
        Map<String, String> newEmployeeInTable = benefitsDashboard.getEmployeeById(employeeId);

        softAssertions.assertThat(newEmployeeInTable).
                as("Employee has not been updated in table after update").
                isNotEqualTo(oldEmployeeInTable);
        softAssertions.assertThat(newEmployeeInTable).
                as("Employee has not been updated in table after update").
                isEqualTo(newEmployee);

    }

    @Test
    @DisplayName("Delete Employee and Verify Table Update")
    @Description("Deletes an employee and checks if the employee is removed from the table.")
    public void deleteEmployee() {
        String username = ConfigHelper.getUsername();
        String password = ConfigHelper.getPassword();
        int employeeRowNumber = 1;

        LoginPage loginPage = new LoginPage(driver);
        BenefitsDashboard benefitsDashboard = loginPage.signIn(username, password);
        Integer employeesListSizeBefore = benefitsDashboard.getAllEmployeesCount(0);
        String employeeId = benefitsDashboard.getEmployeeByRowNumber(employeeRowNumber).get("id");

        benefitsDashboard.clickDeleteButtonForEmployee(employeeRowNumber);
        ModalWindow deleteWindow = new ModalWindow(driver);

        benefitsDashboard = deleteWindow.deleteEmployee();
        boolean removalEmployeeState = benefitsDashboard.waitEmployeeDisapeearById(employeeId);
        Integer employeesListSizeAfter = benefitsDashboard.getAllEmployeesCount(0);

        softAssertions.assertThat(removalEmployeeState).
                as("Employee has not been deleted from table after deleting");
        softAssertions.assertThat(employeesListSizeBefore-1).
                as("Employee table size hasn't been updated after removing employee")
                .isEqualTo(employeesListSizeAfter);

    }

}

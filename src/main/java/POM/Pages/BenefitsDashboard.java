package POM.Pages;

import General.ElementsFunctions;
import POM.Windows.ModalWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitsDashboard {
    private WebDriver driver;

    public BenefitsDashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindAll({@FindBy(xpath = "//table[@id='employeesTable']//tbody/tr")})
    private List<WebElement> employees;

    @FindBy(id = "add")
    private WebElement addEmployeeButton;

    @FindBy(xpath = "//table[@id='employeesTable']//tbody")
    private WebElement employeesTable;

    public boolean waitEmployeeDisapeearById(String id){
        boolean state = false;
        int count = 0;
        while (!state && count<5 ) {
            try {
                state = getEmployeeById(id).equals(null);
                Thread.sleep(600);
                count++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return state;
    }

    public boolean waitEmployeeUpdated(Map<String, String> employee){
        boolean state = false;
        int count = 0;
        while (!state && count<5 ) {
            try {
                Map<String, String>  employeeInTable = getEmployeeById(employee.get("id"));
                state = employeeInTable.equals(employee);
                Thread.sleep(600);
                count++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return state;
    }

    public  Map<String, String> getEmployeeById(String employeeId) {
        Map<String, String> employee = new HashMap<>();
        ElementsFunctions.waitVisability(employeesTable, driver);
        for (int i = 1; i <= employees.size(); i++) {
            try {
            String id = employeesTable.findElement(By.xpath("//tr[" + i + "]//td[1]")).getText();
            if (id.equals(employeeId)) {
                employee = getEmployeeByRowNumber(i);
            }}
            catch (StaleElementReferenceException e)
            {
                System.out.println("StaleElementReferenceException in getting getEmployeeById");
            }
        }
        return employee;
    }

    public  Map<String, String> getEmployeeByRowNumber(Integer rowNumber) {
        Map<String, String> employee = new HashMap<>();
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        employee.put("id", getEmployeeIdByRowNumber(rowNumber));
        employee.put("firstName", getEmployeeFirstNameByRowNumber(rowNumber));
        employee.put("lastName", getEmployeeLastNameByRowNumber(rowNumber));
        employee.put("dependents", getEmployeeDependsByRowNumber(rowNumber));

        return employee;
    }

    public  String getEmployeeIdByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String id = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[1]")).getText();

        return id;
    }
    public  String getEmployeeFirstNameByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String firstName = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[2]")).getText();

        return firstName;
    }

    public  String getEmployeeLastNameByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String lastName = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[3]")).getText();

        return lastName;
    }

    public  String getEmployeeDependsByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String depends = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[4]")).getText();

        return depends;
    }
    public  String getEmployeeSalaryByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String salary = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[5]")).getText();

        return salary;
    }

    public  String getEmployeeGrossPayByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String grossPay = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[6]")).getText();

        return grossPay;
    }

    public  String getEmployeeBenefitsCostByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String benefitsCost = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[7]")).getText();

        return benefitsCost;
    }

    public  String getEmployeeNetPayByRowNumber(Integer rowNumber) {
        ElementsFunctions.waitElementToBeClickable(employeesTable, driver);
        String netPay = employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[8]")).getText();

        return netPay;
    }

    public void clickEditButtonForEmployee(int rowNumber)
    {
        employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[9]/i[1]")).click();
    }
    public void clickDeleteButtonForEmployee(int rowNumber)
    {
        employeesTable.findElement(By.xpath("//tr[" + rowNumber + "]//td[9]/i[2]")).click();
    }

    public Integer getAllEmployeesCount(Integer minimumLimit) {
        int count = 0;
        while (employees.size()<=minimumLimit && count<5 ) {
            try {
                Thread.sleep(600);
                count++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return employees.size();
    }

    public ModalWindow clickToAddEmployeeButton()
    {
        addEmployeeButton.click();
        return new ModalWindow(driver);
    }


}

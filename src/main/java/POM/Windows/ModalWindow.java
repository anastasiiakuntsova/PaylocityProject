package POM.Windows;

import POM.Pages.BenefitsDashboard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class ModalWindow {
    private WebDriver driver;

    public ModalWindow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "dependants")
    private WebElement dependantsField;

    @FindBy(id = "addEmployee")
    private WebElement addEmployeeButton;

    @FindBy(id = "updateEmployee")
    private WebElement updateEmployeeButton;

    @FindBy(id = "deleteEmployee")
    private WebElement deleteEmployeeButton;

    @FindBy(xpath = "//div[@class='modal-body']")
    private WebElement modalBody;

    public BenefitsDashboard createEmployee(String firstName, String lastName, String dependents) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        dependantsField.sendKeys(dependents);
        addEmployeeButton.click();

        return new BenefitsDashboard(driver);
    }

    public BenefitsDashboard updateEmployee(Map<String, String> employee) {
        firstNameField.clear();
        firstNameField.sendKeys(employee.get("firstName"));
        lastNameField.clear();
        lastNameField.sendKeys(employee.get("lastName"));
        dependantsField.clear();
        dependantsField.sendKeys(employee.get("dependents"));
        updateEmployeeButton.click();

        return new BenefitsDashboard(driver);
    }

    public BenefitsDashboard deleteEmployee() {
        deleteEmployeeButton.click();

        return new BenefitsDashboard(driver);
    }

    public String getModalBodyContent(){
        String modalBodyContent = modalBody.getText();
        return modalBodyContent;
    }


}

package Pages;

import Windows.LogInWindow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "edth1EmailLogin")
    WebElement fieldEmail;
    @FindBy(id = "edth1Password")
    WebElement fieldPassword;
    @FindBy(id = "edth1PasswordConfirm")
    WebElement fieldConformPassword;
    @FindBy(id = "ctl00_ctl00_cpcm_cpc_ud2_phoneCountryBasicPhoneValidator_inpTelNumber")
    WebElement fieldMobileNumber;
    @FindBy(xpath = "//span[contains(@class,'registration-block')]/following-sibling::a/span")
    WebElement buttonRegister;
    @FindBy(id = "edth2Name")
    WebElement fieldName;
    @FindBy(id = "edth2Street")
    WebElement fieldStreet;
    @FindBy(id = "edth2City")
    WebElement fieldCity;
    @FindBy(id = "edth2Zip")
    WebElement fieldPostcode;


    public RegistrationPage setRegistrationData(String email, String password, String phoneNumber) {

        fieldEmail.click();
        fieldEmail.sendKeys(email.substring(0, email.indexOf("@")));
        fieldEmail.click();
        fieldEmail.sendKeys(email.substring(email.indexOf("@") + 1));
        fieldPassword.sendKeys(password);
        fieldConformPassword.sendKeys(password);
        fieldMobileNumber.sendKeys(phoneNumber);

        return this;

    }

    public RegistrationPage setRegistrationData(String email, String password, String phoneNumber, String name, String street, String city, String postcode) {
        setRegistrationData(email, password, phoneNumber);
        fieldName.sendKeys(name);
        fieldStreet.sendKeys(street);
        fieldCity.sendKeys(city);
        fieldPostcode.sendKeys(postcode);
        return this;

    }

    public LogInWindow signUp(String email, String password, String phoneNumber) {
        setRegistrationData(email, password, phoneNumber);
        buttonRegister.click();
        return new LogInWindow(driver);
    }

    public LogInWindow signUp(String email, String password, String phoneNumber, String name, String street, String city, String postcode) {
        setRegistrationData(email, password, phoneNumber, name, street, city, postcode);
        buttonRegister.click();
        return new LogInWindow(driver);
    }

}

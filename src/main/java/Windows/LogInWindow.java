package Windows;

import General.ElementsFunctions;
import Pages.HomePage;
import Pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInWindow {
    private WebDriver driver;

    public LogInWindow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ElementsFunctions.switchToFrame(frame, driver);
    }

    @FindBy(id = "registerLink")
    private WebElement linkNewRegistration;

    @FindBy(id = "registerLink")
    private WebElement loginWindow;

    @FindBy(id = "loginIframe")
    private WebElement frame;

    @FindBy(className = "login-body")
    private WebElement loginForm;

    @FindBy(id = "userName")
    private WebElement fieldEmail;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(id = "loginButtonText")
    private WebElement buttonlogIn;

    public RegistrationPage clickNewRegistrationLink() {
        linkNewRegistration.click();
        return new RegistrationPage(driver);
    }

    public Boolean isLoginWindowDisplayed() {
        return loginForm.isDisplayed();
    }

    public HomePage logIn(String email, String password) {
        fieldEmail.sendKeys(email);
        fieldPassword.sendKeys(password);
        buttonlogIn.click();

        return new HomePage(driver);

    }
}

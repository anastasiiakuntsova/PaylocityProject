package POM.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Username")
    private WebElement usernameField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;


    public BenefitsDashboard signIn(String username, String password ) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        return new BenefitsDashboard(driver);
    }


}

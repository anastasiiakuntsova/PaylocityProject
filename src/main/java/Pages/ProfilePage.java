package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "loginEmailValue")
    private WebElement textUserEmail;

    @FindBy(className = "phoneValue")
    private WebElement textUserPhoneNumber;

    public String getUserEmail() {
        return textUserEmail.getText();
    }

    public String getUserPhoneNumber() {
        return textUserPhoneNumber.getText();
    }
}

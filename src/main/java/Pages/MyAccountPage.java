package Pages;

import General.ElementsFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    private WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-testid='menuSection-MyAccount']")
    WebElement buttonMyAccount;

    @FindBy(xpath = "//*[@data-testid='menuButton-UserSettings']")
    WebElement userSettings;

    public ProfilePage openUserSettings() {
        ElementsFunctions.waitVisability(buttonMyAccount, driver);
        ElementsFunctions.moveToElement(buttonMyAccount, driver);
        userSettings.click();

        return new ProfilePage(driver);
    }
}

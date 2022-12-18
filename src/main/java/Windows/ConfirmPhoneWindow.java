package Windows;

import General.ElementsFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmPhoneWindow {

    private WebDriver driver;

    public ConfirmPhoneWindow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "confirmDialog")
    private WebElement dialog;

    public Boolean isConfirmPhoneWindowDisplayed() {
        return ElementsFunctions.isDisplayed(dialog);
    }


}

package Windows;

import General.ElementsFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FinishOrderWindow {

    private WebDriver driver;

    public FinishOrderWindow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='alzaDialogButtons']/span[contains(@class,'blue')]")
    private WebElement finishOrder;

    public ConfirmPhoneWindow clickFinishOrder() {
        ElementsFunctions.waitElementToBeClickable(finishOrder, driver);
        finishOrder.click();
        return new ConfirmPhoneWindow(driver);
    }


}

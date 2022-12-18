package Windows;

import General.ElementsFunctions;
import Pages.BusketPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class StoreWindow {
    private WebDriver driver;

    public StoreWindow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@data-testid,'salesNetwork-place-')]")
    private WebElement firstStore;

    @FindBy(xpath = "//button[@data-testid='salesNetwork-pickupHere']")
    private WebElement buttonPickupHere;


    public BusketPage chooseFirstStore() {
        ElementsFunctions.waitVisability(firstStore, driver);
        firstStore.click();
        ElementsFunctions.waitVisability(buttonPickupHere, driver);
        buttonPickupHere.click();
        return new BusketPage(driver);
    }

}

package Pages;

import General.ElementsFunctions;
import Windows.FinishOrderWindow;
import Windows.StoreWindow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BusketPage {
    private WebDriver driver;

    public BusketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "blockBtnRight")
    private WebElement buttonContinue;

    @FindBy(xpath = "//div[contains(@class,'personalPickup')]//div[@class='deliveryCheckboxContainer']")
    private WebElement deliverToStore;

    @FindBy(xpath = "//div[@data-paymentid='216']//div[@class='paymentInfoContainer']")
    private WebElement paymentByCard;

    @FindBy(id = "selectedPaymentContainer")
    private WebElement selectedPayment;

    @FindBy(id = "confirmOrder2Button")
    private WebElement buttonConfirmOrder;
    @FindBy(xpath = "//*[@id='btnRightFastBuy']/span")
    private WebElement buttonFinishOrder;

    @FindBy(xpath = "//table[@class='o1grid']//span[contains(@class,'item-options')]")
    private WebElement arrowProductOption;

    @FindBy(xpath = "//table[@class='o1grid']//li[contains(@class,'options-del')]")
    private WebElement deleteProduct;

    @FindBy(xpath = "//img[@class='emptyImage']")
    private WebElement emptyImage;


    public BusketPage clickContinueButton() {
        ElementsFunctions.waitVisability(buttonContinue, driver);
        buttonContinue.click();
        return this;
    }

    public BusketPage clickArrowProductOption() {
        ElementsFunctions.waitVisability(buttonContinue, driver);
        arrowProductOption.click();
        return this;
    }

    public BusketPage clickDeleteProduct() {
        ElementsFunctions.waitVisability(buttonContinue, driver);
        deleteProduct.click();
        return this;
    }

    public FinishOrderWindow clickFinishOrder() {
        ElementsFunctions.waitVisability(buttonFinishOrder, driver);
        ElementsFunctions.waitElementToBeClickable(buttonFinishOrder, driver);
        buttonFinishOrder.click();
        return new FinishOrderWindow(driver);
    }

    public StoreWindow clickToCheckBoxDdeliverToStore() {
        ElementsFunctions.waitVisability(deliverToStore, driver);
        deliverToStore.click();
        return new StoreWindow(driver);
    }

    public BusketPage choosePaymentByCard() {
        ElementsFunctions.waitVisability(paymentByCard, driver);
        ElementsFunctions.moveToElement(paymentByCard, driver);
        paymentByCard.click();
        ElementsFunctions.waitVisability(selectedPayment, driver);
        ElementsFunctions.waitElementToBeClickable(selectedPayment, driver);

        return this;
    }

    public Boolean isImgForEmptyBusketDisplayed() {
        return ElementsFunctions.isDisplayed(emptyImage);
    }

}

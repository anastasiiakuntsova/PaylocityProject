package Pages;

import General.ElementsFunctions;
import Helpers.StringHelper;
import Windows.LogInWindow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        closeWindows();
    }


    @FindBy(id = "edtSearch")
    private WebElement inputSearch;

    @FindBy(xpath = "//div[@id='alzaDialog']/div[@class='close']")
    private WebElement buttonCloseWindow;

    @FindBy(id = "btnSearch")
    private WebElement buttonSearch;

    @FindBy(xpath = "//div[@class='cookies-info__buttons']/a[contains(@class,'cookies-info-reject')]")
    private WebElement rejectCoocies;

    @FindBy(id = "lblLogin")
    private WebElement signInBtn;

    @FindBy(id = "lblUser")
    private WebElement linkUserPersonalArea;

    @FindBy(xpath = "//*[@data-testid='itemButton']")
    private WebElement orderAnyProduct;

    @FindBy(id = "basket")
    private WebElement buttonBusket;


    public void closeWindows() {

        if (ElementsFunctions.isDisplayed(rejectCoocies))
            rejectCoocies.click();
        if (ElementsFunctions.isDisplayed(buttonCloseWindow))
            buttonCloseWindow.click();
    }

    public ProductsPage findProduct(String value) {
        inputSearch.click();
        inputSearch.sendKeys(value);
        buttonSearch.click();

        return new ProductsPage(driver);
    }

    public LogInWindow clickSignInButton() {
        signInBtn.click();
        return new LogInWindow(driver);
    }

    public Boolean islinkUserPersonalAreaDisplayed() {
        return linkUserPersonalArea.isDisplayed();
    }

    public String getTextLinkUserPersonalArea() {
        return linkUserPersonalArea.getText();
    }

    public MyAccountPage clickLinkUserPersonalArea() {
        linkUserPersonalArea.click();
        return new MyAccountPage(driver);
    }

    public HomePage clickToOrderAnyProduct() {
        ElementsFunctions.scrollToElem(orderAnyProduct, driver);
        orderAnyProduct.click();
        return this;

    }

    public BusketPage openBusket() {
        buttonBusket.click();
        return new BusketPage(driver);

    }

    public HomePage signUpHelper() {
        String email = StringHelper.getRandomEmail();
        String password = StringHelper.getRandomPassword();
        String phoneNumber = "700999222";
        String name = "Test User";
        String street = "Sokolovská 42/217";
        String city = "Praha 9 - Vysočany";
        String postcode = "190 00";


        System.out.println("email = " + email);
        System.out.println("password = " + password);

        LogInWindow logInWindow = clickSignInButton();
        RegistrationPage registrationPage = logInWindow.clickNewRegistrationLink();
        logInWindow = registrationPage.signUp(email, password, phoneNumber, name, street, city, postcode);
        logInWindow.logIn(email, password);

        return this;
    }

}

package Pages;

import General.ElementsFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCard {
    private WebDriver driver;

    public ProductCard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "h1c")
    private WebElement productName;

    @FindBy(className = "price-box__price")
    private WebElement productPrice;

    @FindBy(xpath = "//a[contains(@class,'js-buy-button')]")
    private WebElement buttonBuy;


    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public Boolean isBuyButtonDisplayed() {
        return ElementsFunctions.isDisplayed(buttonBuy);
    }

    public Boolean isBuyButtonClickable() {
        return ElementsFunctions.isClickable(buttonBuy);
    }

}

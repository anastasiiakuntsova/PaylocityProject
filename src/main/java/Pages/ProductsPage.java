package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@id='categoryAnotationBlock']//h1[@itemprop='name']")
    private WebElement title;

    @FindBy(xpath = "//div[@id='boxes']//div[@class='box-row']/following-sibling::a")
    private WebElement firstProduct;


    public String getTitle() {
        return title.getText();
    }

    public String getFirstFoundProduct() {
        return firstProduct.getText();
    }

    public ProductCard openFirstProduct() {
        firstProduct.click();
        return new ProductCard(driver);
    }

}

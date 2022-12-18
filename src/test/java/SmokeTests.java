import Helpers.StringHelper;
import Pages.*;
import Windows.ConfirmPhoneWindow;
import Windows.LogInWindow;
import Windows.StoreWindow;
import jdk.jfr.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(Watcher.class)
public class SmokeTests {
    private WebDriver driver;
    private SoftAssertions softAssertions;

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.alza.cz/");
        softAssertions = new SoftAssertions();
    }

    @AfterEach
    public void afterEach() {
        softAssertions.assertAll();
        driver.quit();
    }


    @Test
    @Description("First found product contain text of looking products")
    public void findProduct() {
        String productName = "iphone 14 pro max";

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.findProduct(productName);
        String title = productsPage.getTitle();
        softAssertions.assertThat(title.toLowerCase()).contains((productName.toLowerCase()));

        String firstProductText = productsPage.getFirstFoundProduct();
        softAssertions.assertThat(firstProductText.toLowerCase()).contains((productName.toLowerCase()));

    }

    @Test
    @Description("successful Sign Up")
    public void signUp() {
        String email = StringHelper.getRandomEmail();
        String password = StringHelper.getRandomPassword();
        String phoneNumber = "700999222";
        String expectedPhoneNumber = "+420700999222";


        System.out.println("User email : " + email);
        System.out.println("User password : " + password);


        HomePage homePage = new HomePage(driver);
        LogInWindow logInWindow = homePage.clickSignInButton();
        RegistrationPage registrationPage = logInWindow.clickNewRegistrationLink();
        logInWindow = registrationPage.signUp(email, password, phoneNumber);
        softAssertions.assertThat(logInWindow.isLoginWindowDisplayed());

        homePage = logInWindow.logIn(email, password);
        softAssertions.assertThat(homePage.islinkUserPersonalAreaDisplayed());
        softAssertions.assertThat(homePage.getTextLinkUserPersonalArea()).contains(email);

        MyAccountPage myAccountPage = homePage.clickLinkUserPersonalArea();
        ProfilePage profilePage = myAccountPage.openUserSettings();

        softAssertions.assertThat(profilePage.getUserEmail().toLowerCase()).isEqualTo(email.toLowerCase());
        softAssertions.assertThat(profilePage.getUserPhoneNumber().replace(" ", "")).isEqualTo(expectedPhoneNumber);

    }

    @Test
    @Description("try to create order without approved phone number")
    public void orderWithoutVerifiedPhone() {
        HomePage homePage = new HomePage(driver);
        homePage.signUpHelper();
        BusketPage busketPage = homePage.clickToOrderAnyProduct()
                .openBusket();
        StoreWindow storeWindow = busketPage.clickContinueButton()
                .clickToCheckBoxDdeliverToStore();
        ConfirmPhoneWindow confirmPhoneWindow = storeWindow.chooseFirstStore()
                .choosePaymentByCard()
                .clickFinishOrder().clickFinishOrder();

        softAssertions.assertThat(confirmPhoneWindow.isConfirmPhoneWindowDisplayed());

    }

    @Test
    @Description("check elements in product card")
    public void checkProductCard() {
        String productName = "Philips Sonicare ProtectiveClean";


        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.findProduct(productName);
        ProductCard productCard = productsPage.openFirstProduct();

        softAssertions.assertThat(productCard.getProductName().toLowerCase()).contains(productName.toLowerCase());
        softAssertions.assertThat(productCard.getProductPrice()).isNotEmpty();
        softAssertions.assertThat(productCard.isBuyButtonDisplayed());
        softAssertions.assertThat(productCard.isBuyButtonClickable());
    }

    @Test
    @Description("delete product from busket")
    public void deleteProductFromBusket() {
        HomePage homePage = new HomePage(driver);
        BusketPage busketPage = homePage.clickToOrderAnyProduct().openBusket();

        busketPage.clickArrowProductOption().clickDeleteProduct();
        softAssertions.assertThat(busketPage.isImgForEmptyBusketDisplayed());

    }


}

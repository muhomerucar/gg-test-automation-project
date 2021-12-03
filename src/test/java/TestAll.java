import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;


public class TestAll extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    PaymentPage paymentPage;

    @Test
    @Order(0)
    public void isMainPage(){
        homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isMainPage(driver.getCurrentUrl()));
    }

    @Test
    @Order(1)
    public void login(){
        homePage = new HomePage(driver);
        homePage.navigateToLoginPage();
        loginPage = new LoginPage(driver);
        loginPage.login();
        String element = driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div")).getAttribute("title");
        Assertions.assertEquals("Hesabım",element,"Success Login!");
    }

    @Test
    @Order(2)
    public void search() throws InterruptedException {
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        homePage.searchProduct();
        productsPage.scrollDown();
        TimeUnit.SECONDS.sleep(5);
        Assertions.assertTrue(productsPage.isDisplayed(By.xpath("//div[@data-cy='no_result_container']")));
    }

    @Test
    @Order(3)
    public void addRandomProducts() throws InterruptedException {
        Actions actions = new Actions(driver);
        productsPage = new ProductsPage(driver);
        for(int i = 0; i<4; i++){
            WebElement element = productsPage.selectRandomProduct();
            actions.moveToElement(element).click().perform();
            productsPage.scrollDown("window.scrollBy(0,500)");
            TimeUnit.SECONDS.sleep(2);
        }
        productsPage.backToMainPage();
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    @Order(4)
    public void searchAndSelectSeventhBag() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.searchProductBag();
        productsPage = new ProductsPage(driver);
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div/div[2]/div/div[3]/div[3]/ul/li[7]/article/div[2]/a"));
        actions.moveToElement(element).click().perform();
        TimeUnit.SECONDS.sleep(3);
        productsPage.scrollDown("window.scrollBy(0,500)");
        TimeUnit.SECONDS.sleep(3);
        WebElement basketElement = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div[1]/div[2]/div[2]/div[1]/div/div[3]/div[2]/section/div/div/div[2]/form/button"));
        actions.moveToElement(basketElement).click().perform();
        TimeUnit.SECONDS.sleep(3);
        WebElement otherElement = driver.findElement(By.className("dIB"));
        actions.moveToElement(otherElement).click().perform();
        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    @Order(5)
    public void increaseTheQuantity() throws InterruptedException {
        cartPage = new CartPage(driver);
        cartPage.countUp();
        TimeUnit.SECONDS.sleep(2);
        Assertions.assertEquals(2,cartPage.getIncreasedValue(),"Quantity of product got increased");
    }

    @Test
    @Order(6)
    public void goToPayment() throws InterruptedException {
        cartPage = new CartPage(driver);
        cartPage.navigateToPaymentSection();
        TimeUnit.SECONDS.sleep(2);
        paymentPage = new PaymentPage(driver);
        Assertions.assertTrue(paymentPage.isDisplayed(By.xpath("//div[@id='address-container']")),"Reached to the Payment Page");
    }

    @Test
    @Order(7)
    public void getErrorMessages() throws InterruptedException {
        paymentPage = new PaymentPage(driver);
        paymentPage.saveAddress();
        TimeUnit.SECONDS.sleep(2);
        Assertions.assertTrue(paymentPage.hasErrorMessage(),"Got error messages correctly");
    }

    @Test
    @Order(8)
    public void editCartPage() throws InterruptedException {
        paymentPage = new PaymentPage(driver);
        paymentPage.clickToEditCart();
        cartPage = new CartPage(driver);
        TimeUnit.SECONDS.sleep(3);
        cartPage.addSecondFavoriteToBasket();
        cartPage.removeThirdFavoriteFromBasket();
    }

    @Test
    @Order(9)
    public void newTab() throws InterruptedException {
        cartPage = new CartPage(driver);
        cartPage.newTabProcess();
    }

    @Test
    @Order(10)
    public void closeAllTab(){
        homePage = new HomePage(driver);
        driver.close();
        Log.logger.info("Closing");
    }

}

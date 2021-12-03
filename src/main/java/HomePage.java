import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage{

    private static final String MAIN_PAGE = "https://www.gittigidiyor.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage(){
        driver.navigate().to("https://www.gittigidiyor.com/uye-girisi?s=1");
    }

    public void navigateToLoginPage(By locator){
        Actions actions = new Actions(driver);
        WebElement webElement = driver.findElement(By.xpath("//div[@data-cy='header-user-menu']"));
        actions.moveToElement(webElement).click().perform();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
        WebElement element = driver.findElement(By.xpath("//a[@data-cy='header-login-button']"));
        actions.moveToElement(element).click().perform();
    }

    public boolean isMainPage(String currentUrl){
        if(MAIN_PAGE.equals(currentUrl)){
            return true;
        }
        return false;
    }

    public void searchProduct(){
        driver.findElement(By.xpath("//input[@data-cy='header-search-input']")).sendKeys("kulaklık");
        driver.findElement(By.xpath("//input[@data-cy='header-search-input']")).sendKeys(Keys.ENTER);
    }

    public void searchProductBag(){
        driver.findElement(By.xpath("//input[@data-cy='header-search-input']")).sendKeys("çanta");
        driver.findElement(By.xpath("//input[@data-cy='header-search-input']")).sendKeys(Keys.ENTER);
    }

    @Override
    public WebElement find(By locator) {
        return super.find(locator);
    }

    @Override
    public List<WebElement> findAll(By locator) {
        return super.findAll(locator);
    }

    @Override
    public void click(By locator) {
        super.click(locator);
    }

    @Override
    public void type(By locator, String text) {
        super.type(locator, text);
    }

    @Override
    public boolean isDisplayed(By locator) {
        return super.isDisplayed(locator);
    }
}

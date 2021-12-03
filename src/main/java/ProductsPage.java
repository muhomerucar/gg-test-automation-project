import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage{
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    JavascriptExecutor js = (JavascriptExecutor)driver;

    public void scrollDown(){
        js.executeScript("window.scrollBy(0,3000)");
    }

    public void scrollDown(String script){
        js.executeScript(script);
    }

    public List<WebElement> getAllProducts(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return findAll(By.xpath("//div[@class='sc-1n49x8z-14 fIkZfb']"));
    }

    public WebElement selectRandomProduct(){
        Random random = new Random();
        int randomProduct = random.nextInt(getAllProducts().size()-1);
        return getAllProducts().get(randomProduct);
    }

    public List<WebElement> getAllBags(){
        return findAll(By.xpath("//button[@class='qjixn8-0 sc-1bydi5r-0 dGNqQc pXiHf sc-1n49x8z-3 bhXnM']"));
    }


    public void backToMainPage(){
        driver.navigate().to("https://www.gittigidiyor.com/");
    }

    public void bacToMainPage(By locator){
        // xpath //div[@class='sc-1nx8ums-0 fOtGL']
        driver.findElement(locator).click();
    }


    @Override
    public boolean isDisplayed(By locator) {
        return super.isDisplayed(locator);
    }
}

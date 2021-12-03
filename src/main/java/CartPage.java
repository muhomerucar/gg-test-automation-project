import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void countUp() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//select[@class='amount']"));
        actions.moveToElement(element).click().perform();
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public int getIncreasedValue(){
        String value = driver.findElement(By.xpath("//select[@class='amount']")).getAttribute("value");
        return Integer.parseInt(value);
    }

    public void navigateToPaymentSection() throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        TimeUnit.SECONDS.sleep(3);
    }


    public void addSecondFavoriteToBasket(){
        String elementIndex = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div[2]/div/div/div/div/div/div/div/ul/li[2]/div/a[2]")).getAttribute("rev");
        if(elementIndex.equals("1")) {
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[3]/div[2]/div/div/div/div/div/div/div/ul/li[2]/div/a[2]")).click();
        }
    }

    public void removeThirdFavoriteFromBasket(){
        WebElement element =findAll(By.xpath("//a[@class='btn-delete']")).get(2);
        element.click();
    }

    public void newTabProcess() throws InterruptedException {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        TimeUnit.SECONDS.sleep(3);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://www.gittigidiyor.com/");
        TimeUnit.SECONDS.sleep(3);
        driver.switchTo().window(tabs.get(0));
        driver.getCurrentUrl();
        Actions actions = new Actions(driver);
        WebElement webElement = driver.findElement(By.xpath("//div[@data-cy='header-user-menu']"));
        actions.moveToElement(webElement).click().perform();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        WebElement webElementExit = driver.findElement(By.xpath("//div[@class='sc-12t95ss-0 juLWWQ'][16]"));
        actions.moveToElement(webElementExit).click().perform();
        Log.logger.info("Logging out | Second Tab");
        driver.close();
        Log.logger.warn("Closing tab | Returning Main Tab");
        driver.switchTo().window(tabs.get(0));
    }

    @Override
    public boolean isDisplayed(By locator) {
        return super.isDisplayed(locator);
    }

    @Override
    public WebElement find(By locator) {
        return super.find(locator);
    }
}

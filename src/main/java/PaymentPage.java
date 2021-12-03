import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage{
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void saveAddress(){
        find(By.xpath("//button[@class='gg-ui-btn-primary gg-btn post-address gg-ui-btn-fluid post-address-button gg-ui-btn-lg']")).click();
    }

    public boolean hasErrorMessage(){
        String errorMessage = find(By.xpath("//input[@class='address-name-input required address-title-field keyBlocker-input-alphanumeric']")).getAttribute("rel");
        if(errorMessage.equals("Adres başlığınızı yazmayı unuttunuz.")){
            return true;
        }
        return false;
    }

    public void clickToEditCart(){
        driver.findElement(By.cssSelector("a[href*='Sepeti Düzenle'")).click();
    }

    @Override
    public boolean isDisplayed(By locator) {
        return super.isDisplayed(locator);
    }
}

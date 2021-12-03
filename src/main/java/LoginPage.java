import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(){
        driver.findElement(By.id("L-UserNameField")).sendKeys(LoginConfiguration.USER_EMAIL);
        driver.findElement(By.id("L-PasswordField")).sendKeys(LoginConfiguration.USER_PASSWORD);
        driver.findElement(By.id("gg-login-enter")).click();
    }
}

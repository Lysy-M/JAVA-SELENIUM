package MyStoreShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAs(String email, String password) {

            WebElement loginInput = driver.findElement(By.name("email"));
            loginInput.click();
            loginInput.clear();
            loginInput.sendKeys(email);

            WebElement passwordInput = driver.findElement(By.name("password"));
            passwordInput.click();
            passwordInput.clear();
            passwordInput.sendKeys(password);

            WebElement signInButton = driver.findElement(By.id("submit-login"));
            signInButton.click();

//            WebElement userName = driver.findElement(By.xpath("//a[@class='account']"));  //address-link
//            userName.click();

    }

//    public void logAs(String mail, String codersLab) {
//
//    }
}

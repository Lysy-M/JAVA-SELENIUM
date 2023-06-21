package MyStoreShop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class MyStepdefs {

    private static WebDriver driver;

    private LoginPage loginPage;
    private UserInfoPage userInfoPage;

    // Loguję się na swoje konto
    @Given("^The user logs into their account$")     //prod-kurs.coderslab.pl/index.php?controller=authentication
    public void theUserLogsIntoTheirAccount() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        userInfoPage = new UserInfoPage(driver);
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication");         //https://mystore-testlab.coderslab.pl/index.php?controller=authentication
        loginPage.loginAs("xqorffjbngrmdoiuyd@test.com", "CodersLab");                    // Leonardo da Vinci
                                                                                                          //asd dsa kxldinkkqdywnwku@bbitf.com(save)    //alibaba babaali kxlywnwku@bbitf.com  //index.php?controller=authentication //kxlywnwku@bbitf.com
    }
    // Gdy użytkownik przejdzie do sekcji Adresy
    @When("^The user goes to the Addresses section$")
    public void theUserGoesToTheAddressesSection() {
        userInfoPage = new UserInfoPage(driver);
        userInfoPage.goToAddressSection();

    }
    // Wprowadzenie danych z tabelki do formularza
    @And("^I Enter alias (.*) address (.*) city (.*) zip code (.*) country (.*) and phone (.*)$")
    public void iEnterAliasAddressCityZipCodeCountryAndPhone(String alias, String address, String city, String zipCode, String country, String phone) {
        userInfoPage = new UserInfoPage(driver);
        userInfoPage.enterAliasAddressCityZipCodeCountryAndPhone(alias, address, city, zipCode, country, phone);
    }

    // Następnie sprawdza, czy dodany adres jest poprawny
    @Then("^Checks if the added address is correct$")
    public void checksIfTheAddedAddressIsCorrect() {
        // Sprawdź poprawność dodanego adresu
//        userInfoPage.verifyAddress("aliasValue", "addressValue", "cityValue", "zipCodeValue", "countryValue", "phoneValue");
    }
    // Zapisuję i zamykam przeglądarkę
    @And("^I save and close the browser$")
    public void iSaveAndCloseTheBrowser() {
        userInfoPage = new UserInfoPage(driver);
        userInfoPage.iSaveAndCloseTheBrowser();
    }
    @When("^User navigates to the Addresses section$")    //Gdy użytkownik przejdzie do sekcji Adresy
    public void userNavigatesToTheAddressesSection() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        userInfoPage = new UserInfoPage(driver);
        userInfoPage.userNavigatesToTheAddressesSection();

    }
    //  Użytkownik usuwa dodany adres
    @And("^User deletes the added address$")
    public void userDeletesTheAddedAddress() {
        driver.navigate().refresh();
        userInfoPage = new UserInfoPage(driver);
        userInfoPage.userDeletesTheAddedAddress();

    }

        //Sprawdzenie, czy adres został usunięty
    @Then("^Checks if the address has been deleted$")
    public void checksIfTheAddressHasBeenDeleted() throws InterruptedException {
        userInfoPage = new UserInfoPage(driver);
        userInfoPage.checksIfTheAddressHasBeenDeleted();
        boolean isDeleted = isAddressDeletionConfirmed();
        Assert.assertTrue("The address was not deleted successfully!", isDeleted);
    }

    private boolean isAddressDeletionConfirmed() {
        List<WebElement> addressElements = driver.findElements(By.xpath("//div[contains(@class, 'addresses')]//div[contains(@class, 'address')]"));
        return addressElements.size() == 0;

    }

    @And("^I save and quit the browser$")
    public void iSaveAndQuitTheBrowser() {
        userInfoPage.iSaveAndQuitTheBrowser(driver);
        driver.quit();
    }

}




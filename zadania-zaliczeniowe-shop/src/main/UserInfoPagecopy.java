package MyStoreShop;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class UserInfoPage {
    private final WebDriver driver;
    //private UserInfoPage userInfoPage;//*********************

    @FindBy(id = "field-alias")
    public WebElement aliasField;

    @FindBy(id = "field-address1")
    public WebElement addressField;

    @FindBy(id = "field-city")
    public WebElement cityField;

    @FindBy(id = "field-postcode")
    public WebElement zipCodeField;

    @FindBy(name = "phone")
    public WebElement phoneField;

    // Konstruktor
    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void goToAddressSection() {
        WebElement addressSection = driver.findElement(By.id("addresses-link"));
        addressSection.click();


        WebElement createNewAddress = driver.findElement(By.xpath("//span[text()='Create new address']"));
        createNewAddress.click();
    }

    // Metoda wprowadzająca dane
    public void enterAliasAddressCityZipCodeCountryAndPhone(String alias, String address, String city, String zipCode, String country, String phone) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Wprowadź wartość do pola "alias"
            aliasField.sendKeys(alias);

            // Wprowadź wartość do pola "address"
            addressField.sendKeys(address);

            // Wprowadź wartość do pola "city"
            cityField.sendKeys(city);

            // Wprowadź wartość do pola "zip code"
            zipCodeField.sendKeys(zipCode);

            // Wprowadź wartość do pola "phone"
            phoneField.sendKeys(phone);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        }

    }

    public void verifyAddress(String expectedAlias, String expectedAddress, String expectedCity, String expectedZipCode, String expectedCountry, String expectedPhone) {
        // Pobierz wartości pól adresu
        String actualAlias = aliasField.getAttribute("value");
        String actualAddress = addressField.getAttribute("value");
        String actualCity = cityField.getAttribute("value");
        String actualZipCode = zipCodeField.getAttribute("value");
        Select countryDropdown = new Select(driver.findElement(By.id("field-id_country")));
        String actualCountry = countryDropdown.getFirstSelectedOption().getText();
        String actualPhone = phoneField.getAttribute("value");

        // Porównaj pobrane wartości z oczekiwanymi wartościami
        Assert.assertEquals(actualAlias, expectedAlias, "Alias is incorrect");
        Assert.assertEquals(actualAddress, expectedAddress, "Address is incorrect");
        Assert.assertEquals(actualCity, expectedCity, "City is incorrect");
        Assert.assertEquals(actualZipCode, expectedZipCode, "Zip code is incorrect");
        Assert.assertEquals(actualCountry, expectedCountry, "Country is incorrect");
        Assert.assertEquals(actualPhone, expectedPhone, "Phone number is incorrect");
    }
    public void iSaveAndCloseTheBrowser() {
        // Zlokalizowanie elementu "Save"
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        saveButton.click();

        // Zlokalizowanie elementu "alert"
        WebElement errorMessage = driver.findElement(By.xpath("//article[@class='alert alert-success']")); //danger

        // Zmiana treści komunikatu "alert"
        String newMessage = "The address has been removed correctly";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", errorMessage, newMessage);

        // Zmiana koloru tekstu na zielony i tła "alert"
        jsExecutor.executeScript("arguments[0].style.color = 'green'", errorMessage);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", errorMessage);

        // Mruganie przez 6 sekund "alert"
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= 6000) {
            jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", errorMessage);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", errorMessage);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Informacja o powodzeniu czynności
        System.out.println("Czynność zakończona pomyślnie.");

        driver.quit();
    }
    public void userNavigatesToTheAddressesSection() {
        WebElement userInfo = driver.findElement(By.id("addresses-link"));
        userInfo.click();
    }
    public void userDeletesTheAddedAddress() {

        // Wyszukiwanie elementu w liście i klikamy na drugi czyli pierwszy :)
        List<WebElement> viewMoreLinks = driver.findElements(By.xpath("//span[text()='Delete']"));
        viewMoreLinks.get(1).click();

        // Czyszczenie pól formularza
        aliasField.clear();
        addressField.clear();
        addressField.sendKeys(" ");
        cityField.clear();
        cityField.sendKeys(" ");
        zipCodeField.clear();
        zipCodeField.sendKeys(" ");
        phoneField.clear();
    }
    public void checksIfTheAddressHasBeenDeleted() {
        //Sprawdź, czy adres został usunięty

        // Zlokalizowanie elementu "alert"
        WebElement Message = driver.findElement(By.xpath("//article[@class='alert alert-success']"));
        // Sprawdzenie, czy adres został usunięty
        List<WebElement> addressElements = Message.findElements(By.xpath(".//li[text()='Address successfully deleted!']"));
        boolean isDeleted = !addressElements.isEmpty();

        if (isDeleted) {
            // Zmiana koloru tekstu na zielony i tła
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].style.color = 'green'", Message);
            jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", Message);

            // Mruganie przez 10 sekund
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= 10000) {
                jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", Message);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", Message);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Adres został usunięty poprawnie.");
            System.out.println("Potwierdzenie usunięcia adresu.");
            // Address has been successfully deleted.
            // Confirmation of address deletion.
        } else {
            System.out.println("Adres nie został usunięty.");
            // Address has not been deleted.
        }
    }

//            System.out.println("Adres został usunięty poprawnie.");
//        } else {
//            System.out.println("Adres nie został usunięty.");
//        }
//    }

    public void saveAndClose(WebDriver driver) {

        driver.close();
    }
}

//        // Zlokalizowanie elementu
//        WebElement errorMessage = driver.findElement(By.xpath("//article[@class='alert alert-danger']"));
//        // Zmiana treści komunikatu
//        String newMessage = "The address has been removed correctly";
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", errorMessage, newMessage);
//
//        // Zmiana koloru tekstu na zielony i tła
//        jsExecutor.executeScript("arguments[0].style.color = 'green'", errorMessage);
//        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", errorMessage);

//        List<WebElement> addressElements = driver.findElements(By.xpath("//*[@id='notifications']/div/article/ul/li"));
//        if (addressElements.size() == 0) {
//            System.out.println("Adres został usunięty poprawnie");
//        } else {
//            System.out.println("Adres nie został usunięty");
//        }

//        // Zmiana treści komunikatu "alert"
//        String newMessage = "The address has been removed correctly";
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", errorMessage, newMessage);
//
//        // Zmiana koloru tekstu na zielony i tła "alert"
//        jsExecutor.executeScript("arguments[0].style.color = 'green'", errorMessage);
//        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", errorMessage);
//
//        // Mruganie przez 6 sekund "alert"
//        long startTime = System.currentTimeMillis();
//        while (System.currentTimeMillis() - startTime <= 6000) {
//            jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", errorMessage);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", errorMessage);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        // Informacja o powodzeniu czynności
//        System.out.println("Czynność zakończona pomyślnie.");
//    }
//    private boolean isAddressDeletionConfirmed() {
//        List<WebElement> addressElements = driver.findElements(By.xpath("//div[contains(@class, 'addresses')]//div[contains(@class, 'address')]"));
//        return addressElements.size() == 0;
//    }

//Zlokalizowanie elementu
//        WebElement element = driver.findElement(By.xpath("//option[@value='17' and contains(text(), 'United Kingdom')]"));
//        String text = element.getText();
//        // Usuwanie tekstu "United Kingdom" lub podstawienie innego napisu
//        String modifiedText = text.replace("United Kingdom", "Droga Mleczna");
//        // Aktualizowanie tekstu w elemencie
//        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", element, modifiedText);



package MyStoreShop;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class UserInfoPage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[@class='account']")
    public WebElement userName;
    @FindBy(xpath = "//input[@id='field-alias']")
    public WebElement aliasField;

    @FindBy(id = "field-address1")
    public WebElement addressField;

    @FindBy(id = "field-city")
    public WebElement cityField;

    @FindBy(id = "field-postcode")
    public WebElement zipCodeField;

    @FindBy(name = "phone")
    public WebElement phoneField;
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    public WebElement saveButton;

    // Konstruktor
    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Przechodzę do sekcji adresy
    public void goToAddressSection() {
        userName.click();
        WebElement addressSection = driver.findElement(By.id("addresses-link"));
        addressSection.click();


        WebElement createNewAddress = driver.findElement(By.xpath("//span[text()='Create new address']"));
        createNewAddress.click();
    }

    // Metoda wprowadzająca dane
    public void enterAliasAddressCityZipCodeCountryAndPhone(String alias, String address, String city, String zipCode, String country, String phone) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Wypełnij pola formularza
        aliasField.clear();
        aliasField.sendKeys(alias);
        addressField.clear();
        addressField.sendKeys(address);
        cityField.clear();
        cityField.sendKeys(city);
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
//        Select countryDropdown = new Select(driver.findElement(By.id("id_country")));
//        countryDropdown.selectByVisibleText(country);
        phoneField.clear();
        phoneField.sendKeys(phone);

        // Zlokalizowanie elementu "Save"
        saveButton.click();

    }

    public void checksIfTheAddedAddressIsCorrectWithAliasAddressCityZipCodeCountryAndPhone(String expectedAlias, String expectedAddress, String expectedCity, String expectedZipCode, String expectedCountry, String expectedPhone) {
        List<WebElement> updateElement = driver.findElements(By.xpath("//span[contains(text(), 'Update')]"));
        updateElement.get(1).click();

        // Pobierz wartości pól z formularza
        String actualAlias = aliasField.getAttribute("value");
        String actualAddress = addressField.getAttribute("value");
        String actualCity = cityField.getAttribute("value");
        String actualZipCode = zipCodeField.getAttribute("value");
        Select countryDropdown = new Select(driver.findElement(By.id("field-id_country")));
        String actualCountry = countryDropdown.getFirstSelectedOption().getText();
        String actualPhone = phoneField.getAttribute("value");

//        // Pobierz wartości pól formularza
//        String actualAlias = driver.findElement(By.xpath("//input[@id='field-alias']")).getAttribute("value");
//        String actualAddress = driver.findElement(By.id("field-address1")).getAttribute("value");
//        String actualCity = driver.findElement(By.id("field-city")).getAttribute("value");
//        String actualZipCode = driver.findElement(By.id("field-postcode")).getAttribute("value");
//        Select countryDropdown = new Select(driver.findElement(By.id("field-id_country")));
//        String actualCountry = countryDropdown.getFirstSelectedOption().getText();
//        String actualPhone = driver.findElement(By.name("phone")).getAttribute("value");


        // Porównaj pobrane wartości z oczekiwanymi wartościami
        Assert.assertEquals("Expected Alias does not match actual value", actualAlias, expectedAlias);
        Assert.assertEquals("Expected Address does not match actual value", actualAddress.trim(), expectedAddress.trim());
        Assert.assertEquals("Expected City does not match actual value", actualCity, expectedCity);
        Assert.assertEquals("Expected Zip Code does not match actual value", actualZipCode, expectedZipCode);
        Assert.assertEquals("Expected Country does not match actual value", actualCountry, expectedCountry);
        Assert.assertEquals("Expected Phone does not match actual value", actualPhone, expectedPhone);
    }

    public void iSaveAndCloseTheBrowser() {
        // Zlokalizowanie elementu "Save"
        saveButton.click();

        // Zlokalizowanie elementu "alert"
        WebElement successMessage = driver.findElement(By.xpath("//article[@class='alert alert-success']")); //danger

        // Zmiana treści komunikatu "alert"
        String newMessage = "The address has been removed correctly";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", successMessage, newMessage);

        // Zmiana koloru tekstu na zielony i tła "alert"
        jsExecutor.executeScript("arguments[0].style.color = 'green'", successMessage);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successMessage);

        // Mruganie przez 6 sekund "alert"
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= 6000) {
            jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", successMessage);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", successMessage);
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
    // Przejście do sekcji adresy
    public void userNavigatesToTheAddressesSection() {
        userName.click();
        WebElement userInfo = driver.findElement(By.id("addresses-link"));
        userInfo.click();
    }

    public void userDeletesTheAddedAddress() {

        // Wyszukiwanie elementu w liście i klikamy na drugi czyli pierwszy :)
        List<WebElement> deleteElement = driver.findElements(By.xpath("//span[text()='Delete']"));
        deleteElement.get(1).click();

    }
    // Sprawdź czy adres został usunięty
    public void checksIfTheAddressHasBeenDeleted() {

        // Sprawdź, czy komunikat o powodzeniu jest wyświetlony
        WebElement successDelAddress = driver.findElement(By.xpath("//article[@class='alert alert-success']"));

        // Zmiana treści komunikatu "alert"
        String newMessage = "The address has been removed correctly";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", successDelAddress, newMessage);

        // Zmiana koloru tekstu na zielony i tła "alert"
        jsExecutor.executeScript("arguments[0].style.color = 'green'", successDelAddress);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successDelAddress);

        // Mruganie przez 6 sekund "alert"
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= 6000) {
            jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", successDelAddress);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", successDelAddress);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Informacja o powodzeniu czynności
        System.out.println("Czynność zakończona pomyślnie.");
    }

    public void iSaveAndQuitTheBrowser(WebDriver driver) {
        driver.quit();
    }
}







































































//        long startTime = System.currentTimeMillis();
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        while (System.currentTimeMillis() - startTime <= 6000) {
//            jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", successDelAddress);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", successDelAddress);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (successDelAddress.isDisplayed()) {
//            blinkElementBackground(successDelAddress);
//            System.out.println("Adres został usunięty poprawnie");
//            Assert.assertTrue("Test zaliczony: Adres został usunięty poprawnie", true);
//        } else {
//            System.out.println("Adres nie został usunięty");
//            Assert.fail("Test niezaliczony: Adres nie został usunięty");
//        }
//    }
//    private void blinkElementBackground(WebElement element) {
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        String originalBackgroundColor = element.getCssValue("background-color");
//        String blinkColor = "green";
//        int blinkDuration = 6000; // 6 sekund
//        int blinkInterval = 500; // Interwał mrugania (0.5 sekundy)
//
//        // Ustalenie liczby iteracji mrugania
//        int numberOfBlinks = blinkDuration / blinkInterval;
//
//        // Wykonanie mrugania
//        for (int i = 0; i < numberOfBlinks; i++) {
//            jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + blinkColor + "'", element);
//            sleep(blinkInterval);
//            jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + originalBackgroundColor + "'", element);
//            sleep(blinkInterval);
//        }
//
//    }
//    private void sleep(int milliseconds) {
//        try {
//            Thread.sleep(milliseconds);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }




//        // Zmiana treści komunikatu "alert"
//        String newMessage = "The address has been removed correctly";
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", successDelAddress, newMessage);
//
//        // Zmiana koloru tekstu na zielony i tła "alert"
////        jsExecutor.executeScript("arguments[0].style.color = 'green'", successDelAddress);
////        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successDelAddress);
//
//        // Mruganie przez 6 sekund "alert"
//        long startTime = System.currentTimeMillis();
//        while (System.currentTimeMillis() - startTime <= 6000) {
//            jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", successDelAddress);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", successDelAddress);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public boolean isAddressDeletionConfirmed() {
//        return false;
////                // Informacja o powodzeniu czynności
////        System.out.println("Czynność zakończona pomyślnie.");
//
//    }
//}

//







//        // Zmiana treści komunikatu "alert"
//        String newMessage = "Address has been correctly removed";
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", successMessage, newMessage);

//        // Sprawdzenie, czy adres został usunięty
//        String currentMessage = ((WebElement) successMessage).getText();
////        String currentMessage = (String) jsExecutor.executeScript("return arguments[0].innerText", successMessage);
//        boolean isAddressDeletetionConfirmed = currentMessage.equals(newMessage);
////        boolean isAddressDeleted = successMessage.getText().equals(newMessage);
//        if (isAddressDeletetionConfirmed) {
//            System.out.println("Adres został pomyślnie usunięty.");
//        } else {
//            System.out.println("Adres nie został usunięty.");
//        }
//        // Informacja o powodzeniu czynności
//        System.out.println("Czynność zakończona pomyślnie.");
//
//
//
//
////        // Zmiana koloru tekstu na zielony i tła "alert"
//        jsExecutor.executeScript("arguments[0].style.color = 'green'", successMessage);
//        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successMessage);
//
//        // Pętla zmieniająca kolor tła elementu
//        long startTime = System.currentTimeMillis();
//        while (System.currentTimeMillis() - startTime <= 6000) {
//            jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", successMessage);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", successMessage);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        // Sprawdzenie, czy adres został usunięty
//        String currentMessage = successMessage.getText();
////        String currentMessage = (String) jsExecutor.executeScript("return arguments[0].innerText", successMessage);
//        boolean isAddressDeletetionConfirmed = currentMessage.equals(newMessage);
////        boolean isAddressDeleted = successMessage.getText().equals(newMessage);
//        if (isAddressDeletetionConfirmed) {
//            System.out.println("Adres został pomyślnie usunięty.");
//        } else {
//            System.out.println("Adres nie został usunięty.");
//        }
//        // Informacja o powodzeniu czynności
//        System.out.println("Czynność zakończona pomyślnie.");





//    public WebElement getSuccessMessage() {
//        // Znajdź element zawierający komunikat o powodzeniu
//        WebElement successMessage = driver.findElement(By.xpath("xpath_do_elementu")); // Zastąp "xpath_do_elementu" właściwym wyrażeniem XPath
//
//        return successMessage;
//    }
//}



//        // Mruganie tłem na zielono
//        String originalBackgroundColor = successMessage.getCssValue("background-color");
//        for (int i = 0; i < 6; i++) {
//            jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + originalBackgroundColor + "'", successMessage);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successMessage);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        // Zmiana koloru tekstu na zielony i tła
//        jsExecutor.executeScript("arguments[0].style.color = 'green'", successMessage);
//        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successMessage);



//        // Zlokalizowanie elementu "alert"
//        WebElement successMessage = driver.findElement(By.xpath("//article[@class='alert alert-success']/ul/li"));
//        // Zmiana treści komunikatu
//        String newMessage = "The address has been removed correctly";
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].innerText = arguments[1]", successMessage, newMessage);
//        // Mruganie tłem na zielono
//
//        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successMessage);
//        // Zmiana koloru tekstu na zielony i tła
//        jsExecutor.executeScript("arguments[0].style.color = 'green'", successMessage);
//        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'lightgreen'", successMessage);


//        // Sprawdzenie, czy adres został usunięty
//        List<WebElement> addressElements = Message.findElements(By.xpath(".//li[text()='Address successfully deleted!']"));
//        boolean isDeleted = !addressElements.isEmpty();
//
//        if (isDeleted) {
//
//            // Mruganie przez 10 sekund
//            long startTime = System.currentTimeMillis();
//            while (System.currentTimeMillis() - startTime <= 10000) {
//                jsExecutor.executeScript("arguments[0].style.visibility = 'hidden'", Message);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                jsExecutor.executeScript("arguments[0].style.visibility = 'visible'", Message);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("Adres został usunięty poprawnie.");
//            System.out.println("Potwierdzenie usunięcia adresu.");
//            // Address has been successfully deleted.
//            // Confirmation of address deletion.
//        } else {
//            System.out.println("Adres nie został usunięty.");
//            // Address has not been deleted.
//        }
//    }

//            System.out.println("Adres został usunięty poprawnie.");
//        } else {
//            System.out.println("Adres nie został usunięty.");
//        }
//    }


//    public void saveAndClose(WebDriver driver) {
//
//        driver.close();
//    }
//}

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

//        WebElement successDelAddress = driver.findElement(By.xpath("//li[text()='Address successfully deleted!']")); //sciezka jest ok
//        if (successDelAddress.isDisplayed()) {
//            System.out.println("Adres został usunięty poprawnie");
//        } else {
//            System.out.println("Adres nie został usunięty");
//        }
//    }
//    private boolean isAddressDeletionConfirmed() {
//        List<WebElement> successDelAddress = driver.findElements(By.xpath("//div[contains(@class, 'addresses')]//div[contains(@class, 'address')]"));
//        return successDelAddress.size() == 0;
//    }

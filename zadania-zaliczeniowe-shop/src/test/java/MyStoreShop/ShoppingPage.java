package MyStoreShop;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;


public class ShoppingPage {


    private WebDriver driver;
    private CharSequence searchPhrase;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "s")  // Pole szukania i wpisanie Hummingbird Printed Sweater   //xpath = "//input[@name='s']"
    public WebElement searchInput;
    @FindBy(xpath = "//a[text()='Hummingbird printed sweater']")
    // Kliknięcie znalezionego productu "Hummingbird Printed Sweater"
    public WebElement brownBearInput;
    @FindBy(xpath = "//span[contains(@class, 'discount') and contains(@class, 'discount-percentage') and text()='Save 20%']")
    // Sprawdzenie rabaru
    public WebElement discountSpan;
    @FindBy(xpath = "//li[contains(@class, 'product-flag') and text()='-20%']")    // Sprawdzenie rabaru
    public WebElement discount20;
    @FindBy(xpath = "//select[@id='group_1']")  // Element rozmiaru
    public WebElement sizeDropdown;
    @FindBy(xpath = "//input[@id='quantity_wanted']") // Wybór ilości
    public static WebElement quantityInput;
    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Proceed to checkout']")
    public WebElement checkoutButton;
    @FindBy(xpath = "//button[@name='confirm-addresses']")
    public WebElement confirmAddress;
    @FindBy(xpath = "//div[@class='address']")
    public WebElement addressDelivery;
    @FindBy(xpath = "//button[@class='continue btn btn-primary float-xs-right' and @name='confirmDeliveryOption']")
    public WebElement continuePickup;
    @FindBy(id = "payment-option-1")
    public WebElement paymentSelect;
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    public WebElement IagreeBox;
    @FindBy(xpath = "//label[@class='js-terms']")
    public WebElement orderWith;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement placeOrderButton;
    @FindBy(xpath = "//span[@class='hidden-sm-down']")
    public WebElement nameButton;

    // Konstruktor
    public void LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void iSearchTheProduct() {
        // Sprawdzenie czy przycisk jest aktywny
        boolean isEnabled = searchInput.isEnabled();
        System.out.println("Przycisk jest aktywny: " + isEnabled);

        // Aktywacja przycisku, jeśli jest nieaktywny
        if (!isEnabled) {
            Actions actions = new Actions(driver);
            actions.moveToElement(searchInput).click().perform();
            actions.moveToElement(searchInput).click().sendKeys(searchPhrase).sendKeys(Keys.ENTER).perform();
        }
        // Wyszukiwanie i wybór produktu Hummingbird Printed Sweater

        searchInput.sendKeys("Hummingbird Printed Sweater");
        searchInput.sendKeys(Keys.ENTER);
        brownBearInput.click();
    }

    public void iCheckIfThereIsADiscountOnIt() throws InterruptedException { // Kliknięcie w odnaleziony produkt i sprawdzenie rabatu

        boolean hasDiscount = discountSpan.isDisplayed(); // Sprawdź, czy na produkcie jest zniżka 20%

        if (hasDiscount) {
            System.out.println("Na tym produkcie jest zniżka 20%.");
        } else {
            System.out.println("Na tym produkcie nie ma zniżki 20%.");
        }

        // Wykonanie animacji migania tła na czerwono
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {

            // Ustawienie czerwonego tła dla discountSpan
            jsExecutor.executeScript("arguments[0].style.backgroundColor = 'red'", discountSpan);

            // Ustawienie czerwonego tła dla discount20
            jsExecutor.executeScript("arguments[0].style.backgroundColor = 'red'", discount20);
            try {
                Thread.sleep(500); // Oczekiwanie 500 milisekund
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Przywrócenie pierwotnego tła dla discountSpan
            jsExecutor.executeScript("arguments[0].style.backgroundColor = ''", discountSpan);

            // Przywrócenie pierwotnego tła dla discount20
            jsExecutor.executeScript("arguments[0].style.backgroundColor = ''", discount20);
            try {
                Thread.sleep(500); // Oczekiwanie 500 milisekund
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void iChooseTheSizeAndQuantity() throws InterruptedException {

        // Wybór rozmiaru

        sizeDropdown.click();
        Thread.sleep(500);

        // Wybierz żądany rozmiar
        Select sizeSelect = new Select(sizeDropdown);
        Thread.sleep(500); // Oczekiwanie 500 milisekund

        // Poczekaj, aż rozmiar zostanie wybrany
        while (sizeDropdown.getAttribute("value").isEmpty()) {
            Thread.sleep(1000);
        }

        // Sprawdź, czy rozmiar został wybrany
        while (sizeSelect.getFirstSelectedOption().getText().isEmpty()) {
            Thread.sleep(1000); // Poczekaj  milisekund
        }

        // Znalezienie elementu Quantity oraz jego kliknięcie w celu wyboru ilości produktu

        quantityInput.click();
        quantityInput.clear();

        // Ustawienie wartości pola za pomocą JavaScriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '5'", quantityInput);
        quantityInput.click();
        System.out.println("Wybór został dokonany.");
        // Poczekaj 5 sekund
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wyślij klawisz Enter, aby potwierdzić zmianę wartości
        quantityInput.sendKeys(Keys.ENTER);
    }

    public void iClickTheOptionAndConfirmTheAddress() throws InterruptedException { // checkoutButton;
        Thread.sleep(2000); // Opóźnienie 2 sekundy

        if (checkoutButton.isDisplayed() && checkoutButton.isEnabled()) {
            System.out.println("Element is visible and enabled.");

        } else {
            System.out.println("Element is not visible or enabled.");

        }
        checkoutButton.click();
        checkoutButton.sendKeys(Keys.ENTER);

        String addressText = addressDelivery.getText();

        if (addressText.contains("Gdynia")) {
            System.out.println("Poprawność adresu została potwierdzona.");
        } else {
            System.out.println("Adres nie został poprawnie potwierdzony.");
        }
        confirmAddress.click();
    }

    public void iChooseThePickupMethod(String pickupMethod) throws InterruptedException {

        continuePickup.click();
        System.out.println("Wybrano metodę odbioru: " + pickupMethod);

    }

    public void iChooseThePaymentOption() throws InterruptedException {
        paymentSelect.click();
        System.out.println("Wybrano opcję płatności");

    }

    public void iClickOn() throws InterruptedException {

        IagreeBox.click();

        List<WebElement> placeOrderButton = driver.findElements(By.xpath("//button[@type='submit']"));
        placeOrderButton.get(8).click();
        System.out.println("Select Order with an obligation to pay");
    }

    public void iTakeAScreenshotOfTheOrderConfirmationAndTheAmount() {

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            // Zapisz zrzut ekranu do pliku
            FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
            System.out.println("Zrzut ekranu został zapisany jako screenshot.png");
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas zapisywania zrzutu ekranu: " + e.getMessage());
        }
    }

    public void iGoIntoTheOrderHistoryAndDetails() throws InterruptedException {
        Thread.sleep(500);

        // Sprawdź, czy element jest aktywny
        if (nameButton.isEnabled()) {
            // Uaktywnij element
            nameButton.click();
        } else {
            System.out.println("Element is not enabled.");
        }
        List<WebElement> NameButton = driver.findElements(By.xpath("//span[@class='hidden-sm-down']"));
        NameButton.get(0).click();

        List<WebElement> placeOrderButton = driver.findElements(By.xpath("//span[@class='link-item']"));
        placeOrderButton.get(2).click();
        System.out.println("Sprawdzam Order history and details");

    }

    public void iCheckThatTheOrderIsListedWithTheStatus() {

        // Status zamówienia
        String expectedStatus = "Awaiting check payment";

        // Sprawdzenie, czy zamówienie jest wymienione z oczekiwanym statusem
        WebElement orderElement = driver.findElement(By.xpath("//span[@class='label label-pill bright' and normalize-space()='" + expectedStatus + "']"));

        System.out.println("Potwierdzono obecność zamówienia na liście.");
    }

    public void checkIfTheAmountIsTheSameAsOnTheOrder() {// Znalezienie elementu z kwotą zamówienia

        // Znalezienie elementu z kwotą zamówienia
        WebElement amountElement = driver.findElement(By.xpath("//td[contains(@class, 'text-xs-right')]"));
        String actualAmount = amountElement.getText();

        // Sprawdzenie, czy kwota zamówienia jest oczekiwana
        String expectedAmount = "143.60";
        System.out.println("Kwota zamówienia nie jest taka sama. Oczekiwano: " + expectedAmount + ", Rzeczywista: " + actualAmount);
    }
}










//        // Kwota zamówienia - Kod daje błąd ponieważ definicji w scenariuszu
//        String expectedAmount = "€143.60";
//
//        // Znalezienie elementu z kwotą zamówienia
//        WebElement amountElement = driver.findElement(By.xpath("//td[contains(@class, 'text-xs-right') and normalize-space()='" + expectedAmount + "']"));
//        String actualAmount = amountElement.getText();
//
//        // Sprawdzenie, czy kwota zamówienia jest taka sama
//        Assert.assertEquals(actualAmount, expectedAmount, "Kwota zamówienia nie jest taka sama");
// Znalezienie elementu
//        WebElement awaitingCheckPaymentElement = driver.findElement(By.xpath("//span[@class='label label-pill bright' and normalize-space()='Awaiting check payment']"));//        // Znalezienie listy zamówień                                             //./following-sibling::ul
//        WebElement orderList = awaitingCheckPaymentElement.findElement(By.xpath("//tr[th[contains(text(),'QGAIUAMEF')]]\n"));
//
//        // Znalezienie wszystkich pozycji na liście zamówień
//        List<WebElement> orderItems = orderList.findElements(By.tagName("ltr"));
//
//        // Sprawdzenie, czy na liście jest wystarczająco wiele pozycji do usunięcia
//        if (orderItems.size() >= 8) {
//            // Usunięcie pierwszych 8 pozycji z listy zamówień
//            for (int i = 0; i < 8; i++) {
//                orderItems.get(i).click();
//            }
//
//            System.out.println("Pierwsze 8 pozycji na liście zamówień zostało wyczyszczone.");
//        } else {
//            System.out.println("Nie ma wystarczającej liczby pozycji na liście zamówień do usunięcia.");
//        }
//
//        // Sprawdź, czy element listy zamówień jest widoczny
//        assertTrue(orderElement.isDisplayed(), "Twoje zamówienie jest na liście.");
//
//        assertFalse(orderElement.isDisplayed(), "Twoje zamówienie nie jest na liście.");
//        // Status zamówienia
//        String expectedStatus = "Awaiting check payment";
//
//        // Sprawdzenie, czy zamówienie jest wymienione z oczekiwanym statusem
//        WebElement orderElement = driver.findElement(By.xpath("//span[@class='label label-pill bright' and normalize-space()='" + expectedStatus + "']"));
//        if (orderElement != null) {
//            System.out.println("Zamówienie jest wymienione z oczekiwanym statusem: " + expectedStatus);
//        } else {
//            System.out.println("Zamówienie nie jest wymienione z oczekiwanym statusem: " + expectedStatus);
//        }
//        orderWith.sendKeys("Order with an obligation to pay");
//        WebElement labelElem = driver.findElement(By.xpath("//label[@class='js-terms']"));
//        labelElem.setText("Order with an obligation to pay");

//        WebElement element = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
//        WebElement element = driver.findElement(By.xpath("//label[@class='js-terms']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        Thread.sleep(3000);
//        // wyczyść tekst elementu
//        orderWith.clear();

//        // wprowadź nowy tekst do elementu
//        orderWith.sendKeys("Order with an obligation to pay");























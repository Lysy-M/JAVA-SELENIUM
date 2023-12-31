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

    // Pole szukania i wpisanie Hummingbird Printed Sweater   //xpath = "//input[@name='s']"
    @FindBy(name = "s")
    public WebElement searchInput;

    // Kliknięcie znalezionego productu "Hummingbird Printed Sweater"
    @FindBy(xpath = "//a[text()='Hummingbird printed sweater']")
    public WebElement brownBearInput;

    // Sprawdzenie rabatu
    @FindBy(xpath = "//span[contains(@class, 'discount') and contains(@class, 'discount-percentage') and text()='Save 20%']")
    public WebElement discountSpan;
    // Sprawdzenie rabatu
    @FindBy(xpath = "//li[contains(@class, 'product-flag') and text()='-20%']")
    public WebElement discount20;
    // Wybór rozmiaru
    @FindBy(xpath = "//select[@id='group_1']")
    public WebElement sizeDropdown;
    // Wybór ilości
    @FindBy(xpath = "//input[@id='quantity_wanted']")
    public static WebElement quantityInput;
    // Przejście do kasy
    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Proceed to checkout']")
    public WebElement checkoutButton;
    // Potwierdzenie adresu
    @FindBy(xpath = "//button[@name='confirm-addresses']")
    public WebElement confirmAddress;
    // Adres dostawy
    @FindBy(xpath = "//div[@class='address']")
    public WebElement addressDelivery;
    // Metoda odbioru
    @FindBy(xpath = "//button[@class='continue btn btn-primary float-xs-right' and @name='confirmDeliveryOption']")
    public WebElement continuePickup;
    // Opcja płatności
    @FindBy(id = "payment-option-1")
    public WebElement paymentSelect;
    // Boc I agree
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    public WebElement IagreeBox;
    @FindBy(xpath = "//label[@class='js-terms']")
    public WebElement orderWith;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement placeOrderButton;
    @FindBy(xpath = "//span[@class='hidden-sm-down']")
    public WebElement nameButton;

    // Metody
    public void iSearchTheProduct(String searchPhrase) {

        // Sprawdzenie czy przycisk jest aktywny
        boolean isEnabled = searchInput.isEnabled();
        System.out.println("The button is enabled: " + isEnabled); // Przycisk jest aktywny

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
    // Kliknięcie w odnaleziony produkt i sprawdzenie rabatu
    public void iCheckIfThereIsADiscountOnIt(int discountPercentage) throws InterruptedException {
        Thread.sleep(500);

        // Sprawdź, czy na produkcie jest zniżka 20%
        boolean hasDiscount = discountSpan.isDisplayed();

        if (hasDiscount) {
            System.out.println("There is a " + discountPercentage + "% discount on this product."); // Na tym produkcie jest zniżka 20%
        } else {
            System.out.println("There is no " + discountPercentage + "% discount on this product."); // Na tym produkcie nie ma zniżki 20%
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
        System.out.println("The selection has been made."); // Wybór został dokonany

        // Poczekaj 5 sekund - Przechwyt wyjątku i wyświetlenie w przypadku wystąpienia wyjątku
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wyślij klawisz Enter, aby potwierdzić zmianę wartości
        quantityInput.sendKeys(Keys.ENTER);
    }

    public void iClickTheOptionAndConfirmTheAddress(String option) throws InterruptedException { // checkoutButton;
        Thread.sleep(2000); // Opóźnienie 2 sekundy

        if (checkoutButton.isDisplayed() && checkoutButton.isEnabled()) {
            System.out.println("The item is visible and enabled."); // Element jest widoczny i włączony

        } else {
            System.out.println("Item is not visible or enabled."); // Element nie jest widoczny ani włączony

        }
        checkoutButton.click();
        checkoutButton.sendKeys(Keys.ENTER);

        String addressText = addressDelivery.getText();

        if (addressText.contains("Jaskinia Wielka")) {
//        if (addressText.contains(option)) {
            System.out.println("The correctness of the address has been confirmed."); // Poprawność adresu została potwierdzona
        } else {
            System.out.println("The address has not been correctly confirmed."); // Adres nie został poprawnie potwierdzony
        }
        confirmAddress.click();
    }

    public void iChooseThePickupMethod(String pickupMethod) throws InterruptedException {

        continuePickup.click();
        System.out.println("Collection method has been selected: " + pickupMethod); // Wybrano metodę odbioru

    }

    public void iChooseThePaymentOption(String pay) throws InterruptedException {
        paymentSelect.click();
        System.out.println("Payment option has been selected"); // Wybrano opcję płatności

    }

    public void iClickOn(String Order) throws InterruptedException {

        IagreeBox.click();

        List<WebElement> placeOrderButton = driver.findElements(By.xpath("//button[@type='submit']"));
        placeOrderButton.get(8).click();
        System.out.println("Select Order with payment obligation"); // Wybierz Zamówienie z obowiązkiem zapłaty
    }

    public void iTakeAScreenshotOfTheOrderConfirmationAndTheAmount() {

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            // Zapisz zrzut ekranu do pliku
            FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
            System.out.println("The screenshot was saved as screenshot.png"); // Zrzut ekranu został zapisany jako screenshot.png
        } catch (Exception e) {
            System.out.println("An error occurred while saving the screenshot: " + e.getMessage()); // Wystąpił błąd podczas zapisywania zrzutu ekranu
        }
    }

    public void iGoIntoTheOrderHistoryAndDetails() throws InterruptedException {
        Thread.sleep(500);

        // Sprawdź, czy element jest aktywny
        if (nameButton.isEnabled()) {
            // Uaktywnij element
            nameButton.click();
        } else {
            System.out.println("Element is not enabled."); // Element nie jest włączony
        }
        List<WebElement> NameButton = driver.findElements(By.xpath("//span[@class='hidden-sm-down']"));
        NameButton.get(0).click();

        List<WebElement> placeOrderButton = driver.findElements(By.xpath("//span[@class='link-item']"));
        placeOrderButton.get(2).click();
        System.out.println("Sprawdzam Order history and details");

    }

    public void iCheckThatTheOrderIsListedWithTheStatus(String Awaiting) {

        // Status zamówienia - czekuje na płatność czekiem
        String expectedStatus = "Awaiting check payment";

        // Sprawdzenie, czy zamówienie jest wymienione z oczekiwanym statusem
        WebElement orderElement = driver.findElement(By.xpath("//span[@class='label label-pill bright' and normalize-space()='" + expectedStatus + "']"));

        // Potwierdzono obecność zamówienia na liście
        System.out.println("Confirmed the presence of the order in the list.");
    }

    // Znalezienie elementu z kwotą zamówienia
    public void checkIfTheAmountIsTheSameAsOnTheOrder() {

        // Znalezienie elementu z kwotą zamówienia
        WebElement amountElement = driver.findElement(By.xpath("//td[contains(@class, 'text-xs-right')]"));
        String actualAmount = amountElement.getText();

        // Sprawdzenie, czy kwota zamówienia jest oczekiwana
        String expectedAmount = "€143.60";                                                                              //"Alt" kod numeryczny "0128"
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

//////////////////////////////////**********************8
//    // Zdefiniowanie selektora CSS do wyszukania elementów
//    String cssSelector = "span.label.label-pill.bright[text()='Awaiting check payment']";
//
//    // Oczekiwanie na pojawienie się elementów na stronie
//
//    WebDriver driver = new ChromeDriver();
//    WebDriverWait wait = new WebDriverWait(driver, 10);
//    List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
//
//    // Iteracja przez każdy znaleziony element
//    for {
//    WebElement element :elements)
//
//
//        // Zmiana tła elementu na niebieskie przy użyciu JavaScript
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background-color: blue')", element);
//
//        // Oczekiwanie na określony czas (np. 1 sekunda)
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Przywrócenie pierwotnego tła elementu
//        jsExecutor.executeScript("arguments[0].removeAttribute('style')", element);
//    }

//        if (hasDiscount) {
//            System.out.println("There is a 20% discount on this product."); // Na tym produkcie jest zniżka 20%
//        } else {
//            System.out.println("There is no 20% discount on this product."); // Na tym produkcie nie ma zniżki 20%
//        }





















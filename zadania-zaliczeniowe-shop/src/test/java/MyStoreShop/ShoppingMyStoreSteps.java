package MyStoreShop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ShoppingMyStoreSteps {
    private static WebDriver driver;


    private LoginPage loginPage;
    private ShoppingPage ShoppingPage;

    // Biorąc pod uwagę, że jestem zalogowany na swoje konto
    @Given("I am logged into my account") // Logowanie na stronie MyStore
    public void iAmLoggedIntoMyAccount() {
        // Inicjalizacja przeglądarki
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication");         //https://mystore-testlab.coderslab.pl/index.php?controller=authentication
        loginPage.loginAs("xqorffjbngrmdoiuyd@test.com", "CodersLab");                            //kxlywnwku@bbitf.com - alibabali
    }
    // Wyszukuję produkt "Sweter z nadrukiem Hummaning"
    @When("I search the product {string}")
    public void iSearchTheProduct(String searchPhrase) {
        // Wyszukiwanie i wybór produktu
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iSearchTheProduct();
    }
    //  I sprawdzam, czy jest na niego 20% zniżki
    @And("I check if there is a {int}% discount on it")
    public void iCheckIfThereIsADiscountOnIt(int discountPercentage) throws InterruptedException {

        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iCheckIfThereIsADiscountOnIt();
    }
    // Wybieram rozmiar i ilość
    @And("I choose the size and quantity")
    public void iChooseTheSizeAndQuantity() throws InterruptedException {

        ShoppingPage = new ShoppingPage(driver);
        ShoppingMyStoreSteps shoppingPage;
        ShoppingPage.iChooseTheSizeAndQuantity();

    }
    // Klikam opcję "Do kasy" i potwierdzam adres.
    @And("I click the {string} option and confirm the address.")
    public void iClickTheOptionAndConfirmTheAddress(String option) throws InterruptedException {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iClickTheOptionAndConfirmTheAddress();

    }
    // Wybieram metodę odbioru "Odbiór w sklepie".
    @And("I choose the {string} pickup method")
    public void iChooseThePickupMethod(String pickupMethod) throws InterruptedException {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iChooseThePickupMethod(pickupMethod);


    }
    //  Wybieram opcję płatności "Zapłać czekiem".
    @And("I choose the payment option {string}")
    public void iChooseThePaymentOption(String arg0) throws InterruptedException {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iChooseThePaymentOption();
    }
    // Klikam "Zamówienie z obowiązkiem zapłaty".
    @And("I click on {string}")
    public void iClickOn(String arg0) throws InterruptedException {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iClickOn();

    }
    // Robię zrzut ekranu z potwierdzeniem zamówienia i kwotą.
    @And("I take a screenshot of the order confirmation and the amount")
    public void iTakeAScreenshotOfTheOrderConfirmationAndTheAmount() {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iTakeAScreenshotOfTheOrderConfirmationAndTheAmount();

    }
    // Następnie przechodzę do historii i szczegółów zamówienia
    @And("I go into the order history and details")
    public void iGoIntoTheOrderHistoryAndDetails() throws InterruptedException {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iGoIntoTheOrderHistoryAndDetails();

    }
    // Następnie sprawdzam, czy zamówienie ma status "Oczekuje na płatność czekiem".
    @Then("I check that the order is listed with the status {string}")
    public void iCheckThatTheOrderIsListedWithTheStatus(String arg0) {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.iCheckThatTheOrderIsListedWithTheStatus();

    }
        // Sprawdzam, czy kwota jest taka sama jak w zamówieniu.
    @And("Check if the amount is the same as on the order")
    public void checkIfTheAmountIsTheSameAsOnTheOrder() {
        ShoppingPage = new ShoppingPage(driver);
        ShoppingPage.checkIfTheAmountIsTheSameAsOnTheOrder();
    }
}














//// Wyświetl informację po dokonaniu wyboru
//        String selectedQuantity = quantityInput.getAttribute("value");
//        System.out.println("Wybrana ilość: " + selectedQuantity);
//
//// Sprawdź, czy wybrana ilość mieści się w zakresie dostępnych wartości
//        int min = Integer.parseInt(minQuantity);
//        int max = Integer.parseInt(maxQuantity);
//        int quantity = Integer.parseInt(selectedQuantity);
//
//        if (quantity >= min && quantity <= max) {
//            System.out.println("Wybrana ilość mieści się w zakresie dostępnych wartości.");
//        } else {
//            System.out.println("Wybrana ilość nie mieści się w zakresie dostępnych wartości.");
//        }
//    }

//
//// Pobierz informacje o dodanym produkcie
//        WebElement productTitle = driver.findElement(By.xpath("//h2[@class='product-title']"));
//        String productName = productTitle.getText();
//
//// Wyświetl informację o dodaniu produktu do koszyka
//        System.out.println("Produkt " + productName + " został dodany do koszyka.");






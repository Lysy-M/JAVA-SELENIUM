@shopping
Feature: Shopping at MyStore

  @create
#  Kupuję produkty
  Scenario: I buy products
#  Biorąc pod uwagę, że jestem zalogowany na swoje konto
    Given I am logged into my account
#  Kiedy wyszukuję produkt "Sweter z nadrukiem Hummaning"
    When I search the product "Hummaning Printed Sweater"
#  I sprawdzam, czy jest na niego 20% zniżki
    And I check if there is a 20% discount on it
#  Wybieram rozmiar i ilość
    And I choose the size and quantity
#  Klikam opcję "Do kasy" i potwierdzam adres.
    And  I click the "To checkout" option and confirm the address.
#  Wybieram metodę odbioru "Odbiór w sklepie".
    And I choose the "Pick up in store" pickup method
#  Wybieram opcję płatności "Zapłać czekiem".
    And I choose the payment option "Pay by check"
#  Klikam "Zamówienie z obowiązkiem zapłaty".
    And I click on "Order with an obligation to pay"
#  Robię zrzut ekranu z potwierdzeniem zamówienia i kwotą.
    And I take a screenshot of the order confirmation and the amount
#  Następnie przechodzę do historii i szczegółów zamówienia
    And I go into the order history and details
#  Następnie sprawdzam, czy zamówienie ma status "Oczekuje na płatność czekiem".
    Then I check that the order is listed with the status "Awaiting check payment"
#  I sprawdzam, czy kwota jest taka sama jak w zamówieniu.
    And Check if the amount is the same as on the order


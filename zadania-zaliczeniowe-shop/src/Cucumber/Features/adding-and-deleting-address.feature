@shop
Feature: Adding and deleting an address

  @create
#  Dodawanie nowego adresu
  Scenario Outline: Adding a new address
#  Użytkownik loguje się na swoje konto
    Given The user logs into their account
#  Gdy użytkownik przejdzie do sekcji Adresy
    When The user goes to the Addresses section
#  Wprowadza alias...
    And I Enter alias <alias> address <address>  city <city> zip code <zip code> country <country> and phone <phone>
#  Następnie sprawdza, czy dodany adres jest poprawny
#    Then Checks if the added address is correct
    Then Checks if the added address is correct with alias "<alias>" address "<address>" city "<city>" zip code "<zip code>" country "<country>" and phone "<phone>"
#  Zapisuję i zamykam przeglądarkę
    And I save and close the browser
    Examples:
      | alias           | address         | city             | zip code | country        | phone     |
      | Piotruś Pan     | Zaczarowany Las | Marchewkowe Pole | 12-999   | United Kingdom | 111222999 |
#      | Kuba Rozpruwacz | Jaskinia Wielka | Dolina Wielka | 12-666   | United Kingdom | 15643566 |
  @delete
#  Usuwanie adresu
  Scenario: Deleting an address
#  Użytkownik loguje się na swoje konto
    Given The user logs into their account
#  Użytkownik przechodzi do sekcji Adresy
    When User navigates to the Addresses section
#  Użytkownik usuwa dodany adres
    And User deletes the added address
#  Następnie sprawdza, czy adres został usunięty
    Then Checks if the address has been deleted
#  Zapisuje i zamyka przeglądarkę
    And I save and quit the browser
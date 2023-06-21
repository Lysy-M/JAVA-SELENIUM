@shop
Feature: Adding and deleting an address

  @create
  Scenario Outline: Adding a new address
    Given The user logs into their account
    When The user goes to the Addresses section
    And I Enter alias <alias> address <address>  city <city> zip code <zip code> country <country> and phone <phone>
    Then Checks if the added address is correct
    And I save and close the browser
    Examples:
      | alias           | address         | city          | zip code | country  | phone    |
      | Kuba Rozpruwacz | Jaskinia wielka | Dolina Wielka | 12-666   | Testland | 15643566 |

  @delete
  Scenario: Deleting an address
    Given The user logs into their account
    When User navigates to the Addresses section
    And User deletes the added address
    Then Checks if the address has been deleted
    And I save and quit the browser
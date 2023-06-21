@shopping
Feature: Shopping at MyStore
  @create
  Scenario: I buy products
    Given I am logged into my account
    When I search the product "Hummaning Printed Sweater"
    And I check if there is a 20% discount on it
    And I choose the size and quantity
    And  I click the "To checkout" option and confirm the address.
    And I choose the "Pick up in store" pickup method
    And I choose the payment option "Pay by check"
    And I click on "Order with an obligation to pay"
    And I take a screenshot of the order confirmation and the amount
    And I go into the order history and details
    Then I check that the order is listed with the status "Awaiting check payment"
    And Check if the amount is the same as on the order


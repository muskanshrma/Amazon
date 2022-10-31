Feature: Add product to cart and change quantity

  Background:
    Given User is on Homepage
    When User search for product
    And User clicks on product
    And User adds product to cart

  Scenario: Search for a product and Add it to cart
    Then Product is added to cart successfully
    And User increases the quantity

  Scenario: Search for a product and Add it to cart
    Then Product is added to cart successfully
    And User clicks on proceed to buy button
    And User enters account details
      | Email/Phone number             | abc@gmail.com       |
      | password                       | abc123              |
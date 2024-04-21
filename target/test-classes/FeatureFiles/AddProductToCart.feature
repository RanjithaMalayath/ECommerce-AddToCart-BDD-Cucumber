
@AddItemsToCart
Feature: Add items to cart and verify subtotal
  I want to use this template to verify adding items to cart and validate item price and subtotal in cart

  @AddMonitor
  Scenario: Adding a Monitor to cart and verfying price
    Given I am on Amazon.in page
    Then I verify expected page is loaded
    When I type "Monitor" in the search field and press Enter
    And I get the price of the item by position "2"
    And I select item "1" from the list
    And I add the item "1" to the cart by clicking on Add to Cart button
    Then I should validate "Added to Cart" success message
    When I open the cart
    Then The price in the cart should match the price on the product page
    And The subtotal in the cart should match the total on the product page
    
  @AddLaptop
  Scenario: Adding a Laptop to cart and verfying price
    Given I am on Amazon.in page
    Then I verify expected page is loaded
    When I type "Laptop" in the search field and press Enter
    And I get the price of the item by position "3"
    And I select item "2" from the list
    And I add the item "2" to the cart by clicking on Add to Cart button
    Then I should validate "Added to Cart" success message
    When I open the cart
    Then The price in the cart should match the price on the product page
    And The subtotal in the cart should match the total on the product page

  @AddHeadphoneAndKeyboard
  Scenario Outline: Adding a Headphone and Keyboard to cart and verfying subtotal
    Given I am on Amazon.in page
    Then I verify expected page is loaded
    When I type item <item1> in the search field and press Enter
    And I get the price of the item by position "2"
    And I select item "1" from the list
    And I add the item "1" to the cart by clicking on Add to Cart button
    Then I should validate "Added to Cart" success message
    When I open the cart
    When I type item <item2> in the search field and press Enter
    And I get the price of the item by position "2"
    And I select item "1" from the list
    And I add the item "1" to the cart by clicking on Add to Cart button
    Then I should validate "Added to Cart" success message
    When I open the cart
    And The subtotal in the cart should match the total on the product page


  Examples: 
  | item1     | item2     |
  | Headphone | Keyboard  |
  
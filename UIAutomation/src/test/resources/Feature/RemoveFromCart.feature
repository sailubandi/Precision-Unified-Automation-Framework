Feature: Cart Functionality

  Scenario: Verify product removal from cart

 	Given user launches browser
    And user navigates to application
    Then home page should be visible

    When user adds products to cart
    And user clicks Cart button

    Then cart page should be displayed

    When user clicks X button of a product

    Then product should be removed from cart
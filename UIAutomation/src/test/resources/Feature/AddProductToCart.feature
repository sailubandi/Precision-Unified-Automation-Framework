Feature: Add Products to Cart and Verify

  Scenario: Add products to cart and verify details
    Given user launches browser
    And user navigates to application
    Then home page should be visible
    When user clicks on Products button
    And user hovers over first product and adds to cart
    And user clicks Continue Shopping button
    And user hovers over second product and adds to cart
    And user clicks View Cart button
    Then both products should be visible in cart
    And verify their prices quantity and total price
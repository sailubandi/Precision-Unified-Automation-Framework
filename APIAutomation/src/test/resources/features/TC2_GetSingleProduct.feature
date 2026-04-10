Feature: TC2 - Get Single Product Details

  As a user of the automation exercise application
  I want to search for specific products
  So that I can find products that match my criteria

  Background:
    Given the API endpoint is available for products

  @TC2
  Scenario: Get Single Product Details by Search - TC2
    When I make a POST request to "/api/searchProduct" with search parameter "top"
    Then the response status code should be 200
    And the response should contain searched products
    And the response JSON schema should be valid for search results
    And the response code in body should be 200

  @TC2
  Scenario Outline: Get Single Product Details with Different Search Terms - TC2
    When I make a POST request to "/api/searchProduct" with search parameter "<searchTerm>"
    Then the response status code should be 200
    And the response should contain searched products for "<searchTerm>"
    And the response JSON schema should be valid for search results
    And the response code in body should be 200

    Examples:
      | searchTerm |
      | top        |
      | tshirt      |
      | jean        |

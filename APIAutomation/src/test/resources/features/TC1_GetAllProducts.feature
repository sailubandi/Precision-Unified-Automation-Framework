Feature: TC1 - Get All Products List

  As a user of the automation exercise application
  I want to retrieve all products from the catalog
  So that I can view the complete product inventory

  Background:
    Given the API endpoint is available for products

  @TC1
  Scenario: Get All Products List - TC1
    When I make a GET request to "${api.endpoint.products.list}"
    Then the response status code should be 200
    And the response should contain a valid products list
    And the response JSON schema should be valid for products list
    And the response code in body should be 200

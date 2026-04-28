Feature: TC6 - Negative Validation

  As a user of the automation exercise application
  I want to ensure proper error handling
  So that invalid inputs return appropriate error responses

  Background:
    Given the API endpoint is available for user account operations

  @TC6 @Negative
  Scenario: Negative Validation - Invalid Email - TC6
    When I make a POST request to "/api/createAccount" with invalid email from configuration
    Then the response status code should be 200
    And the response should contain error message about invalid email
    And the response JSON schema should be valid for error response

  @TC6 @Negative
  Scenario: Negative Validation - Missing Required Fields - TC6
    When I make a POST request to "/api/createAccount" with missing required fields
      | name | email | password | title | birth_date | birth_month | birth_year | firstname | lastname | company | address1 | country | zipcode | state | city | mobile_number |
      |      |       |          |        |            |             |             |            |           |          |          |         |         |        |       |              |
    Then the response status code should be 200
    And the response should contain error message about email already exists
    And the response JSON schema should be valid for error response

  @TC6 @Negative
  Scenario: Negative Validation - Invalid Search Parameter - TC6
    When I make a POST request to "/api/searchProduct" with search parameter "nonexistentproduct123"
    Then the response status code should be 200
    And the response should contain empty search results
    And the response JSON schema should be valid for search results

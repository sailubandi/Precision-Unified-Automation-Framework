Feature: TC5 - Update User Account

  As a registered user of the automation exercise application
  I want to update my account information
  So that I can keep my personal details current

  Background:
    Given the API endpoint is available for user account operations

  @TC5
  Scenario: Update User Account - TC5
    When I make a PUT request to "/api/updateAccount" with updated user details from configuration
    Then the response status code should be 200
    And the response message should be "Account not found!"
    And the response JSON schema should be valid for user update
    And the response code in body should be 404

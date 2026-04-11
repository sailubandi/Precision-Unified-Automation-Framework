Feature: TC4 - Delete User Account

  As a registered user of the automation exercise application
  I want to delete my account
  So that I can remove my personal data from the system

  Background:
    Given the API endpoint is available for user account operations

  @TC4
  Scenario: Delete User Account - TC4
    When I make a DELETE request to "/api/deleteAccount" with user credentials from configuration
    Then the response status code should be 200
    And the response message should be "Account not found!"
    And the response JSON schema should be valid for account deletion
    And the response code in body should be 404

Feature: TC4 - Delete User Account

  As a registered user of the automation exercise application
  I want to delete my account
  So that I can remove my personal data from the system

  Background:
    Given the API endpoint is available for user account operations

  @TC4
  Scenario: Delete User Account Successfully - TC4
    Given I have a newly created user for deletion
    When I make a DELETE request to "${api.endpoint.user.delete}" with user credentials from configuration
    Then the user deletion response status code should be 200
    And the user deletion response message should be "Account deleted!"
    And the response JSON schema should be valid for account deletion

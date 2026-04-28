Feature: TC5 - Update User Account

  As a registered user of the automation exercise application
  I want to update my account information
  So that I can keep my personal details current

  Background:
    Given the API endpoint is available for user account operations

  @TC5 @Positive
  Scenario: Update User Account Successfully - TC5
    Given I have a newly created user for update
    When I update the user account with the following details:
      | key         | value      |
      | email       | newemail   |
      | password    | newpassword|
      | name        | newname    |
      | phone       | newphone   |
    Then the user update response status code should be 200
    And the user update response message should be "User updated!"
    And the response JSON schema should be valid for user update

  @TC5 @Negative
  Scenario: Update User Account - TC5
    When I make a PUT request to "/api/updateAccount" with updated user details from configuration
    Then the user update response status code should be 200
    And the user update response message should be "Account not found!"
    And the response JSON schema should be valid for user update
    And the response code in body should be 404

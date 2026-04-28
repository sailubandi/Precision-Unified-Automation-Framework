Feature: TC5 - Update User Account

  As a registered user of the automation exercise application
  I want to update my account information
  So that I can keep my personal details current

  Background:
    Given the API endpoint is available for user account operations

  @TC5
  Scenario: Update User Account Successfully - TC5
    Given I have a newly created user for update
    When I make a PUT request to "${api.endpoint.user.update}" with updated user details from configuration with the following details:
      | key         | value                    |
      | name        | ${test.user.update.name} |
      | phone       | ${test.user.update.phone}|
    Then the user update response status code should be 200
    And the user update response message should be "User updated!"
    And the response JSON schema should be valid for user update

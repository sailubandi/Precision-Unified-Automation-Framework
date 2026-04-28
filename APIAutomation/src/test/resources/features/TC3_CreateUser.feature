Feature: TC3 - Create User Account

  As a new user of automation exercise application
  I want to create a new account
  So that I can register and use the application

  Background:
    Given the API endpoint is available for user account operations

  @TC3
  Scenario: Create New User Account Successfully - TC3
    When I make a POST request to "${api.endpoint.user.create}" with valid user details
    Then the response status code should be 200
    And the user creation response message should be "User created!"
    And the response JSON schema should be valid for user creation

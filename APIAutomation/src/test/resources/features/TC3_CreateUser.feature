Feature: TC3 - Create User Account

  As a new user of automation exercise application
  I want to create a new account
  So that I can register and use the application

  Background:
    Given the API endpoint is available for user account operations

  @TC3 @Positive
  Scenario: Create New User Account Successfully - TC3
    When I create a new user account
    Then the response status code should be 201
    And the user creation response message should be "User created!"
    And the response JSON schema should be valid for user creation

  @TC3 @Negative
  Scenario: Create User Account - Email Already Exists - TC3
    When I make a POST request to "/api/createAccount" with valid user details from configuration
    Then the response status code should be 200
    And the user creation response message should be "Email already exists!"
    And the response JSON schema should be valid for user creation
    And the response code in body should be 400

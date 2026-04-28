Feature: InvalidLogin

  Scenario: Login with incorrect credentials

    Given user launches browser
    And user navigates to application
    Then home page should be visible

    When user clicks on Signup Login button
    Then Login to your account text should be visible

    When user enters incorrect email and password 
    And user clicks on login button
    Then error message should be displayed
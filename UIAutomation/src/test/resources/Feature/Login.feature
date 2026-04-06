Feature: Login and Delete Account

  Scenario: User logs in and deletes account successfully

    Given user launches browser
    And user navigates to application
    Then home page should be visible

    When user clicks on Signup Login button
    Then Login to your account text should be visible

    When user enters correct email and password 
    And user clicks on login button
    Then Logged in as username should be visible

    When user clicks Delete Account button
    Then ACCOUNT DELETED should be visible
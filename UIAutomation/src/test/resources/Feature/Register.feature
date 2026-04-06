Feature: Register User

Scenario: Register successfully

	Given user launches browser
	When user navigates to application
	Then home page should be visible
	
	When user clicks on Signup Login button
	Then New User Signup should be visible
	
	When user enters name and email
	And clicks Signup button
	
	When user fills account information
	And user selects newsletter and offers
	And user fills address details
	And user clicks Create Account button
	
	Then ACCOUNT CREATED should be visible
	
	When user clicks Continue button
	Then Logged in as username should be visible
	
	When user clicks Delete Account button
	Then ACCOUNT DELETED should be visible
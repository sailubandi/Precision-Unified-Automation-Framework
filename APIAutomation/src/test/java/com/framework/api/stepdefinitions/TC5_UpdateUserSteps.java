package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import client.ApiClient;
import com.framework.api.factory.UserDataFactory;
import com.framework.api.pojo.User;
import com.framework.api.validator.ResponseValidator;
import utils.LoggerUtil;

/**
 * Step definitions for TC5 - Update User Account
 */
public class TC5_UpdateUserSteps {

    private ApiClient apiClient;
    private User currentUser;

    public TC5_UpdateUserSteps() {
        this.apiClient = new ApiClient();
    }

    @Given("I have a newly created user for update")
    public void i_have_a_newly_created_user_for_update() {
        LoggerUtil.logInfo("Starting setup: Creating user for update scenario");
        
        // Create a new user for this scenario
        currentUser = UserDataFactory.createUniqueUser();
        
        LoggerUtil.logApiRequest("POST", "/api/createAccount", currentUser.toString());
        Response createResponse = apiClient.createUser("/api/createAccount", currentUser);
        LoggerUtil.logApiResponse(createResponse.getStatusCode(), 0);
        
        // Validate user creation was successful
        if (createResponse.getStatusCode() == 201) {
            LoggerUtil.logInfo("User creation successful for setup: " + currentUser.getEmail());
        } else {
            LoggerUtil.logError("User creation failed for setup: " + createResponse.getBody().asString());
            throw new RuntimeException("Failed to create user for update scenario");
        }
    }

    @When("I update the same user account")
    public void i_update_the_same_user_account() {
        LoggerUtil.logInfo("STEP: Updating user account with email: " + currentUser.getEmail());
        
        // Update user with new details
        User updatedUser = UserDataFactory.createUpdatedUser();
        currentUser.setName(updatedUser.getName());
        currentUser.setEmail(updatedUser.getEmail()); // Keep same email
        currentUser.setPassword(updatedUser.getPassword());
        currentUser.setTitle(updatedUser.getTitle());
        currentUser.setBirth_date(updatedUser.getBirth_date());
        currentUser.setBirth_month(updatedUser.getBirth_month());
        currentUser.setBirth_year(updatedUser.getBirth_year());
        currentUser.setFirstname(updatedUser.getFirstname());
        currentUser.setLastname(updatedUser.getLastname());
        currentUser.setCompany(updatedUser.getCompany());
        currentUser.setAddress1(updatedUser.getAddress1());
        currentUser.setAddress2(updatedUser.getAddress2());
        currentUser.setCountry(updatedUser.getCountry());
        currentUser.setZipcode(updatedUser.getZipcode());
        currentUser.setState(updatedUser.getState());
        currentUser.setCity(updatedUser.getCity());
        currentUser.setMobile_number(updatedUser.getMobile_number());
        
        LoggerUtil.logInfo("STEP: Making API request to update user with new details");
        CommonSteps.response = apiClient.updateUser("/api/updateAccount", currentUser);
        LoggerUtil.logInfo("STEP: User update request completed");
    }

    @When("I make a PUT request to {string} with updated user details")
    public void i_make_a_put_request_to_with_updated_user_details(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUpdatedUser();
        CommonSteps.response = apiClient.updateUser(endpoint, user);
    }

    @When("I make a PUT request to {string} with updated user details from configuration")
    public void i_make_a_put_request_to_with_updated_user_details_from_configuration(String endpoint) {
        User user = UserDataFactory.createUpdatedUser();
        CommonSteps.response = apiClient.updateUser(endpoint, user);
    }

    @Then("user update response status code should be 200")
    public void user_update_response_status_code_should_be_200() {
        ResponseValidator.validateStatusCode(CommonSteps.response, 200);
        LoggerUtil.logInfo("Validated 200 status code for user update");
    }

    @Then("user update response message should be {string}")
    public void user_update_response_message_should_be(String expectedMessage) {
        String actualMessage = CommonSteps.response.jsonPath().getString("message");
        LoggerUtil.logInfo("Expected message: " + expectedMessage + ", Actual message: " + actualMessage);
        assert actualMessage != null : "Response message should not be null";
        assert actualMessage.contains(expectedMessage) : 
            "Message mismatch. Expected: " + expectedMessage + ", Actual: " + actualMessage;
    }

    @And("response JSON schema should be valid for user update")
    public void response_json_schema_should_be_valid_for_user_update() {
        ResponseValidator.validateUserUpdateSuccess(CommonSteps.response);
        LoggerUtil.logInfo("Validated user update response schema");
    }

}

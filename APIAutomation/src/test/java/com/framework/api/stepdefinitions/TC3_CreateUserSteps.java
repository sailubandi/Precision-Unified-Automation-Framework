package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
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
 * Step definitions for TC3 - Create User Account
 */
public class TC3_CreateUserSteps {

    private ApiClient apiClient;

    public TC3_CreateUserSteps() {
        this.apiClient = new ApiClient();
    }

    @When("I create a new user account")
    public void i_create_a_new_user_account() {
        LoggerUtil.logInfo("STEP: Creating new user account");
        User user = UserDataFactory.createUniqueUser();
        
        LoggerUtil.logInfo("STEP: Making API request to create user with email: " + user.getEmail());
        CommonSteps.response = apiClient.createUser("/api/createAccount", user);
        
        LoggerUtil.logInfo("STEP: User creation request completed");
        LoggerUtil.logApiResponse(CommonSteps.response.getStatusCode(), 0);
        LoggerUtil.logInfo("Created user with email: " + user.getEmail());
    }

    @When("I make a POST request to {string} with valid user details from configuration")
    public void i_make_a_post_request_to_with_valid_user_details_from_configuration(String endpoint) {
        User user = UserDataFactory.createValidUser();
        CommonSteps.response = apiClient.createUser(endpoint, user);
    }

    @Then("response status code should be 201")
    public void response_status_code_should_be_201() {
        ResponseValidator.validateStatusCode(CommonSteps.response, 201);
        LoggerUtil.logInfo("Validated 201 status code for user creation");
    }

    @Then("user creation response message should be {string}")
    public void user_creation_response_message_should_be(String expectedMessage) {
        String actualMessage = CommonSteps.response.jsonPath().getString("message");
        LoggerUtil.logInfo("Expected message: " + expectedMessage + ", Actual message: " + actualMessage);
        assert actualMessage != null : "Response message should not be null";
        assert actualMessage.contains(expectedMessage) : 
            "Message mismatch. Expected: " + expectedMessage + ", Actual: " + actualMessage;
    }

    @And("response JSON schema should be valid for user creation")
    public void the_response_json_schema_should_be_valid_for_user_creation() {
        ResponseValidator.validateUserCreationSuccess(CommonSteps.response);
        LoggerUtil.logInfo("Validated user creation response schema");
    }

    
}

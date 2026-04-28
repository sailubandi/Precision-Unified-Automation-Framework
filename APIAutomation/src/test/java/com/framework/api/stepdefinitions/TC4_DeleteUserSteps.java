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
 * Step definitions for TC4 - Delete User Account
 */
public class TC4_DeleteUserSteps {

    private ApiClient apiClient;
    private User currentUser;

    public TC4_DeleteUserSteps() {
        this.apiClient = new ApiClient();
    }

    @Given("I have a newly created user for deletion")
    public void i_have_a_newly_created_user_for_deletion() {
        LoggerUtil.logInfo("Starting setup: Creating user for delete scenario");
        
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
            throw new RuntimeException("Failed to create user for delete scenario");
        }
    }

    @When("I delete the same user account")
    public void i_delete_the_same_user_account() {
        LoggerUtil.logInfo("STEP: Deleting user account with email: " + currentUser.getEmail());
        
        CommonSteps.response = apiClient.deleteUser("/api/deleteAccount", currentUser);
        LoggerUtil.logInfo("STEP: User deletion request completed");
    }

    @When("I make a DELETE request to {string} with user credentials")
    public void i_make_a_delete_request_to_with_user_credentials(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUserCredentials();
        CommonSteps.response = apiClient.deleteUser(endpoint, user);
    }

    @When("I make a DELETE request to {string} with user credentials from configuration")
    public void i_make_a_delete_request_to_with_user_credentials_from_configuration(String endpoint) {
        User user = UserDataFactory.createUserCredentials();
        CommonSteps.response = apiClient.deleteUser(endpoint, user);
    }

    @Then("user deletion response status code should be 200")
    public void user_deletion_response_status_code_should_be_200() {
        ResponseValidator.validateStatusCode(CommonSteps.response, 200);
        LoggerUtil.logInfo("Validated 200 status code for user deletion");
    }

    @Then("user deletion response message should be {string}")
    public void user_deletion_response_message_should_be(String expectedMessage) {
        String actualMessage = CommonSteps.response.jsonPath().getString("message");
        LoggerUtil.logInfo("Expected message: " + expectedMessage + ", Actual message: " + actualMessage);
        assert actualMessage != null : "Response message should not be null";
        assert actualMessage.contains(expectedMessage) : 
            "Message mismatch. Expected: " + expectedMessage + ", Actual: " + actualMessage;
    }

    @And("response JSON schema should be valid for account deletion")
    public void _response_json_schema_should_be_valid_for_account_deletion() {
        ResponseValidator.validateUserDeletionSuccess(CommonSteps.response);
        LoggerUtil.logInfo("Validated account deletion response schema");
    }

}

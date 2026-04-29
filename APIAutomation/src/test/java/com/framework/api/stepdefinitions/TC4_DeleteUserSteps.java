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
import config.ConfigReader;

/**
 * Step definitions for TC4 - Delete User Account
 */
public class TC4_DeleteUserSteps {

    private ApiClient apiClient;
    private User currentUser;

    public TC4_DeleteUserSteps() {
        this.apiClient = new ApiClient();
    }
    
    /**
     * Resolves configuration placeholders in the endpoint
     * @param endpoint The endpoint that may contain placeholders like ${config.key}
     * @return Resolved endpoint from configuration or original endpoint if no placeholder
     */
    private String resolveEndpoint(String endpoint) {
        if (endpoint == null) {
            return null;
        }
        
        if (endpoint.startsWith("${") && endpoint.endsWith("}")) {
            String configKey = endpoint.substring(2, endpoint.length() - 1);
            String configValue = ConfigReader.get(configKey);
            if (configValue != null) {
                LoggerUtil.logInfo("Resolved endpoint placeholder: " + endpoint + " -> " + configValue);
                return configValue;
            } else {
                LoggerUtil.logError("Configuration key not found: " + configKey);
                return endpoint; // Return original if config not found
            }
        }
        
        return endpoint;
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
        if (createResponse.getStatusCode() == 200) {
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
        String resolvedEndpoint = resolveEndpoint(endpoint);
        // Use the same user that was created in setup
        CommonSteps.response = apiClient.deleteUser(resolvedEndpoint, currentUser);
    }

    @Then("the user deletion response status code should be {int}")
    public void the_user_deletion_response_status_code_should_be(Integer expectedCode) {
        ResponseValidator.validateStatusCode(CommonSteps.response, expectedCode);
        LoggerUtil.logInfo("Validated " + expectedCode + " status code for user deletion");
    }

    @Then("the user deletion response message should be {string}")
    public void the_user_deletion_response_message_should_be(String expectedMessage) {
        String actualMessage = CommonSteps.response.jsonPath().getString("message");
        LoggerUtil.logInfo("Expected message: " + expectedMessage + ", Actual message: " + actualMessage);
        assert actualMessage != null : "Response message should not be null";
        assert actualMessage.contains(expectedMessage) : 
            "Message mismatch. Expected: " + expectedMessage + ", Actual: " + actualMessage;
    }

    @Then("the response JSON schema should be valid for account deletion")
    public void the_response_json_schema_should_be_valid_for_account_deletion() {
        // Check if this is a negative scenario by looking at the response code
        int responseCode = CommonSteps.response.jsonPath().getInt("responseCode");
        if (responseCode == 404) {
            // Negative scenario - use error schema validation
            String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/errorResponseSchema.json";
            LoggerUtil.logInfo("Validating account deletion error schema: " + schemaPath);
            ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
            ResponseValidator.validateUserDeletionError(CommonSteps.response);
            LoggerUtil.logInfo("Validated account deletion error response schema");
        } else {
            // Positive scenario - use user schema validation
            String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/userSchema.json";
            LoggerUtil.logInfo("Validating account deletion success schema: " + schemaPath);
            ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
            ResponseValidator.validateUserDeletionSuccess(CommonSteps.response);
            LoggerUtil.logInfo("Validated account deletion success response schema");
        }
    }

}

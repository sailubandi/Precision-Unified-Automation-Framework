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
import config.ConfigReader;
import utils.LoggerUtil;

/**
 * Step definitions for TC3 - Create User Account
 */
public class TC3_CreateUserSteps {

    private ApiClient apiClient;

    public TC3_CreateUserSteps() {
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

    @When("I make a POST request to {string} with valid user details")
    public void i_make_a_post_request_to_with_valid_user_details(String endpoint) {
        String resolvedEndpoint = resolveEndpoint(endpoint);
        User user = UserDataFactory.createUniqueUser();
        CommonSteps.response = apiClient.createUser(resolvedEndpoint, user);
    }

    @When("I make a POST request to {string} with valid user details from configuration")
    public void i_make_a_post_request_to_with_valid_user_details_from_configuration(String endpoint) {
        String resolvedEndpoint = resolveEndpoint(endpoint);
        User user = UserDataFactory.createUserWithExistingEmail();
        CommonSteps.response = apiClient.createUser(resolvedEndpoint, user);
    }

    @Then("response status code should be 201")
    public void response_status_code_should_be_201() {
        ResponseValidator.validateStatusCode(CommonSteps.response, 201);
        LoggerUtil.logInfo("Validated 201 status code for user creation");
    }

    
    @Then("the user creation response message should be {string}")
    public void the_user_creation_response_message_should_be(String expectedMessage) {
        String actualMessage = CommonSteps.response.jsonPath().getString("message");
        LoggerUtil.logInfo("Expected message: " + expectedMessage + ", Actual message: " + actualMessage);
        assert actualMessage != null : "Response message should not be null";
        assert actualMessage.contains(expectedMessage) : 
            "Message mismatch. Expected: " + expectedMessage + ", Actual: " + actualMessage;
    }

    @Then("user creation response message should contain {string}")
    public void user_creation_response_message_should_contain(String expectedMessage) {
        String actualMessage = CommonSteps.response.jsonPath().getString("message");
        LoggerUtil.logInfo("Expected message: " + expectedMessage + ", Actual message: " + actualMessage);
        assert actualMessage != null : "Response message should not be null";
        assert actualMessage.contains(expectedMessage) : 
            "Message mismatch. Expected: " + expectedMessage + ", Actual: " + actualMessage;
    }

    @Then("the response JSON schema should be valid for user creation")
    public void the_response_json_schema_should_be_valid_for_user_creation() {
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/userSchema.json";
        LoggerUtil.logInfo("Validating user creation schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        // Also run business logic validation
        ResponseValidator.validateUserCreationSuccess(CommonSteps.response);
        LoggerUtil.logInfo("Validated user creation response schema");
    }

    
}

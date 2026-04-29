package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Map;

import client.ApiClient;
import com.framework.api.factory.UserDataFactory;
import com.framework.api.pojo.User;
import com.framework.api.validator.ResponseValidator;
import utils.LoggerUtil;
import config.ConfigReader;

/**
 * Step definitions for TC5 - Update User Account
 */
public class TC5_UpdateUserSteps {

    private ApiClient apiClient;
    private User currentUser;

    public TC5_UpdateUserSteps() {
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

    /**
     * Resolves configuration placeholders in the value
     * @param value The value that may contain placeholders like ${config.key}
     * @return Resolved value from configuration or original value if no placeholder
     */
    private String resolveConfigValue(String value) {
        if (value == null) {
            return null;
        }
        
        if (value.startsWith("${") && value.endsWith("}")) {
            String configKey = value.substring(2, value.length() - 1);
            String configValue = ConfigReader.get(configKey);
            if (configValue != null) {
                LoggerUtil.logInfo("Resolved config placeholder: " + value + " -> " + configValue);
                return configValue;
            } else {
                LoggerUtil.logError("Configuration key not found: " + configKey);
                return value; // Return original if config not found
            }
        }
        
        return value;
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
        if (createResponse.getStatusCode() == 200) {
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

    @When("I update the user account with the following details:")
    public void i_update_the_user_account_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        LoggerUtil.logInfo("STEP: Updating user account with details from DataTable");
        
        // For positive scenario, update existing user with new details
        // For negative scenario, this step shouldn't be called as we use different step
        
        // Only process DataTable if we have a valid user to update
        if (currentUser != null) {
            // Convert DataTable to List<Map<String, String>>
            List<Map<String, String>> userDetails = dataTable.asMaps(String.class, String.class);
            
            // Update user with details from DataTable
            for (Map<String, String> detail : userDetails) {
                String key = detail.get("key");
                String value = detail.get("value");
                
                if (key != null && value != null) {
                    // Resolve configuration placeholders
                    String resolvedValue = resolveConfigValue(value);
                    
                    switch (key.toLowerCase()) {
                        case "email":
                            currentUser.setEmail(resolvedValue);
                            break;
                        case "password":
                            currentUser.setPassword(resolvedValue);
                            break;
                        case "name":
                            currentUser.setName(resolvedValue);
                            break;
                        case "phone":
                            currentUser.setMobile_number(resolvedValue);
                            break;
                        default:
                            LoggerUtil.logInfo("Ignoring unknown field: " + key);
                            break;
                    }
                }
            }
            
            LoggerUtil.logInfo("STEP: Making API request to update user with new details");
            CommonSteps.response = apiClient.updateUser("/api/updateAccount", currentUser);
            LoggerUtil.logInfo("STEP: User update request completed");
        } else {
            LoggerUtil.logInfo("No user available to update - skipping DataTable processing");
        }
    }

    @When("I make a PUT request to {string} with updated user details")
    public void i_make_a_put_request_to_with_updated_user_details(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUpdatedUser();
        CommonSteps.response = apiClient.updateUser(endpoint, user);
    }

    @When("I make a PUT request to {string} with updated user details from configuration with the following details:")
    public void i_make_a_put_request_to_with_updated_user_details_from_configuration_with_the_following_details(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        String resolvedEndpoint = resolveEndpoint(endpoint);
        
        // Only process DataTable if we have a valid user to update
        if (currentUser != null) {
            // Convert DataTable to List<Map<String, String>>
            List<Map<String, String>> userDetails = dataTable.asMaps(String.class, String.class);
            
            // Update user with details from DataTable
            for (Map<String, String> detail : userDetails) {
                String key = detail.get("key");
                String value = detail.get("value");
                
                if (key != null && value != null) {
                    // Resolve configuration placeholders
                    String resolvedValue = resolveConfigValue(value);
                    
                    switch (key.toLowerCase()) {
                        case "email":
                            currentUser.setEmail(resolvedValue);
                            break;
                        case "password":
                            currentUser.setPassword(resolvedValue);
                            break;
                        case "name":
                            currentUser.setName(resolvedValue);
                            break;
                        case "phone":
                            currentUser.setMobile_number(resolvedValue);
                            break;
                        default:
                            LoggerUtil.logInfo("Ignoring unknown field: " + key);
                            break;
                    }
                }
            }
            
            LoggerUtil.logInfo("STEP: Making API request to update user with new details");
            CommonSteps.response = apiClient.updateUser(resolvedEndpoint, currentUser);
            LoggerUtil.logInfo("STEP: User update request completed");
        } else {
            LoggerUtil.logInfo("No user available to update - skipping DataTable processing");
        }
    }

    @When("I make a PUT request to {string} with updated user details from configuration")
    public void i_make_a_put_request_to_with_updated_user_details_from_configuration(String endpoint) {
        User user = UserDataFactory.createUpdatedUser();
        CommonSteps.response = apiClient.updateUser(endpoint, user);
    }

    @Then("user update response status code should be {int}")
    public void user_update_response_status_code_should_be(Integer expectedCode) {
        ResponseValidator.validateStatusCode(CommonSteps.response, expectedCode);
        LoggerUtil.logInfo("Validated " + expectedCode + " status code for user update");
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
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/userSchema.json";
        LoggerUtil.logInfo("Validating user update schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        ResponseValidator.validateUserUpdateSuccess(CommonSteps.response);
        LoggerUtil.logInfo("Validated user update response schema");
    }

    @Then("the user update response status code should be {int}")
    public void _user_update_response_status_code_should_be(Integer expectedCode) {
        ResponseValidator.validateStatusCode(CommonSteps.response, expectedCode);
        LoggerUtil.logInfo("Validated " + expectedCode + " status code for user update");
    }
    
    @Then("the user update response message should be {string}")
    public void _user_update_response_message_should_be(String expectedMessage) {
        String actualMessage = CommonSteps.response.jsonPath().getString("message");
        LoggerUtil.logInfo("Expected message: " + expectedMessage + ", Actual message: " + actualMessage);
        assert actualMessage != null : "Response message should not be null";
        assert actualMessage.contains(expectedMessage) : 
            "Message mismatch. Expected: " + expectedMessage + ", Actual: " + actualMessage;
    }
    
    @Then("the response JSON schema should be valid for user update")
    public void _response_json_schema_should_be_valid_for_user_update() {
        // Check if this is a negative scenario by looking at the response code
        int responseCode = CommonSteps.response.jsonPath().getInt("responseCode");
        if (responseCode == 404) {
            // Negative scenario - use error schema validation
            String schemaPath = "schemas/errorResponseSchema.json";
            LoggerUtil.logInfo("Validating user update error schema: " + schemaPath);
            ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
            ResponseValidator.validateUserUpdateError(CommonSteps.response);
            LoggerUtil.logInfo("Validated user update error response schema");
        } else {
            // Positive scenario - use user schema validation
            String schemaPath = "schemas/userSchema.json";
            LoggerUtil.logInfo("Validating user update success schema: " + schemaPath);
            ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
            ResponseValidator.validateUserUpdateSuccess(CommonSteps.response);
            LoggerUtil.logInfo("Validated user update success response schema");
        }
    }

}

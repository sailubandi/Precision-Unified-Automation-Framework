package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import client.ApiClient;
import com.framework.api.factory.UserDataFactory;
import com.framework.api.pojo.User;
import com.framework.api.validator.ResponseValidator;
import utils.LoggerUtil;
import config.ConfigReader;

/**
 * Step definitions for TC6 - Negative Validation
 */
public class TC6_NegativeValidationSteps {

    private Response response;
    private ApiClient apiClient;

    public TC6_NegativeValidationSteps() {
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


    @When("I make a POST request to {string} with invalid email")
    public void i_make_a_post_request_to_with_invalid_email(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUserWithInvalidEmail();
        CommonSteps.response = apiClient.createUser(endpoint, user);
    }

    @When("I make a POST request to {string} with invalid email from configuration")
    public void i_make_a_post_request_to_with_invalid_email_from_configuration(String endpoint) {
        String resolvedEndpoint = resolveEndpoint(endpoint);
        User user = UserDataFactory.createUserWithInvalidEmail();
        CommonSteps.response = apiClient.createUser(resolvedEndpoint, user);
    }

    @When("I make a POST request to {string} with missing required fields")
    public void i_make_a_post_request_to_with_missing_required_fields(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        String resolvedEndpoint = resolveEndpoint(endpoint);
        User user = UserDataFactory.createUserWithMissingFields();
        CommonSteps.response = apiClient.createUser(resolvedEndpoint, user);
    }



    @And("the response should contain error message about invalid email")
    public void the_response_should_contain_error_message_about_invalid_email() {
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/errorResponseSchema.json";
        LoggerUtil.logInfo("Validating invalid email error schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        ResponseValidator.validateErrorResponseSchema(CommonSteps.response);
        String responseBody = CommonSteps.response.getBody().asString().toLowerCase();
        assertThat("Should contain email error", responseBody, 
                 anyOf(
                     containsStringIgnoringCase("email"),
                     containsStringIgnoringCase("bad request"),
                     containsStringIgnoringCase("parameter")));
    }

    @And("the response should contain error message about email already exists")
    public void the_response_should_contain_error_message_about_email_already_exists() {
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/errorResponseSchema.json";
        LoggerUtil.logInfo("Validating email already exists error schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        ResponseValidator.validateErrorResponseSchema(CommonSteps.response);
        String responseBody = CommonSteps.response.getBody().asString().toLowerCase();
        assertThat("Should contain email already exists error", responseBody, 
                 anyOf(
                     containsStringIgnoringCase("email already exists"),
                     containsStringIgnoringCase("bad request")));
    }

    @And("the response should contain empty search results")
    public void the_response_should_contain_empty_search_results() {
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/searchResultsSchema.json";
        LoggerUtil.logInfo("Validating empty search results schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        ResponseValidator.validateSearchResultsSchema(CommonSteps.response);
        assertThat("Search results should be empty for invalid search", 
                 CommonSteps.response.jsonPath().getList("products").size(), 
                 equalTo(0));
    }


}

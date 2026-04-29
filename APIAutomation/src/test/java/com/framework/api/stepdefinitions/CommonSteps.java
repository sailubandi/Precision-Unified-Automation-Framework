package com.framework.api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import client.ApiClient;
import com.framework.api.validator.ResponseValidator;
import config.ConfigReader;
import utils.LoggerUtil;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Common step definitions shared across all test cases
 */
public class CommonSteps {

    public static Response response;
    private ApiClient apiClient;

    public CommonSteps() {
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

    @Given("the API endpoint is available for products")
    public void the_api_endpoint_is_available_for_products() {
        assertThat("API base URL should be configured", 
                 apiClient.getBaseUrl(), notNullValue());
    }

    @Given("the API endpoint is available for user account operations")
    public void the_api_endpoint_is_available_for_user_account_operations() {
        assertThat("API base URL should be configured", 
                 apiClient.getBaseUrl(), notNullValue());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        assertThat("Response status code", 
                 CommonSteps.response.getStatusCode(), equalTo(expectedStatusCode));
    	//failed test to verify listener functionality
    	/*assertThat("Response status code",
    							 CommonSteps.response.getStatusCode(), equalTo(999));
    	*/
    }

    @And("the response code in body should be {int}")
    public void the_response_code_in_body_should_be(int expectedCode) {
        ResponseValidator.validateResponseCode(CommonSteps.response, expectedCode);
    }

    @And("the response should contain a valid products list")
    public void the_response_should_contain_a_valid_products_list() {
        ResponseValidator.validateProductsListSchema(CommonSteps.response);
        assertThat("Products list should not be empty", 
                 CommonSteps.response.jsonPath().getList("products").size(),
                 org.hamcrest.Matchers.greaterThan(0));
    }

    @And("the response JSON schema should be valid for products list")
    public void the_response_json_schema_should_be_valid_for_products_list() {
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/productsSchema.json";
        LoggerUtil.logInfo("Validating products list schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        // Also run business logic validation
        ResponseValidator.validateProductsListSchema(CommonSteps.response);
    }

    @And("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        assertThat("Response message", 
                 CommonSteps.response.jsonPath().getString("message"), equalTo(expectedMessage));
    }

    @And("the response JSON schema should be valid for search results")
    public void the_response_json_schema_should_be_valid_for_search_results() {
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/searchResultsSchema.json";
        LoggerUtil.logInfo("Validating search results schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        // Also run business logic validation
        ResponseValidator.validateSearchResultsSchema(CommonSteps.response);
    }

    @And("the response JSON schema should be valid for error response")
    public void the_response_json_schema_should_be_valid_for_error_response() {
        String schemaPath = System.getProperty("user.dir") + "/APIAutomation/src/test/resources/schemas/errorResponseSchema.json";
        LoggerUtil.logInfo("Validating error response schema: " + schemaPath);
        ResponseValidator.validateSchema(CommonSteps.response, schemaPath);
        // Also run business logic validation
        ResponseValidator.validateErrorResponseSchema(CommonSteps.response);
    }

    @When("I make a POST request to {string} with search parameter {string}")
    public void i_make_a_post_request_to_with_search_parameter(String endpoint, String searchTerm) {
        String resolvedEndpoint = resolveEndpoint(endpoint);
        CommonSteps.response = apiClient.postWithSearch(resolvedEndpoint, searchTerm);
        
        // Handle potential rate limiting for certain search terms
        int statusCode = CommonSteps.response.getStatusCode();
        if (statusCode == 503) {
            LoggerUtil.logInfo("Search API returned 503, possible rate limiting for term: " + searchTerm);
            LoggerUtil.logInfo("Retrying search request once...");
            try {
                Thread.sleep(1000); // Wait 1 second
                CommonSteps.response = apiClient.postWithSearch(resolvedEndpoint, searchTerm);
                LoggerUtil.logInfo("Retry completed, new status code: " + CommonSteps.response.getStatusCode());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LoggerUtil.logError("Retry interrupted: " + e.getMessage());
            }
        }
    }
}

package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import client.ApiClient;
import com.framework.api.validator.ResponseValidator;
import config.ConfigReader;
import utils.LoggerUtil;

/**
 * Step definitions for TC1 - Get All Products List
 */
public class TC1_GetAllProductsSteps {

    private Response response;
    
    private ApiClient apiClient;

    public TC1_GetAllProductsSteps() {
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

    
    @When("I make a GET request to {string}")
    public void i_make_a_get_request_to(String endpoint) {
        String resolvedEndpoint = resolveEndpoint(endpoint);
        LoggerUtil.logApiRequest("GET", resolvedEndpoint, null);
        long startTime = System.currentTimeMillis();
        CommonSteps.response = apiClient.get(resolvedEndpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        LoggerUtil.logApiResponse(CommonSteps.response.getStatusCode(), responseTime);
        LoggerUtil.logInfo("GET Request completed for endpoint: " + endpoint);
    }
    




}

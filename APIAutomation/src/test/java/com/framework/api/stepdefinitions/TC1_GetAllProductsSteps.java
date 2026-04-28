package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import client.ApiClient;
import com.framework.api.validator.ResponseValidator;
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

    
    @When("I make a GET request to {string}")
    public void i_make_a_get_request_to(String endpoint) {
        LoggerUtil.logApiRequest("GET", endpoint, null);
        long startTime = System.currentTimeMillis();
        CommonSteps.response = apiClient.get(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        LoggerUtil.logApiResponse(CommonSteps.response.getStatusCode(), responseTime);
        LoggerUtil.logInfo("GET Request completed for endpoint: " + endpoint);
    }
    




}

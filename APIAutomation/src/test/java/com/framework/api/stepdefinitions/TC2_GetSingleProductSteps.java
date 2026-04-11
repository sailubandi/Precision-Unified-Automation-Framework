package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import client.ApiClient;
import com.framework.api.validator.ResponseValidator;

/**
 * Step definitions for TC2 - Get Single Product Details
 */
public class TC2_GetSingleProductSteps {

    private Response response;
    private ApiClient apiClient;

    public TC2_GetSingleProductSteps() {
        this.apiClient = new ApiClient();
    }




    @And("the response should contain searched products")
    public void the_response_should_contain_searched_products() {
        ResponseValidator.validateSearchResultsSchema(CommonSteps.response);
        assertThat("Search results should not be empty", 
                 CommonSteps.response.jsonPath().getList("products").size(), 
                 org.hamcrest.Matchers.greaterThan(0));
    }

    @And("the response should contain searched products for {string}")
    public void the_response_should_contain_searched_products_for(String searchTerm) {
        ResponseValidator.validateSearchResultsSchema(CommonSteps.response);
        assertThat("Search results should be relevant", 
                 CommonSteps.response.getBody().asString().toLowerCase(), 
                 org.hamcrest.Matchers.containsStringIgnoringCase(searchTerm.toLowerCase()));
    }


}

package com.framework.api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import client.ApiClient;
import com.framework.api.validator.ResponseValidator;
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
        ResponseValidator.validateProductsListSchema(CommonSteps.response);
    }

    @And("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        assertThat("Response message", 
                 CommonSteps.response.jsonPath().getString("message"), equalTo(expectedMessage));
    }

    @And("the response JSON schema should be valid for search results")
    public void the_response_json_schema_should_be_valid_for_search_results() {
        ResponseValidator.validateSearchResultsSchema(CommonSteps.response);
    }

    @And("the response JSON schema should be valid for error response")
    public void the_response_json_schema_should_be_valid_for_error_response() {
        ResponseValidator.validateErrorResponseSchema(CommonSteps.response);
    }

    @When("I make a POST request to {string} with search parameter {string}")
    public void i_make_a_post_request_to_with_search_parameter(String endpoint, String searchTerm) {
        CommonSteps.response = apiClient.postWithSearch(endpoint, searchTerm);
    }
}

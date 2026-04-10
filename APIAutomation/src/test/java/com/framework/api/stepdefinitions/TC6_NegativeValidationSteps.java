package com.framework.api.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import client.ApiClient;
import com.framework.api.factory.UserDataFactory;
import com.framework.api.pojo.User;
import com.framework.api.validator.ResponseValidator;

/**
 * Step definitions for TC6 - Negative Validation
 */
public class TC6_NegativeValidationSteps {

    private Response response;
    private ApiClient apiClient;

    public TC6_NegativeValidationSteps() {
        this.apiClient = new ApiClient();
    }


    @When("I make a POST request to {string} with invalid email")
    public void i_make_a_post_request_to_with_invalid_email(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUserWithInvalidEmail();
        CommonSteps.response = apiClient.createUser(endpoint, user);
    }

    @When("I make a POST request to {string} with invalid email from configuration")
    public void i_make_a_post_request_to_with_invalid_email_from_configuration(String endpoint) {
        User user = UserDataFactory.createUserWithInvalidEmail();
        CommonSteps.response = apiClient.createUser(endpoint, user);
    }

    @When("I make a POST request to {string} with missing required fields")
    public void i_make_a_post_request_to_with_missing_required_fields(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUserWithMissingFields();
        CommonSteps.response = apiClient.createUser(endpoint, user);
    }



    @And("the response should contain error message about invalid email")
    public void the_response_should_contain_error_message_about_invalid_email() {
        ResponseValidator.validateErrorResponseSchema(CommonSteps.response);
        assertThat("Should contain email error", 
                 CommonSteps.response.getBody().asString().toLowerCase(), 
                 org.hamcrest.Matchers.containsStringIgnoringCase("email"));
    }

    @And("the response should contain error message about email already exists")
    public void the_response_should_contain_error_message_about_email_already_exists() {
        ResponseValidator.validateErrorResponseSchema(CommonSteps.response);
        assertThat("Should contain email already exists error", 
                 CommonSteps.response.getBody().asString().toLowerCase(), 
                 org.hamcrest.Matchers.containsStringIgnoringCase("email already exists"));
    }

    @And("the response should contain empty search results")
    public void the_response_should_contain_empty_search_results() {
        ResponseValidator.validateSearchResultsSchema(CommonSteps.response);
        assertThat("Search results should be empty for invalid search", 
                 CommonSteps.response.jsonPath().getList("products").size(), 
                 org.hamcrest.Matchers.equalTo(0));
    }


}

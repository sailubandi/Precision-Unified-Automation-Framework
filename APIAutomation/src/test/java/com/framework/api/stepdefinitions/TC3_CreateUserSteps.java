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
 * Step definitions for TC3 - Create User Account
 */
public class TC3_CreateUserSteps {

    private Response response;
    private ApiClient apiClient;

    public TC3_CreateUserSteps() {
        this.apiClient = new ApiClient();
    }


    @When("I make a POST request to {string} with valid user details from configuration")
    public void i_make_a_post_request_to_with_valid_user_details_from_configuration(String endpoint) {
        User user = UserDataFactory.createValidUser();
        CommonSteps.response = apiClient.createUser(endpoint, user);
    }



    @And("the response JSON schema should be valid for user creation")
    public void the_response_json_schema_should_be_valid_for_user_creation() {
        ResponseValidator.validateUserCreationSchema(CommonSteps.response);
    }

}

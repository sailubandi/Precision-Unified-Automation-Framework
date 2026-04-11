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
 * Step definitions for TC5 - Update User Account
 */
public class TC5_UpdateUserSteps {

    private Response response;
    private ApiClient apiClient;

    public TC5_UpdateUserSteps() {
        this.apiClient = new ApiClient();
    }


    @When("I make a PUT request to {string} with updated user details")
    public void i_make_a_put_request_to_with_updated_user_details(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUpdatedUser();
        CommonSteps.response = apiClient.updateUser(endpoint, user);
    }

    @When("I make a PUT request to {string} with updated user details from configuration")
    public void i_make_a_put_request_to_with_updated_user_details_from_configuration(String endpoint) {
        User user = UserDataFactory.createUpdatedUser();
        CommonSteps.response = apiClient.updateUser(endpoint, user);
    }



    @And("the response JSON schema should be valid for user update")
    public void the_response_json_schema_should_be_valid_for_user_update() {
        ResponseValidator.validateUserUpdateSchema(CommonSteps.response);
    }

}

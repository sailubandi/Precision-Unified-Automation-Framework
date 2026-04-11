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
 * Step definitions for TC4 - Delete User Account
 */
public class TC4_DeleteUserSteps {

    private Response response;
    private ApiClient apiClient;

    public TC4_DeleteUserSteps() {
        this.apiClient = new ApiClient();
    }


    @When("I make a DELETE request to {string} with user credentials")
    public void i_make_a_delete_request_to_with_user_credentials(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        User user = UserDataFactory.createUserCredentials();
        CommonSteps.response = apiClient.deleteUser(endpoint, user);
    }

    @When("I make a DELETE request to {string} with user credentials from configuration")
    public void i_make_a_delete_request_to_with_user_credentials_from_configuration(String endpoint) {
        User user = UserDataFactory.createUserCredentials();
        CommonSteps.response = apiClient.deleteUser(endpoint, user);
    }



    @And("the response JSON schema should be valid for account deletion")
    public void the_response_json_schema_should_be_valid_for_account_deletion() {
        ResponseValidator.validateAccountDeletionSchema(CommonSteps.response);
    }

}

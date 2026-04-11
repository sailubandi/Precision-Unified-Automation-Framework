package client;

import java.util.Map;

import com.framework.api.pojo.User;

import config.ConfigReader;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.LoggerUtil;

/**
 * Enhanced API client with RestAssured direct usage
 */
public class ApiClient {
    
    private RequestSpecification requestSpec;
    
    static {
        // Initialize RestAssured static configuration once
        RestAssured.baseURI = ConfigReader.get("base.url");
    }
    
    public ApiClient() {
        this.requestSpec = RestAssured.given();
    }
    
    public String getBaseUrl() {
        return ConfigReader.get("base.url");
    }
    
    // TC1: Get All Products List
    public Response get(String endpoint) {
        LoggerUtil.logApiRequest("GET", endpoint, null);
        long startTime = System.currentTimeMillis();
        Response response = RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .when()
                .get(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        LoggerUtil.logApiResponse(response.getStatusCode(), responseTime);
        
        // Add Allure attachment for response
        try {
            String responseBody = response.getBody().asString();
            Allure.addAttachment("API Response", "application/json", responseBody);
        } catch (Exception e) {
            LoggerUtil.logWarning("Could not attach response to Allure: " + e.getMessage());
        }
        
        return response;
    }
    
    // TC2: Get Single Product Details with Search
    public Response postWithSearch(String endpoint, String searchTerm) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParam("search_product", searchTerm)
                .when()
                .post(endpoint);
    }
    
    // TC3: Create User Account
    public Response createUser(String endpoint, User user) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", user.getName())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getBirth_date())
                .formParam("birth_month", user.getBirth_month())
                .formParam("birth_year", user.getBirth_year())
                .formParam("firstname", user.getFirstname())
                .formParam("lastname", user.getLastname())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress1())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipcode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobile_number())
                .when()
                .post(endpoint);
    }
    
    // TC4: Delete User Account
    public Response deleteUser(String endpoint, User user) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .when()
                .delete(endpoint);
    }
    
    // TC5: Update User Account
    public Response updateUser(String endpoint, User user) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", user.getName())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getBirth_date())
                .formParam("birth_month", user.getBirth_month())
                .formParam("birth_year", user.getBirth_year())
                .formParam("firstname", user.getFirstname())
                .formParam("lastname", user.getLastname())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress1())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipcode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobile_number())
                .when()
                .put(endpoint);
    }
    
    // Legacy methods for backward compatibility
    public Response createUser(String endpoint, Map<String, String> userData) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParams(userData)
                .when()
                .post(endpoint);
    }
    
    public Response deleteUser(String endpoint, Map<String, String> credentials) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParams(credentials)
                .when()
                .delete(endpoint);
    }
    
    public Response updateUser(String endpoint, Map<String, String> userData) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParams(userData)
                .when()
                .put(endpoint);
    }
    
    // Legacy static methods for backward compatibility
    public static Response getStatic(String endpoint) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .when()
                .get(endpoint);
    }
    
    public static Response postStatic(String endpoint, Object body) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParams((Map<String, String>) body)
                .when()
                .post(endpoint);
    }
    
    public static Response postJsonStatic(String endpoint, Object body) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/json")
                .body(body)
                .when()
                .post(endpoint);
    }
    
    public static Response putStatic(String endpoint, Object body) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParams((Map<String, String>) body)
                .when()
                .put(endpoint);
    }
    
    public static Response deleteStatic(String endpoint, Object body) {
        return RestAssured
                .given()
                .baseUri(ConfigReader.get("base.url"))
                .contentType("application/x-www-form-urlencoded")
                .formParams((Map<String, String>) body)
                .when()
                .delete(endpoint);
    }
}
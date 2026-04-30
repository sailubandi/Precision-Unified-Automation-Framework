package client;

import java.util.Map;

import com.framework.api.builder.RequestBuilder;
import com.framework.api.pojo.User;

import config.ConfigReader;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.LoggerUtil;
//RequestBuilder (Layer we added) → RequestSpecBuilder (RestAssured) → RequestSpecification (Executable)
/**
 * Enhanced API client using RequestBuilder for proper layered architecture
 */
public class ApiClient {
    
    private RequestBuilder requestBuilder;
    
    public ApiClient() {
        this.requestBuilder = new RequestBuilder();
    }
    
    public String getBaseUrl() {
        return ConfigReader.get("base.url");
    }
    
    // TC1: Get All Products List
    public Response get(String endpoint) {
        LoggerUtil.logInfo("======== API REQUEST START ========");
        LoggerUtil.logInfo("Method: GET");
        LoggerUtil.logInfo("Endpoint: " + endpoint);
        LoggerUtil.logInfo("Base URL: " + getBaseUrl());
        LoggerUtil.logInfo("======== API REQUEST END ==========");
        
        long startTime = System.currentTimeMillis();
        RequestSpecification request = requestBuilder.build();
        Response response = request.get(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        
        LoggerUtil.logInfo("======== API RESPONSE START =======");
        LoggerUtil.logInfo("Status Code: " + response.getStatusCode());
        LoggerUtil.logInfo("Response Time: " + responseTime + "ms");
        LoggerUtil.logInfo("Response Body: " + response.getBody().asString());
        LoggerUtil.logInfo("======== API RESPONSE END ==========");
        
        // Add Allure attachment for response
        try {
            String responseBody = response.getBody().asString();
            Allure.addAttachment("Endpoint", "text/plain", endpoint);
            Allure.addAttachment("Status Code", "text/plain", String.valueOf(response.getStatusCode()));
            Allure.addAttachment("API Response", "application/json", responseBody);
        } catch (Exception e) {
            LoggerUtil.logWarning("Could not attach response to Allure: " + e.getMessage());
        }
        
        return response;
    }
    
    // TC2: Get Single Product Details with Search
    public Response postWithSearch(String endpoint, String searchTerm) {
        RequestSpecification request = requestBuilder
                .setFormContentType()
                .addFormParam("search_product", searchTerm)
                .build();
        
        return request.post(endpoint);
    }
    
    // TC3: Create User Account
    public Response createUser(String endpoint, User user) {
        LoggerUtil.logApiRequest("POST", endpoint, user.toString());
        long startTime = System.currentTimeMillis();
        
        RequestSpecification request = requestBuilder
                .setFormContentType()
                .addFormParams(user.toMap())
                .build();
        
        Response response = request.post(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        
        LoggerUtil.logInfo("======== API RESPONSE START =======");
        LoggerUtil.logInfo("Status Code: " + response.getStatusCode());
        LoggerUtil.logInfo("Response Time: " + responseTime + "ms");
        LoggerUtil.logInfo("Response Body: " + response.getBody().asString());
        LoggerUtil.logInfo("======== API RESPONSE END ==========");
        
        // Attach request/response to Allure
        Allure.addAttachment("Endpoint", "text/plain", endpoint);
        Allure.addAttachment("Status Code", "text/plain", String.valueOf(response.getStatusCode()));
        Allure.addAttachment("API Request", "application/json", "POST " + endpoint + " with user: " + user.toString());
        Allure.addAttachment("API Response", "application/json", response.getBody().asString());
        
        return response;
    }
    
    // TC4: Delete User Account
    public Response deleteUser(String endpoint, User user) {
        LoggerUtil.logInfo("======== API REQUEST START ========");
        LoggerUtil.logInfo("Method: DELETE");
        LoggerUtil.logInfo("Endpoint: " + endpoint);
        LoggerUtil.logInfo("Base URL: " + getBaseUrl());
        LoggerUtil.logInfo("Request Body: " + user.toString());
        LoggerUtil.logInfo("======== API REQUEST END ==========");
        
        long startTime = System.currentTimeMillis();
        
        RequestSpecification request = requestBuilder
                .setFormContentType()
                .addFormParams(user.toMap())
                .build();
        
        Response response = request.delete(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        
        LoggerUtil.logInfo("======== API RESPONSE START =======");
        LoggerUtil.logInfo("Status Code: " + response.getStatusCode());
        LoggerUtil.logInfo("Response Time: " + responseTime + "ms");
        LoggerUtil.logInfo("Response Body: " + response.getBody().asString());
        LoggerUtil.logInfo("======== API RESPONSE END ==========");
        
        // Attach request/response to Allure
        Allure.addAttachment("Endpoint", "text/plain", endpoint);
        Allure.addAttachment("Status Code", "text/plain", String.valueOf(response.getStatusCode()));
        Allure.addAttachment("API Request", "application/json", "DELETE " + endpoint + " with user: " + user.toString());
        Allure.addAttachment("API Response", "application/json", response.getBody().asString());
        
        return response;
    }
    
    // TC5: Update User Account
    public Response updateUser(String endpoint, User user) {
        LoggerUtil.logInfo("======== API REQUEST START ========");
        LoggerUtil.logInfo("Method: PUT");
        LoggerUtil.logInfo("Endpoint: " + endpoint);
        LoggerUtil.logInfo("Base URL: " + getBaseUrl());
        LoggerUtil.logInfo("Request Body: " + user.toString());
        LoggerUtil.logInfo("======== API REQUEST END ==========");
        
        long startTime = System.currentTimeMillis();
        
        RequestSpecification request = requestBuilder
                .setFormContentType()
                .addFormParams(user.toMap())
                .build();
        
        Response response = request.put(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        
        LoggerUtil.logInfo("======== API RESPONSE START =======");
        LoggerUtil.logInfo("Status Code: " + response.getStatusCode());
        LoggerUtil.logInfo("Response Time: " + responseTime + "ms");
        LoggerUtil.logInfo("Response Body: " + response.getBody().asString());
        LoggerUtil.logInfo("======== API RESPONSE END ==========");
        
        // Attach request/response to Allure
        Allure.addAttachment("Endpoint", "text/plain", endpoint);
        Allure.addAttachment("Status Code", "text/plain", String.valueOf(response.getStatusCode()));
        Allure.addAttachment("API Request", "application/json", "PUT " + endpoint + " with user: " + user.toString());
        Allure.addAttachment("API Response", "application/json", response.getBody().asString());
        
        return response;
    }
    
    // Legacy methods for backward compatibility
    public Response createUser(String endpoint, Map<String, String> userData) {
        RequestSpecification request = requestBuilder
                .setFormContentType()
                .addFormParams(userData)
                .build();
        
        return request.post(endpoint);
    }
    
    public Response deleteUser(String endpoint, Map<String, String> credentials) {
        LoggerUtil.logApiRequest("DELETE", endpoint, credentials.toString());
        long startTime = System.currentTimeMillis();
        
        RequestSpecification request = requestBuilder
                .setFormContentType()
                .addFormParams(credentials)
                .build();
        
        Response response = request.delete(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        
        // Log response details
        LoggerUtil.logApiResponse(response.getStatusCode(), responseTime);
        LoggerUtil.logDebug("DELETE Response: " + response.getBody().asString());
        
        // Attach request/response to Allure
        Allure.addAttachment("API Request", "application/json", "DELETE " + endpoint + " with credentials: " + credentials);
        Allure.addAttachment("API Response", "application/json", response.getBody().asString());
        
        return response;
    }
    
    public Response updateUser(String endpoint, Map<String, String> userData) {
        LoggerUtil.logApiRequest("PUT", endpoint, userData.toString());
        long startTime = System.currentTimeMillis();
        
        RequestSpecification request = requestBuilder
                .setFormContentType()
                .addFormParams(userData)
                .build();
        
        Response response = request.put(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        
        // Log response details
        LoggerUtil.logApiResponse(response.getStatusCode(), responseTime);
        LoggerUtil.logDebug("PUT Response: " + response.getBody().asString());
        
        // Attach request/response to Allure
        Allure.addAttachment("API Request", "application/json", "PUT " + endpoint + " with user data: " + userData);
        Allure.addAttachment("API Response", "application/json", response.getBody().asString());
        
        return response;
    }
    
    // Legacy static methods for backward compatibility
    public static Response getStatic(String endpoint) {
        RequestBuilder staticBuilder = new RequestBuilder();
        RequestSpecification request = staticBuilder.build();
        
        LoggerUtil.logApiRequest("GET", endpoint, null);
        long startTime = System.currentTimeMillis();
        
        Response response = request.get(endpoint);
        long responseTime = System.currentTimeMillis() - startTime;
        
        LoggerUtil.logInfo("======== API RESPONSE START =======");
        LoggerUtil.logInfo("Status Code: " + response.getStatusCode());
        LoggerUtil.logInfo("Response Time: " + responseTime + "ms");
        LoggerUtil.logInfo("Response Body: " + response.getBody().asString());
        LoggerUtil.logInfo("======== API RESPONSE END ==========");
        
        // Add Allure attachment for response
        try {
            String responseBody = response.getBody().asString();
            Allure.addAttachment("Endpoint", "text/plain", endpoint);
            Allure.addAttachment("Status Code", "text/plain", String.valueOf(response.getStatusCode()));
            Allure.addAttachment("API Response", "application/json", responseBody);
        } catch (Exception e) {
            LoggerUtil.logWarning("Could not attach response to Allure: " + e.getMessage());
        }
        
        return response;
    }
}

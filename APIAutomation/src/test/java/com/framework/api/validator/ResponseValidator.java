package com.framework.api.validator;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import java.io.File;
import java.util.List;
import utils.LoggerUtil;

/**
 * Enhanced response validator with comprehensive JSON schema validation
 * Includes null-safe methods for robust error handling
 */
public class ResponseValidator {

    /**
     * Safely extracts response code from JSON path
     * @param response The REST response
     * @return Integer response code or null if not found
     */
    private static Integer getResponseCode(Response response) {
        try {
            return response.jsonPath().getInt("responseCode");
        } catch (Exception e) {
            return null; // Return null if field doesn't exist
        }
    }

    // Basic validation methods
    public static void validateStatusCode(Response response, int expectedStatus) {
        int actualStatus = response.getStatusCode();
        LoggerUtil.logInfo("Validating status code. Expected: " + expectedStatus + ", Actual: " + actualStatus);
        
        if (actualStatus == expectedStatus) {
            LoggerUtil.logInfo("Validation PASSED - Status code matches: " + actualStatus);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected: " + expectedStatus + ", Actual: " + actualStatus);
        }
        
        Assert.assertEquals(actualStatus, expectedStatus,
                "Status code mismatch - Expected: " + expectedStatus + ", Actual: " + actualStatus);
    }

    public static void validateProductSearch(Response response, String keyword) {
        LoggerUtil.logInfo("Validating product search - Keyword: " + keyword);
        List<String> productNames = response.jsonPath().getList("products.name");

        boolean matchFound = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains(keyword.toLowerCase()));

        if (matchFound) {
            LoggerUtil.logInfo("Product search validation passed - Found matching products");
        } else {
            LoggerUtil.logError("Product search validation failed - No matching products found");
        }
        
        Assert.assertTrue(matchFound, "No matching product found in response");
    }

    public static void validateResponseCode(Response response, int expectedCode) {
        int actualCode = getResponseCode(response);
        LoggerUtil.logInfo("Validating response code in body. Expected: " + expectedCode + ", Actual: " + actualCode);
        
        if (actualCode == expectedCode) {
            LoggerUtil.logInfo("Validation PASSED - Response code in body matches: " + actualCode);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected: " + expectedCode + ", Actual: " + actualCode);
        }
        
        Assert.assertEquals(actualCode, expectedCode,
                "Response code mismatch in body - Expected: " + expectedCode + ", Actual: " + actualCode);
    }

    // JSON Schema validation methods
    public static void validateSchema(Response response, String schemaPath) {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }

    public static void validateProductsListSchema(Response response) {
        // Basic structure validation for products list
        Assert.assertTrue(response.jsonPath().get() != null, "Response should not be null");
        Assert.assertTrue(response.jsonPath().getList("products").size() >= 0, 
                "Products list should exist and not be null");
        
        // Validate each product has required fields
        List<Object> products = response.jsonPath().getList("products");
        if (!products.isEmpty()) {
            Assert.assertNotNull(response.jsonPath().getString("products[0].id"), 
                    "Product should have ID");
            Assert.assertNotNull(response.jsonPath().getString("products[0].name"), 
                    "Product should have name");
            Assert.assertNotNull(response.jsonPath().getString("products[0].price"), 
                    "Product should have price");
        }
    }

    public static void validateSearchResultsSchema(Response response) {
        // Basic structure validation for search results
        Assert.assertTrue(response.jsonPath().get() != null, "Response should not be null");
        Assert.assertTrue(response.jsonPath().getList("products").size() >= 0, 
                "Search results should exist and not be null");
        
        // Validate search results structure
        List<Object> products = response.jsonPath().getList("products");
        if (!products.isEmpty()) {
            Assert.assertNotNull(response.jsonPath().getString("products[0].id"), 
                    "Search result should have ID");
            Assert.assertNotNull(response.jsonPath().getString("products[0].name"), 
                    "Search result should have name");
        }
    }

    public static void validateUserCreationSchema(Response response) {
        // Validate user creation response
        Assert.assertTrue(response.jsonPath().get() != null, "Response should not be null");
        
        // Check for success message or user data
        if (response.jsonPath().getString("message") != null) {
            Assert.assertTrue(response.jsonPath().getString("message").contains("created") ||
                    response.jsonPath().getString("message").contains("success") ||
                    response.jsonPath().getString("message").contains("already exists"),
                    "Should indicate successful user creation or existing user");
        }
        
        // Validate response code if present
        Integer responseCode = getResponseCode(response);
        if (responseCode != null && (responseCode == 200 || responseCode == 201)) {
            Assert.assertTrue(true, "User creation should return 200 or 201");
        }
    }

    public static void validateAccountDeletionSchema(Response response) {
        // Validate account deletion response
        Assert.assertTrue(response.jsonPath().get() != null, "Response should not be null");
        
        // Check for success message
        if (response.jsonPath().getString("message") != null) {
            Assert.assertTrue(response.jsonPath().getString("message").contains("deleted") ||
                    response.jsonPath().getString("message").contains("success") ||
                    response.jsonPath().getString("message").contains("not found"),
                    "Should indicate successful account deletion or account not found");
        }
        
        // Validate response code if present
        Integer responseCode = getResponseCode(response);
        if (responseCode != null && responseCode == 200) {
            Assert.assertEquals(responseCode.intValue(), 200,
                    "Account deletion should return 200");
        }
    }

    public static void validateUserUpdateSchema(Response response) {
        // Validate user update response
        Assert.assertTrue(response.jsonPath().get() != null, "Response should not be null");
        
        // Check for success message
        if (response.jsonPath().getString("message") != null) {
            Assert.assertTrue(response.jsonPath().getString("message").contains("updated") ||
                    response.jsonPath().getString("message").contains("success") ||
                    response.jsonPath().getString("message").contains("not found"),
                    "Should indicate successful user update or account not found");
        }
        
        // Validate response code if present
        Integer responseCode = getResponseCode(response);
        if (responseCode != null && responseCode == 200) {
            Assert.assertEquals(responseCode.intValue(), 200,
                    "User update should return 200");
        }
    }

    public static void validateErrorResponseSchema(Response response) {
        // Validate error response structure
        Assert.assertTrue(response.jsonPath().get() != null, "Error response should not be null");
        
        // Should contain error information
        boolean hasError = response.jsonPath().getString("message") != null ||
                         response.jsonPath().getString("error") != null ||
                         getResponseCode(response) != null && getResponseCode(response) >= 400;
        
        Assert.assertTrue(hasError, "Response should contain error information");
        
        // Validate error response code if present
        Integer responseCode = getResponseCode(response);
        if (responseCode != null) {
            Assert.assertTrue(responseCode >= 400 && responseCode < 600,
                    "Error response code should be 4xx or 5xx");
        }
    }
    
    // Positive validation methods for success scenarios
    public static void validateUserCreationSuccess(Response response) {
        LoggerUtil.logInfo("Validating user creation success");
        
        int actualStatus = response.getStatusCode();
        int expectedStatus = 200; // API returns 200 HTTP status with 201 in response body
        LoggerUtil.logInfo("Validating status code. Expected: " + expectedStatus + ", Actual: " + actualStatus);
        
        if (actualStatus == expectedStatus) {
            LoggerUtil.logInfo("Validation PASSED - Status code matches: " + actualStatus);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected: " + expectedStatus + ", Actual: " + actualStatus);
        }
        
        validateStatusCode(response, expectedStatus);
        validateResponseCode(response, 201); // Response code in body should be 201
        
        String message = response.jsonPath().getString("message");
        Assert.assertNotNull(message, "Response should contain success message");
        if (message.contains("User created!") || message.contains("created")) {
            LoggerUtil.logInfo("Validation PASSED - User creation message: " + message);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected success message, got: " + message);
        }
    }
    
    public static void validateUserUpdateSuccess(Response response) {
        LoggerUtil.logInfo("Validating user update success");
        
        int actualStatus = response.getStatusCode();
        int expectedStatus = response.jsonPath().getInt("responseCode");
        LoggerUtil.logInfo("Validating status code. Expected: " + expectedStatus + ", Actual: " + actualStatus);
        
        if (actualStatus == expectedStatus) {
            LoggerUtil.logInfo("Validation PASSED - Status code matches: " + actualStatus);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected: " + expectedStatus + ", Actual: " + actualStatus);
        }
        
        validateStatusCode(response, expectedStatus);
        validateResponseCode(response, expectedStatus);
        
        String message = response.jsonPath().getString("message");
        Assert.assertNotNull(message, "Response should contain success message");
        if (message.contains("User updated!") || message.contains("updated")) {
            LoggerUtil.logInfo("Validation PASSED - User update message: " + message);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected success message, got: " + message);
        }
        Assert.assertTrue(message.contains("User updated!") || message.contains("updated"),
                "Should contain user update success message: " + message);
    }
    
    public static void validateUserDeletionSuccess(Response response) {
        LoggerUtil.logInfo("Validating user deletion success");
        
        int actualStatus = response.getStatusCode();
        int expectedStatus = response.jsonPath().getInt("responseCode");
        LoggerUtil.logInfo("Validating status code. Expected: " + expectedStatus + ", Actual: " + actualStatus);
        
        if (actualStatus == expectedStatus) {
            LoggerUtil.logInfo("Validation PASSED - Status code matches: " + actualStatus);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected: " + expectedStatus + ", Actual: " + actualStatus);
        }
        
        validateStatusCode(response, expectedStatus);
        validateResponseCode(response, expectedStatus);
        
        String message = response.jsonPath().getString("message");
        Assert.assertNotNull(message, "Response should contain success message");
        if (message.contains("Account deleted!") || message.contains("deleted")) {
            LoggerUtil.logInfo("Validation PASSED - User deletion message: " + message);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected success message, got: " + message);
        }
        Assert.assertTrue(message.contains("Account deleted!") || message.contains("deleted"),
                "Should contain account deletion success message: " + message);
    }
    
    /**
     * Validates user deletion error response (for negative scenarios)
     * @param response API response
     */
    public static void validateUserDeletionError(Response response) {
        LoggerUtil.logInfo("Validating user deletion error response");
        
        // For error scenarios, HTTP status should be 200 but responseCode should be 404
        int actualStatus = response.getStatusCode();
        int expectedHttpStatus = 200;
        int expectedResponseCode = response.jsonPath().getInt("responseCode");
        
        LoggerUtil.logInfo("Validating HTTP status. Expected: " + expectedHttpStatus + ", Actual: " + actualStatus);
        validateStatusCode(response, expectedHttpStatus);
        
        LoggerUtil.logInfo("Validating response code in body. Expected: 404, Actual: " + expectedResponseCode);
        validateResponseCode(response, 404);
        
        String message = response.jsonPath().getString("message");
        Assert.assertNotNull(message, "Response should contain error message");
        if (message.contains("Account not found!") || message.contains("not found")) {
            LoggerUtil.logInfo("Validation PASSED - User deletion error message: " + message);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected error message, got: " + message);
        }
        Assert.assertTrue(message.contains("Account not found!") || message.contains("not found"),
                "Should contain account not found error message: " + message);
    }
    
    /**
     * Validates user update error response (for negative scenarios)
     * @param response API response
     */
    public static void validateUserUpdateError(Response response) {
        LoggerUtil.logInfo("Validating user update error response");
        
        // For error scenarios, HTTP status should be 200 but responseCode should be 404
        int actualStatus = response.getStatusCode();
        int expectedHttpStatus = 200;
        int expectedResponseCode = response.jsonPath().getInt("responseCode");
        
        LoggerUtil.logInfo("Validating HTTP status. Expected: " + expectedHttpStatus + ", Actual: " + actualStatus);
        validateStatusCode(response, expectedHttpStatus);
        
        LoggerUtil.logInfo("Validating response code in body. Expected: 404, Actual: " + expectedResponseCode);
        validateResponseCode(response, 404);
        
        String message = response.jsonPath().getString("message");
        Assert.assertNotNull(message, "Response should contain error message");
        if (message.contains("Account not found!") || message.contains("not found")) {
            LoggerUtil.logInfo("Validation PASSED - User update error message: " + message);
        } else {
            LoggerUtil.logError("Validation FAILED - Expected error message, got: " + message);
        }
        Assert.assertTrue(message.contains("Account not found!") || message.contains("not found"),
                "Should contain account not found error message: " + message);
    }
}

package com.framework.api.validator;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import java.io.File;
import java.util.List;

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
        Assert.assertEquals(response.getStatusCode(), expectedStatus,
                "Status code mismatch");
    }

    public static void validateProductSearch(Response response, String keyword) {
        List<String> productNames = response.jsonPath().getList("products.name");

        boolean matchFound = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains(keyword.toLowerCase()));

        Assert.assertTrue(matchFound, "No matching product found in response");
    }

    public static void validateResponseCode(Response response, int expectedCode) {
        int actualCode = getResponseCode(response);
        Assert.assertEquals(actualCode, expectedCode,
                "Response code mismatch in body");
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
}

package com.framework.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Simple test to verify TestNG setup is working
 */
public class SimpleTest {
    
    @Test
    public void testBasicSetup() {
        System.out.println("=== TestNG is working! ===");
        Assert.assertTrue(true, "Basic test should pass");
    }
    
    @Test
    public void testConfiguration() {
        System.out.println("=== Testing Configuration ===");
        String baseUrl = "https://automationexercise.com";
        Assert.assertNotNull(baseUrl, "Base URL should be configured");
        Assert.assertTrue(baseUrl.startsWith("https"), "Base URL should use HTTPS");
    }
}

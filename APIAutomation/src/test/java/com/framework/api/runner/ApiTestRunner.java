package com.framework.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Cucumber TestNG runner for API tests with individual test files
 */
@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {"com.framework.api.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
    },
    monochrome = true,
    tags = "@TC1 or @TC2 or @TC3 or @TC4 or @TC5 or @TC6"
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
    // TestNG will automatically run the Cucumber scenarios
}

package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.qameta.allure.Allure;
import utils.LoggerUtil;
import utils.ScenarioContext;

/**
 * Enhanced Cucumber hooks for scenario-level logging
 * Provides test lifecycle management and structured logging
 */
public class TestHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        // Store scenario name in ThreadLocal for TestNG listener access
        ScenarioContext.setCurrentScenarioName(scenario.getName());
        
        LoggerUtil.logInfo("========== STARTING SCENARIO: " + scenario.getName() + " ==========");
        LoggerUtil.logInfo("Scenario Name: " + scenario.getName());
        LoggerUtil.logInfo("Scenario Tags: " + scenario.getSourceTagNames());
        LoggerUtil.logInfo("Started At: " + java.time.LocalDateTime.now());
        LoggerUtil.logInfo("=================================================");
        
        // Add scenario details to Allure
        Allure.addAttachment("Scenario Name", "text/plain", scenario.getName());
        Allure.addAttachment("Scenario Tags", "text/plain", scenario.getSourceTagNames().toString());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerUtil.logError("========== SCENARIO FAILED: " + scenario.getName() + " ==========");
            LoggerUtil.logError("Failure Reason: " + getFailureReason(scenario));
            LoggerUtil.logError("Failed At: " + java.time.LocalDateTime.now());
            
            // Enhanced Allure reporting for failures
            Allure.addAttachment("Failure Status", "text/plain", "Scenario failed: " + scenario.getName());
            Allure.addAttachment("Failure Reason", "text/plain", getFailureReason(scenario));
        } else {
            LoggerUtil.logInfo("========== SCENARIO PASSED: " + scenario.getName() + " ==========");
            LoggerUtil.logInfo("Passed At: " + java.time.LocalDateTime.now());
            
            // Add success status to Allure
            Allure.addAttachment("Success Status", "text/plain", "Scenario passed: " + scenario.getName());
        }
        
        LoggerUtil.logInfo("=================================================");
        
        // Clear scenario name from ThreadLocal
        ScenarioContext.clearCurrentScenarioName();
    }

    private String getFailureReason(Scenario scenario) {
        if (scenario.getStatus() == Status.FAILED) {
            return "Scenario failed - check test logs for detailed error information";
        }
        return "Test failed with unknown reason";
    }
}

package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;
import utils.LoggerUtil;
import utils.ScenarioContext;

/**
 * TestNG listener for API tests
 * Provides custom logging and reporting for test execution
 */
public class ApiTestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = getTestName(result);
        LoggerUtil.logInfo("========== STARTING TEST: " + testName + " ==========");
        LoggerUtil.logInfo("Test Method: " + result.getMethod().getMethodName());
        LoggerUtil.logInfo("Test Description: " + result.getMethod().getDescription());
        LoggerUtil.logInfo("Test Started At: " + java.time.LocalDateTime.now());
        LoggerUtil.logInfo("=================================================");
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = getTestName(result);
        LoggerUtil.logInfo("========== TEST PASSED: " + testName + " ==========");
        LoggerUtil.logInfo("Test Method: " + result.getMethod().getMethodName());
        LoggerUtil.logInfo("Test Status: PASSED");
        LoggerUtil.logInfo("Test Completed At: " + java.time.LocalDateTime.now());
        LoggerUtil.logInfo("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
        LoggerUtil.logInfo("=================================================");
        LoggerUtil.logTestEnd(testName);
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = getTestName(result);
        LoggerUtil.logInfo("========== TEST FAILED: " + testName + " ==========");
        LoggerUtil.logInfo("Test Method: " + result.getMethod().getMethodName());
        LoggerUtil.logInfo("Test Status: FAILED");
        LoggerUtil.logError("Failure Reason: " + result.getThrowable().getMessage());
        LoggerUtil.logError("Stack Trace: " + getStackTrace(result.getThrowable()));
        LoggerUtil.logError("Test Failed At: " + java.time.LocalDateTime.now());
        LoggerUtil.logInfo("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
        LoggerUtil.logInfo("=================================================");
        LoggerUtil.logTestEnd(testName);

        // Attach failure details to Allure
        Allure.addAttachment("Failure Message", "text/plain", result.getThrowable().getMessage());
        Allure.addAttachment("Stack Trace", "text/plain", getStackTrace(result.getThrowable()));
        
        Object response = result.getAttribute("response");
        if (response != null) {
            Allure.addAttachment("Failure Response", "application/json", response.toString());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = getTestName(result);
        LoggerUtil.logWarning("========== TEST SKIPPED: " + testName + " ==========");
        LoggerUtil.logInfo("Test Method: " + result.getMethod().getMethodName());
        LoggerUtil.logInfo("Test Status: SKIPPED");
        LoggerUtil.logInfo("Test Skipped At: " + java.time.LocalDateTime.now());
        LoggerUtil.logInfo("=================================================");
        LoggerUtil.logTestEnd(testName);
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = getTestName(result);
        LoggerUtil.logWarning("TEST FAILED WITHIN SUCCESS PERCENTAGE: " + testName);
        LoggerUtil.logTestEnd(testName);
    }
    
    private String getTestName(ITestResult result) {
        // First, try to get the real Cucumber scenario name from ScenarioContext (ThreadLocal)
        String scenarioName = ScenarioContext.getCurrentScenarioName();
        
        // If we have a real scenario name from ThreadLocal, use it
        if (scenarioName != null && !"Unknown Scenario".equals(scenarioName)) {
            return scenarioName;
        }
        
        // If ThreadLocal is null, extract scenario name from result.getParameters()
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            for (Object param : parameters) {
                if (param != null && param.toString().contains("Scenario")) {
                    return param.toString();
                }
            }
            // If no parameter contains "Scenario", return the first parameter
            if (parameters[0] != null) {
                return parameters[0].toString();
            }
        }
        
        // Final fallback to TestNG method name
        return result.getMethod().getMethodName();
    }
    
    private String getStackTrace(Throwable throwable) {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
    
    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.logInfo("========== API TEST SUITE STARTED ==========");
        LoggerUtil.logInfo("Total Tests: " + context.getAllTestMethods().length);
    }
    
    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.logInfo("========== API TEST SUITE FINISHED ==========");
        LoggerUtil.logInfo("Passed: " + context.getPassedTests().size());
        LoggerUtil.logInfo("Failed: " + context.getFailedTests().size());
        LoggerUtil.logInfo("Skipped: " + context.getSkippedTests().size());
        
        int passedTests = context.getPassedTests().size();
        int failedTests = context.getFailedTests().size();
        int skippedTests = context.getSkippedTests().size();
        int totalTests = passedTests + failedTests + skippedTests;
        
        double successRate = totalTests > 0 ? (passedTests * 100.0) / totalTests : 0.0;
        
        LoggerUtil.logInfo(String.format("Success Rate: %.2f%%", successRate));
    }
}

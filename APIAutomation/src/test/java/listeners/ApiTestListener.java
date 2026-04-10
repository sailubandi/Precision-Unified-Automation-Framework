package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.LoggerUtil;

/**
 * TestNG listener for API tests
 * Provides custom logging and reporting for test execution
 */
public class ApiTestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.logTestStart(result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtil.logInfo("TEST PASSED: " + result.getMethod().getMethodName());
        LoggerUtil.logTestEnd(result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtil.logError("TEST FAILED: " + result.getMethod().getMethodName());
        LoggerUtil.logError("Failure Reason: " + result.getThrowable().getMessage());
        LoggerUtil.logTestEnd(result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.logWarning("TEST SKIPPED: " + result.getMethod().getMethodName());
        LoggerUtil.logTestEnd(result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LoggerUtil.logWarning("TEST FAILED WITHIN SUCCESS PERCENTAGE: " + result.getMethod().getMethodName());
        LoggerUtil.logTestEnd(result.getMethod().getMethodName());
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
        
        int totalTests = context.getAllTestMethods().length; 
        int passedTests = context.getPassedTests().size();
        double successRate = (double) passedTests / totalTests * 100;
        
        LoggerUtil.logInfo(String.format("Success Rate: %.2f%%", successRate));
    }
}

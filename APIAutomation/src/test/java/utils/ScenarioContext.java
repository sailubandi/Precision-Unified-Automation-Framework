package utils;

/**
 * ThreadLocal utility to store and retrieve Cucumber scenario names
 * Used to pass scenario information from TestHooks to TestNG listener
 */
public class ScenarioContext {
    
    private static final ThreadLocal<String> currentScenarioName = new ThreadLocal<>();
    
    /**
     * Set the current scenario name for this thread
     * @param scenarioName The Cucumber scenario name
     */
    public static void setCurrentScenarioName(String scenarioName) {
        currentScenarioName.set(scenarioName);
    }
    
    /**
     * Get the current scenario name for this thread
     * @return The current scenario name, or "Unknown Scenario" if not set
     */
    public static String getCurrentScenarioName() {
        String name = currentScenarioName.get();
        return name != null ? name : "Unknown Scenario";
    }
    
    /**
     * Clear the current scenario name for this thread
     * Should be called after scenario completion
     */
    public static void clearCurrentScenarioName() {
        currentScenarioName.remove();
    }
}

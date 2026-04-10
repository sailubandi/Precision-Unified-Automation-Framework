package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging across the framework
 * Provides centralized logging with Log4j2
 */
public class LoggerUtil {
    
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);
    
    /**
     * Log info message
     * @param message Message to log
     */
    public static void logInfo(String message) {
        logger.info(message);
    }
    
    /**
     * Log error message
     * @param message Message to log
     */
    public static void logError(String message) {
        logger.error(message);
    }
    
    /**
     * Log warning message
     * @param message Message to log
     */
    public static void logWarning(String message) {
        logger.warn(message);
    }
    
    /**
     * Log debug message
     * @param message Message to log
     */
    public static void logDebug(String message) {
        logger.debug(message);
    }
    
    /**
     * Log test start
     * @param testName Test name
     */
    public static void logTestStart(String testName) {
        logger.info("========== STARTING TEST: {} ==========", testName);
    }
    
    /**
     * Log test end
     * @param testName Test name
     */
    public static void logTestEnd(String testName) {
        logger.info("========== FINISHED TEST: {} ==========", testName);
    }
    
    /**
     * Log API request details
     * @param method HTTP method
     * @param endpoint API endpoint
     * @param requestBody Request body (optional)
     */
    public static void logApiRequest(String method, String endpoint, String requestBody) {
        logger.info("API Request - Method: {}, Endpoint: {}", method, endpoint);
        if (requestBody != null && !requestBody.isEmpty()) {
            logger.debug("Request Body: {}", requestBody);
        }
    }
    
    /**
     * Log API response details
     * @param statusCode Response status code
     * @param responseTime Response time in milliseconds
     */
    public static void logApiResponse(int statusCode, long responseTime) {
        logger.info("API Response - Status: {}, Response Time: {}ms", statusCode, responseTime);
    }
}

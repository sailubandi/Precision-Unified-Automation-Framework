package com.framework.api.context;

import com.framework.api.pojo.User;

/**
 * Test context for sharing data between step definitions
 * Provides thread-safe storage for test data across scenarios
 */
public class TestContext {
    
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();
    
    /**
     * Store the current user in context
     * @param user User object to store
     */
    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }
    
    /**
     * Get the current user from context
     * @return Current user object
     */
    public static User getCurrentUser() {
        return currentUser.get();
    }
    
    /**
     * Clear the current user from context
     */
    public static void clearCurrentUser() {
        currentUser.remove();
    }
}

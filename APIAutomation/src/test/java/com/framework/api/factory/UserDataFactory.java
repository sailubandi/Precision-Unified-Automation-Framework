package com.framework.api.factory;

import com.framework.api.pojo.User;

import config.ConfigReader;
import utils.TestDataUtils;

/**
 * Factory class for creating test data
 * Provides centralized test data management following Veeva guidelines
 * Supports both static configuration and dynamic data generation
 */
public class UserDataFactory {
    
    /**
     * Creates a valid user for testing
     * @return User object with valid test data
     */
    public static User createValidUser() {
        User user = new User();
        user.setName(ConfigReader.get("test.user.name", "John Doe"));
        user.setEmail(ConfigReader.get("test.user.email", "john.doe@test.com"));
        user.setPassword(ConfigReader.get("test.user.password", "Test123"));
        user.setTitle(ConfigReader.get("test.user.title", "Mr"));
        user.setBirth_date(ConfigReader.get("test.user.birth_date", "15"));
        user.setBirth_month(ConfigReader.get("test.user.birth_month", "January"));
        user.setBirth_year(ConfigReader.get("test.user.birth_year", "1990"));
        user.setFirstname(ConfigReader.get("test.user.firstname", "John"));
        user.setLastname(ConfigReader.get("test.user.lastname", "Doe"));
        user.setCompany(ConfigReader.get("test.user.company", "Tech Inc"));
        user.setAddress1(ConfigReader.get("test.user.address1", "123 Main St"));
        user.setAddress2(ConfigReader.get("test.user.address2", "Apt 4B"));
        user.setCountry(ConfigReader.get("test.user.country", "USA"));
        user.setZipcode(ConfigReader.get("test.user.zipcode", "10001"));
        user.setState(ConfigReader.get("test.user.state", "NY"));
        user.setCity(ConfigReader.get("test.user.city", "New York"));
        user.setMobile_number(ConfigReader.get("test.user.mobile_number", "1234567890"));
        return user;
    }
    
    /**
     * Creates a user with dynamic random data for testing
     * @return User object with random test data
     */
    public static User createRandomUser() {
        User user = new User();
        user.setName(TestDataUtils.getRandomUsername());
        user.setEmail(TestDataUtils.getRandomEmail());
        user.setPassword(TestDataUtils.getRandomPassword());
        user.setTitle("Mr");
        user.setBirth_date("15");
        user.setBirth_month("January");
        user.setBirth_year("1990");
        user.setFirstname(TestDataUtils.getRandomString(6));
        user.setLastname(TestDataUtils.getRandomString(8));
        user.setCompany(TestDataUtils.getRandomCompany());
        user.setAddress1(TestDataUtils.getRandomAddress());
        user.setAddress2("Apt " + TestDataUtils.getRandomNumber(2));
        user.setCountry("USA");
        user.setZipcode(TestDataUtils.getRandomZipcode());
        user.setState(TestDataUtils.getRandomState());
        user.setCity(TestDataUtils.getRandomCity());
        user.setMobile_number(TestDataUtils.getRandomPhoneNumber());
        return user;
    }
    
    /**
     * Creates a user for update testing
     * @return User object with updated test data
     */
    public static User createUpdatedUser() {
        User user = new User();
        user.setName(ConfigReader.get("test.user.updated.name", "John Smith"));
        user.setEmail(ConfigReader.get("test.user.email", "john.doe@test.com"));
        user.setPassword(ConfigReader.get("test.user.password", "Test123"));
        user.setTitle(ConfigReader.get("test.user.title", "Mr"));
        user.setBirth_date(ConfigReader.get("test.user.birth_date", "15"));
        user.setBirth_month(ConfigReader.get("test.user.birth_month", "January"));
        user.setBirth_year(ConfigReader.get("test.user.birth_year", "1990"));
        user.setFirstname(ConfigReader.get("test.user.updated.firstname", "John"));
        user.setLastname(ConfigReader.get("test.user.updated.lastname", "Smith"));
        user.setCompany(ConfigReader.get("test.user.updated.company", "Tech Corp"));
        user.setAddress1(ConfigReader.get("test.user.updated.address1", "456 Oak Ave"));
        user.setAddress2(ConfigReader.get("test.user.updated.address2", "Suite 10"));
        user.setCountry(ConfigReader.get("test.user.country", "USA"));
        user.setZipcode(ConfigReader.get("test.user.updated.zipcode", "20002"));
        user.setState(ConfigReader.get("test.user.updated.state", "CA"));
        user.setCity(ConfigReader.get("test.user.updated.city", "Los Angeles"));
        user.setMobile_number(ConfigReader.get("test.user.updated.mobile_number", "0987654321"));
        return user;
    }
    
    /**
     * Creates a user with invalid email for negative testing
     * @return User object with invalid email
     */
    public static User createUserWithInvalidEmail() {
        User user = createValidUser();
        user.setEmail(ConfigReader.get("test.user.invalid.email", "invalid-email"));
        return user;
    }
    
    /**
     * Creates a user with missing required fields for negative testing
     * @return User object with missing fields
     */
    public static User createUserWithMissingFields() {
        User user = new User();
        // All fields are null/empty
        return user;
    }
    
    /**
     * Creates user credentials for login/delete operations
     * @return User object with email and password only
     */
    public static User createUserCredentials() {
        User user = new User();
        user.setEmail(ConfigReader.get("test.user.email", "john.doe@test.com"));
        user.setPassword(ConfigReader.get("test.user.password", "Test123"));
        return user;
    }
    
    /**
     * Gets search term for product testing
     * @return Search term from configuration
     */
    public static String getSearchTerm() {
        return ConfigReader.get("test.product.search.term", "top");
    }
    
    /**
     * Gets invalid search term for negative testing
     * @return Invalid search term from configuration
     */
    public static String getInvalidSearchTerm() {
        return ConfigReader.get("test.product.search.invalid.term", "nonexistentproduct123");
    }
    
    /**
     * Gets multiple search terms for parameterized testing
     * @return Array of search terms
     */
    public static String[] getSearchTerms() {
        String terms = ConfigReader.get("test.product.search.terms", "top,tshirt,jean");
        return terms.split(",");
    }
    
    /**
     * Gets random search term for dynamic testing
     * @return Random search term
     */
    public static String getRandomSearchTerm() {
        String[] searchTerms = {"top", "tshirt", "jean", "dress", "shirt", "pants"};
        int randomIndex = (int) (Math.random() * searchTerms.length);
        return searchTerms[randomIndex];
    }
}

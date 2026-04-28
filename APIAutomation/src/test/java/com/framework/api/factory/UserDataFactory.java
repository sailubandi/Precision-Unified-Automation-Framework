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
     * Creates a valid user for testing with unique email
     * @return User object with valid test data
     */
    public static User createValidUser() {
        User user = new User();
        user.setName(ConfigReader.get("test.user.name"));
        user.setEmail(generateUniqueEmail());
        user.setPassword(ConfigReader.get("test.user.password"));
        user.setTitle(ConfigReader.get("test.user.title"));
        user.setBirth_date(ConfigReader.get("test.user.birth_date"));
        user.setBirth_month(ConfigReader.get("test.user.birth_month"));
        user.setBirth_year(ConfigReader.get("test.user.birth_year"));
        user.setFirstname(ConfigReader.get("test.user.firstname"));
        user.setLastname(ConfigReader.get("test.user.lastname"));
        user.setCompany(ConfigReader.get("test.user.company"));
        user.setAddress1(ConfigReader.get("test.user.address1"));
        user.setAddress2(ConfigReader.get("test.user.address2"));
        user.setCountry(ConfigReader.get("test.user.country"));
        user.setZipcode(ConfigReader.get("test.user.zipcode"));
        user.setState(ConfigReader.get("test.user.state"));
        user.setCity(ConfigReader.get("test.user.city"));
        user.setMobile_number(ConfigReader.get("test.user.mobile_number"));
        return user;
    }
    
    /**
     * Generates a unique email using timestamp
     * @return Unique email address
     */
    private static String generateUniqueEmail() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String domain = ConfigReader.get("test.user.email.domain");
        return "testuser_" + timestamp + "@" + domain;
    }
    
    /**
     * Creates a user with unique email for end-to-end testing
     * @return User object with unique test data
     */
    public static User createUniqueUser() {
        User user = new User();
        user.setName(ConfigReader.get("test.user.name"));
        user.setEmail(generateUniqueEmail());
        user.setPassword(ConfigReader.get("test.user.password"));
        user.setTitle(ConfigReader.get("test.user.title"));
        user.setBirth_date(ConfigReader.get("test.user.birth_date"));
        user.setBirth_month(ConfigReader.get("test.user.birth_month"));
        user.setBirth_year(ConfigReader.get("test.user.birth_year"));
        user.setFirstname(ConfigReader.get("test.user.firstname"));
        user.setLastname(ConfigReader.get("test.user.lastname"));
        user.setCompany(ConfigReader.get("test.user.company"));
        user.setAddress1(ConfigReader.get("test.user.address1"));
        user.setAddress2(ConfigReader.get("test.user.address2"));
        user.setCountry(ConfigReader.get("test.user.country"));
        user.setZipcode(ConfigReader.get("test.user.zipcode"));
        user.setState(ConfigReader.get("test.user.state"));
        user.setCity(ConfigReader.get("test.user.city"));
        user.setMobile_number(ConfigReader.get("test.user.mobile_number"));
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
        user.setTitle(ConfigReader.get("test.user.default.title"));
        user.setBirth_date(ConfigReader.get("test.user.default.birth_date"));
        user.setBirth_month(ConfigReader.get("test.user.default.birth_month"));
        user.setBirth_year(ConfigReader.get("test.user.default.birth_year"));
        user.setFirstname(TestDataUtils.getRandomString(6));
        user.setLastname(TestDataUtils.getRandomString(8));
        user.setCompany(TestDataUtils.getRandomCompany());
        user.setAddress1(TestDataUtils.getRandomAddress());
        user.setAddress2(ConfigReader.get("test.user.default.address.prefix") + " " + TestDataUtils.getRandomNumber(2));
        user.setCountry(ConfigReader.get("test.user.default.country"));
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
        user.setName(ConfigReader.get("test.user.updated.name"));
        user.setEmail(ConfigReader.get("test.user.email"));
        user.setPassword(ConfigReader.get("test.user.password"));
        user.setTitle(ConfigReader.get("test.user.title"));
        user.setBirth_date(ConfigReader.get("test.user.birth_date"));
        user.setBirth_month(ConfigReader.get("test.user.birth_month"));
        user.setBirth_year(ConfigReader.get("test.user.birth_year"));
        user.setFirstname(ConfigReader.get("test.user.updated.firstname"));
        user.setLastname(ConfigReader.get("test.user.updated.lastname"));
        user.setCompany(ConfigReader.get("test.user.updated.company"));
        user.setAddress1(ConfigReader.get("test.user.updated.address1"));
        user.setAddress2(ConfigReader.get("test.user.updated.address2"));
        user.setCountry(ConfigReader.get("test.user.country"));
        user.setZipcode(ConfigReader.get("test.user.updated.zipcode"));
        user.setState(ConfigReader.get("test.user.updated.state"));
        user.setCity(ConfigReader.get("test.user.updated.city"));
        user.setMobile_number(ConfigReader.get("test.user.updated.mobile_number"));
        return user;
    }
    
    /**
     * Creates a user with invalid email for negative testing
     * @return User object with invalid email
     */
    public static User createUserWithInvalidEmail() {
        User user = createValidUser();
        user.setEmail(ConfigReader.get("test.user.invalid.email"));
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
        user.setEmail(ConfigReader.get("test.user.email"));
        user.setPassword(ConfigReader.get("test.user.password"));
        return user;
    }
    
    /**
     * Gets search term for product testing
     * @return Search term from configuration
     */
    public static String getSearchTerm() {
        return ConfigReader.get("test.product.search.term");
    }
    
    /**
     * Gets invalid search term for negative testing
     * @return Invalid search term from configuration
     */
    public static String getInvalidSearchTerm() {
        return ConfigReader.get("test.product.search.invalid.term");
    }
    
    /**
     * Gets multiple search terms for parameterized testing
     * @return Array of search terms
     */
    public static String[] getSearchTerms() {
        String terms = ConfigReader.get("test.product.search.terms");
        return terms.split(",");
    }
    
    /**
     * Gets random search term for dynamic testing
     * @return Random search term
     */
    public static String getRandomSearchTerm() {
        String[] searchTerms = getSearchTerms();
        int randomIndex = (int) (Math.random() * searchTerms.length);
        return searchTerms[randomIndex];
    }
    
    /**
     * Creates a user with existing email for negative testing
     * @return User object with existing email
     */
    public static User createUserWithExistingEmail() {
        User user = new User();
        user.setName(ConfigReader.get("test.user.name"));
        user.setEmail(ConfigReader.get("test.user.existing.email")); // Fixed existing email
        user.setPassword(ConfigReader.get("test.user.password"));
        user.setTitle(ConfigReader.get("test.user.title"));
        user.setBirth_date(ConfigReader.get("test.user.birth_date"));
        user.setBirth_month(ConfigReader.get("test.user.birth_month"));
        user.setBirth_year(ConfigReader.get("test.user.birth_year"));
        user.setFirstname(ConfigReader.get("test.user.firstname"));
        user.setLastname(ConfigReader.get("test.user.lastname"));
        user.setCompany(ConfigReader.get("test.user.company"));
        user.setAddress1(ConfigReader.get("test.user.address1"));
        user.setAddress2(ConfigReader.get("test.user.address2"));
        user.setCountry(ConfigReader.get("test.user.country"));
        user.setZipcode(ConfigReader.get("test.user.zipcode"));
        user.setState(ConfigReader.get("test.user.state"));
        user.setCity(ConfigReader.get("test.user.city"));
        user.setMobile_number(ConfigReader.get("test.user.mobile_number"));
        return user;
    }
}

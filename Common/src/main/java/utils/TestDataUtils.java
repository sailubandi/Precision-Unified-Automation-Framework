package utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class TestDataUtils {

    private TestDataUtils() {}

    /**
     * Generates random alphabetic string
     * @param length Length of the string to generate
     * @return Random alphabetic string
     */
    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * Generates random numeric string
     * @param length Length of the string to generate
     * @return Random numeric string
     */
    public static String getRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * Generates random alphanumeric string
     * @param length Length of the string to generate
     * @return Random alphanumeric string
     */
    public static String getRandomAlphaNumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * Generates random email address
     * @return Random email in format user_xxxxx@test.com
     */
    public static String getRandomEmail() {
        return "user_" + getRandomAlphaNumeric(5) + "@test.com";
    }

    /**
     * Generates random username
     * @return Random username in format user_xxxxx
     */
    public static String getRandomUsername() {
        return "user_" + getRandomString(5);
    }

    /**
     * Generates random phone number
     * @return Random 10-digit phone number
     */
    public static String getRandomPhoneNumber() {
        return getRandomNumber(10);
    }

    /**
     * Generates random password
     * @return Random password with letters and numbers
     */
    public static String getRandomPassword() {
        return getRandomAlphaNumeric(8);
    }

    /**
     * Generates random name
     * @return Random name
     */
    public static String getRandomName() {
        return getRandomString(8);
    }

    /**
     * Generates random company name
     * @return Random company name
     */
    public static String getRandomCompany() {
        return getRandomString(10) + " Corp";
    }

    /**
     * Generates random address
     * @return Random address
     */
    public static String getRandomAddress() {
        return getRandomNumber(3) + " " + getRandomString(8) + " St";
    }

    /**
     * Generates random city
     * @return Random city name
     */
    public static String getRandomCity() {
        return getRandomString(7);
    }

    /**
     * Generates random state code
     * @return Random 2-letter state code
     */
    public static String getRandomState() {
        return getRandomString(2).toUpperCase();
    }

    /**
     * Generates random zipcode
     * @return Random 5-digit zipcode
     */
    public static String getRandomZipcode() {
        return getRandomNumber(5);
    }
}
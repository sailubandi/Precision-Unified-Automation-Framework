package utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class TestDataUtils {

    private TestDataUtils() {}

    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String getRandomAlphaNumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String getRandomEmail() {
        return "user_" + getRandomAlphaNumeric(5) + "@test.com";
    }

    public static String getRandomUsername() {
        return "user_" + getRandomString(5);
    }
}
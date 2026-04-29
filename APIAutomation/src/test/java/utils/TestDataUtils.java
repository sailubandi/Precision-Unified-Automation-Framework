package utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for generating test data
 * Provides random data generation for testing purposes
 */
public class TestDataUtils {
    
    private static final Random random = new Random();
    
    // Character sets for different types of data
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String ALPHANUMERIC = ALPHABET + NUMBERS;
    private static final String UPPERCASE = ALPHABET.toUpperCase();
    
    // Sample data for realistic test data generation
    private static final String[] FIRST_NAMES = {"John", "Jane", "Mike", "Sarah", "David", "Emily", "Chris", "Lisa", "Tom", "Anna"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
    private static final String[] COMPANIES = {"Tech Corp", "Digital Solutions", "Innovation Labs", "Cloud Systems", "Data Analytics", "Software Pro", "Web Design", "Mobile Dev", "AI Solutions", "Cyber Security"};
    private static final String[] CITIES = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
    private static final String[] STATES = {"California", "Texas", "New York", "Florida", "Illinois", "Pennsylvania", "Ohio", "Georgia", "North Carolina", "Michigan"};
    private static final String[] STREETS = {"Main St", "Oak Ave", "Pine Rd", "Maple Dr", "Cedar Ln", "Elm Way", "Park Blvd", "Washington St", "Lincoln Ave", "Jefferson Dr"};
    
    /**
     * Generates a random username
     * @return Random username string
     */
    public static String getRandomUsername() {
        String firstName = getRandomString(5).toLowerCase();
        String lastName = getRandomString(7).toLowerCase();
        String number = getRandomNumber(3);
        return firstName + lastName + number;
    }
    
    /**
     * Generates a random email address
     * @return Random email string
     */
    public static String getRandomEmail() {
        String username = getRandomString(8).toLowerCase();
        String domain = getRandomString(6).toLowerCase();
        String tld = getRandomString(2).toLowerCase();
        return username + "@" + domain + "." + tld;
    }
    
    /**
     * Generates a random password
     * @return Random password string
     */
    public static String getRandomPassword() {
        String lowercase = getRandomString(4, ALPHABET);
        String uppercase = getRandomString(2, UPPERCASE);
        String numbers = getRandomNumber(2);
        String special = getRandomString(2, "!@#$%^&*");
        return lowercase + uppercase + numbers + special;
    }
    
    /**
     * Generates a random company name
     * @return Random company name
     */
    public static String getRandomCompany() {
        return COMPANIES[random.nextInt(COMPANIES.length)];
    }
    
    /**
     * Generates a random address
     * @return Random address string
     */
    public static String getRandomAddress() {
        int number = random.nextInt(9999) + 1;
        String street = STREETS[random.nextInt(STREETS.length)];
        return number + " " + street;
    }
    
    /**
     * Generates a random zipcode
     * @return Random zipcode string
     */
    public static String getRandomZipcode() {
        return getRandomNumber(5);
    }
    
    /**
     * Generates a random state
     * @return Random state name
     */
    public static String getRandomState() {
        return STATES[random.nextInt(STATES.length)];
    }
    
    /**
     * Generates a random city
     * @return Random city name
     */
    public static String getRandomCity() {
        return CITIES[random.nextInt(CITIES.length)];
    }
    
    /**
     * Generates a random phone number
     * @return Random phone number string
     */
    public static String getRandomPhoneNumber() {
        String area = getRandomNumber(3);
        String prefix = getRandomNumber(3);
        String line = getRandomNumber(4);
        return "(" + area + ") " + prefix + "-" + line;
    }
    
    /**
     * Generates a random string of specified length
     * @param length Length of the string to generate
     * @return Random string
     */
    public static String getRandomString(int length) {
        return getRandomString(length, ALPHANUMERIC);
    }
    
    /**
     * Generates a random string of specified length from given character set
     * @param length Length of the string to generate
     * @param charset Character set to use
     * @return Random string
     */
    public static String getRandomString(int length, String charset) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charset.length());
            sb.append(charset.charAt(index));
        }
        return sb.toString();
    }
    
    /**
     * Generates a random number string of specified length
     * @param length Length of the number string
     * @return Random number string
     */
    public static String getRandomNumber(int length) {
        return getRandomString(length, NUMBERS);
    }
    
    /**
     * Generates a random integer between min and max (inclusive)
     * @param min Minimum value
     * @param max Maximum value
     * @return Random integer
     */
    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    /**
     * Generates a random boolean
     * @return Random boolean value
     */
    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }
    
    /**
     * Picks a random element from an array
     * @param array Array to pick from
     * @param <T> Type of array elements
     * @return Random element from array
     */
    public static <T> T getRandomElement(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return array[random.nextInt(array.length)];
    }
}

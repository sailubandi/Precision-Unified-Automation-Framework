package config;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    private ConfigReader() {}

    static {
        try {
            InputStream input;

            // First try with FrameworkConstants path
            try {
                input = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH);
            } catch (IOException e) {
                // Fallback: try to load from resources
                input = ConfigReader.class
                        .getClassLoader()
                        .getResourceAsStream("config.properties");
            }

            if (input == null) {
                throw new RuntimeException("config.properties not found in resources or at path: "
                        + FrameworkConstants.CONFIG_FILE_PATH);
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Key not found in config.properties: " + key);
        }
        return value;
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}

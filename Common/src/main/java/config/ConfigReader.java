package config;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    private ConfigReader() {}

    static {
        try (FileInputStream fis =
                     new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {

            properties.load(fis);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config.properties from: "
                            + FrameworkConstants.CONFIG_FILE_PATH, e);
        }
    }

    public static String get(String key) {

        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            throw new RuntimeException(
                    "Key not found in config.properties: " + key);
        }

        return value;
    }
}

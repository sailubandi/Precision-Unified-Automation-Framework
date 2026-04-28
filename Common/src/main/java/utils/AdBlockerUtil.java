package utils;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;

public class AdBlockerUtil {

    public static ChromeOptions getAdBlockedChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        // Disable notifications & unnecessary UI
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");

        // Block images (optional - speeds up execution)
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);

        // Block ads domains
        Map<String, Object> contentSettings = new HashMap<>();
        contentSettings.put("ads", 2);

        prefs.put("profile.default_content_setting_values", contentSettings);

        options.setExperimentalOption("prefs", prefs);

        // Block known ad URLs via host rules
        options.addArguments("--host-rules=MAP doubleclick.net 127.0.0.1, " +
                "MAP googlesyndication.com 127.0.0.1, " +
                "MAP googleads.g.doubleclick.net 127.0.0.1");

        return options;
    }
}
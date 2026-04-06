package driver;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.AdBlockerUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver initDriver() {

        String browser = ConfigReader.get("browser").toLowerCase();

        WebDriver driver;

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(AdBlockerUtil.getAdBlockedChromeOptions());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();

        DriverManager.setDriver(driver);
        return driver;
    }
}
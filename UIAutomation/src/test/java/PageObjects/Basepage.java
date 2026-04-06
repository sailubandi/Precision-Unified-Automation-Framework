package PageObjects;

import driver.DriverManager;

import org.openqa.selenium.WebDriver;

public class Basepage {

    protected WebDriver driver;
    public Basepage() {
        this.driver = DriverManager.getDriver();
    }
}
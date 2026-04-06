package PageObjects;


import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import utils.TestDataUtils;
import utils.WaitUtils;

public class RegisterPage extends Basepage {

    private By enterAccountText = By.xpath("//b[text()='Enter Account Information']");

    private By titleMr = By.id("id_gender1");
    private By titleMrs = By.id("id_gender2");
    private By password = By.id("password");

    private By day = By.id("days");
    private By month = By.id("months");
    private By year = By.id("years");

    private By newsletter = By.id("newsletter");
    private By offers = By.id("optin");

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By address2 = By.id("address2");
    private By country = By.id("country");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobile = By.id("mobile_number");

    private By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public boolean isEnterAccountVisible() {
        return WaitUtils.waitForElement(enterAccountText).isDisplayed();
    }

    public void fillAccountInformation() {

        if (ConfigReader.get("title").equalsIgnoreCase("Mr")) {
            WaitUtils.waitForElement(titleMr).click();
        }
        else
        	 WaitUtils.waitForElement(titleMrs).click();

        WaitUtils.waitForElement(password)
                .sendKeys(TestDataUtils.getRandomAlphaNumeric(8));

        new Select(WaitUtils.waitForElement(day)).selectByValue(ConfigReader.get("day"));
        new Select(WaitUtils.waitForElement(month)).selectByValue(ConfigReader.get("month"));
        new Select(WaitUtils.waitForElement(year)).selectByValue(ConfigReader.get("year"));
    }

    public void selectCheckboxes() {
        WaitUtils.waitForElement(newsletter).click();
        WaitUtils.waitForElement(offers).click();
    }

    public void fillAddressDetails() {

        WaitUtils.waitForElement(firstName)
                .sendKeys(TestDataUtils.getRandomString(5));

        WaitUtils.waitForElement(lastName)
                .sendKeys(TestDataUtils.getRandomString(5));

        WaitUtils.waitForElement(company).sendKeys(TestDataUtils.getRandomString(5));
        WaitUtils.waitForElement(address1).sendKeys(TestDataUtils.getRandomString(5));
        WaitUtils.waitForElement(address2).sendKeys(TestDataUtils.getRandomString(5));

        new Select(WaitUtils.waitForElement(country))
                .selectByVisibleText(ConfigReader.get("country"));

        WaitUtils.waitForElement(state).sendKeys(ConfigReader.get("state"));
        WaitUtils.waitForElement(city).sendKeys(ConfigReader.get("city"));

        WaitUtils.waitForElement(zipcode)
                .sendKeys(TestDataUtils.getRandomNumber(6));

        WaitUtils.waitForElement(mobile)
                .sendKeys(TestDataUtils.getRandomNumber(10));
    }

    public void clickCreateAccount() {
        WaitUtils.waitForElement(createAccountBtn).click();
    }
}

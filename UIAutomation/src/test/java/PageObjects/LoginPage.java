package PageObjects;


import org.openqa.selenium.By;
import utils.TestDataUtils;
import utils.WaitUtils;

public class LoginPage extends Basepage {

    private By newUserText = By.xpath("//h2[text()='New User Signup!']");
    private By nameField = By.name("name");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[@data-qa='signup-button']");
    public boolean isNewUserVisible() {
        //return false;
    	return WaitUtils.waitForElement(newUserText).isDisplayed();
    }

    public void enterRegisterDetails() {
        WaitUtils.waitForElement(nameField)
                .sendKeys(TestDataUtils.getRandomUsername());

        WaitUtils.waitForElement(emailField)
                .sendKeys(TestDataUtils.getRandomEmail());
    }

    public void clickSignup() {
        WaitUtils.waitForElement(signupBtn).click();
    }
}
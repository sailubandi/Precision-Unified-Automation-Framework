package PageObjects;


import org.openqa.selenium.By;
import utils.TestDataUtils;
import utils.WaitUtils;

public class LoginPage extends Basepage {

    private By newUserText = By.xpath("//h2[text()='New User Signup!']");
    private By nameField = By.name("name");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[@data-qa='signup-button']");
    private By loginText = By.xpath("//h2[text()='Login to your account']");
    private By emailInput = By.xpath("//input[@data-qa='login-email']");
    private By passwordInput = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");
    private By errormessage=By.xpath("//p[normalize-space()='Your email or password is incorrect!']");
    public static String savedEmail;
    public static String savedPassword;
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
    public boolean isLoginVisible() {
        //return false;
    	return WaitUtils.waitForElement(loginText).isDisplayed();
    }
    
    public void enterLoginDetails(String email,String password) {
        WaitUtils.waitForElement(emailInput)
                .sendKeys(email);

        WaitUtils.waitForElement(passwordInput)
                .sendKeys(password);
    }
    public void clickLogin()
    {
    	WaitUtils.waitForElement(loginBtn).click();
    }
    public boolean isErrorMessageVisible() {
        //return false;
    	return WaitUtils.waitForElement(errormessage).isDisplayed();
    }
}
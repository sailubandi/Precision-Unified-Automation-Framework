package PageObjects;


import org.openqa.selenium.By;
import utils.WaitUtils;

public class AccountPage extends Basepage {

    private By accountCreated = By.xpath("//b[text()='Account Created!']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");
    private By loggedInText = By.xpath("//a[contains(text(),'Logged in as')]");
    private By deleteAccountBtn = By.xpath("//a[contains(text(),'Delete Account')]");
    private By accountDeleted = By.xpath("//b[text()='Account Deleted!']");

    public boolean isAccountCreated() {
        return WaitUtils.waitForElement(accountCreated).isDisplayed();
    }

    public void clickContinue() {
        WaitUtils.waitForElement(continueBtn).click();
    }

    public boolean isLoggedIn() {
        return WaitUtils.waitForElement(loggedInText).isDisplayed();
    }

    public void deleteAccount() {
        WaitUtils.waitForElement(deleteAccountBtn).click();
    }

    public boolean isAccountDeleted() {
        return WaitUtils.waitForElement(accountDeleted).isDisplayed();
    }
}
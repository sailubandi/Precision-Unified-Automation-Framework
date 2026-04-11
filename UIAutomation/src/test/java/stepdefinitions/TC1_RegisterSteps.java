package stepdefinitions;

import config.ConfigReader;
import driver.DriverManager;
import io.cucumber.java.en.*;
import logger.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import PageObjects.*;

public class TC1_RegisterSteps {

    private static final Logger log = LogManager.getLogger(TC1_RegisterSteps.class);

    HomePage home = new HomePage();
    LoginPage login = new LoginPage();
    RegisterPage register = new RegisterPage();
    AccountPage account = new AccountPage();

    @Given("user launches browser")
    public void launchBrowser() {
        log.info("Launching browser");
        DriverManager.getDriver();
    }

    @When("user navigates to application")
    public void navigate() {
        String url = ConfigReader.get("base.url");
        log.info("Navigating to URL: {}", url);
        DriverManager.getDriver().get(url);
    }

    @Then("home page should be visible")
    public void verifyHome() {
        log.info("Verifying home page is visible");
        Assert.assertTrue(home.isHomeVisible());
    }

    @When("user clicks on Signup Login button")
    public void clickSignup() {
        log.info("Clicking Signup/Login button");
        home.clickSignupLogin();
    }

    @Then("New User Signup should be visible")
    public void verifySignup() {
        log.info("Verifying New User Signup text");
        Assert.assertTrue(login.isNewUserVisible());
    }

    @When("user enters name and email")
    public void enterDetails() {
        log.info("Entering name and email");
        login.enterRegisterDetails();
    }

    @And("clicks Signup button")
    public void clickSignupBtn() {
        log.info("Clicking Signup button");
        login.clickSignup();
    }

    @Then("Enter Account Information should be visible")
    public void verifyAccountPage() {
        log.info("Verifying Enter Account Information page");
        Assert.assertTrue(register.isEnterAccountVisible());
    }

    // Step 9
    @When("user fills account information")
    public void fillAccount() {
        log.info("Filling account information (title, password, DOB)");
        register.fillAccountInformation();
    }

    // Step 10 & 11
    @And("user selects newsletter and offers")
    public void selectCheckbox() {
        log.info("Selecting newsletter and offers checkboxes");
        register.selectCheckboxes();
    }

    // Step 12
    @And("user fills address details")
    public void fillAddress() {
        log.info("Filling address details");
        register.fillAddressDetails();
    }

    // Step 13
    @And("user clicks Create Account button")
    public void createAccount() {
        log.info("Clicking Create Account button");
        register.clickCreateAccount();
    }

    // Step 14
    @Then("ACCOUNT CREATED should be visible")
    public void verifyCreated() {
        log.info("Verifying Account Created message");
        Assert.assertTrue(account.isAccountCreated());
    }

    // Step 15
    @When("user clicks Continue button")
    public void clickContinue() {
        log.info("Clicking Continue button");
        account.clickContinue();
    }

    // Step 16
    @Then("Logged in as username should be visible")
    public void verifyLogin() {
        log.info("Verifying user is logged in");
        Assert.assertTrue(account.isLoggedIn());
    }

    // Step 17
    @When("user clicks Delete Account button")
    public void deleteAccount() {
        log.info("Clicking Delete Account button");
        account.deleteAccount();
    }

    // Step 18
    @Then("ACCOUNT DELETED should be visible")
    public void verifyDeleted() {
        log.info("Verifying Account Deleted message");
        Assert.assertTrue(account.isAccountDeleted());

        log.info("Clicking Continue after deletion");
        account.clickContinue();
    }
}
package stepdefinitions;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import PageObjects.LoginPage;
import constants.FrameworkConstants;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.internal.org.jline.utils.Log;
import logger.LogManager;
import utils.ExcelUtils;

public class LoginSteps {
	ExcelUtils ex=new ExcelUtils(FrameworkConstants.ROOT_PATH+ "/src/test/resources/Book1.xlsx");
	private static final Logger log = LogManager.getLogger(RegisterSteps.class);
	LoginPage login = new LoginPage();
	@Then("Login to your account text should be visible")
    public void login_to_your_account_text_should_be_visible() {
			log.info("verifing login to your account test is visible");
			Assert.assertTrue(login.isLoginVisible());
    }

    @When("user enters correct email and password")
    public void user_enters_email_and_password() throws IOException {
    		log.info("entering eamil and password");
    		login.enterLoginDetails(ex.getCellData("Sheet1", 1, 0), ex.getCellData("Sheet1", 1, 1));
        
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
    	log.info("clicks on login button");
    	login.clickLogin();
        
    }

}

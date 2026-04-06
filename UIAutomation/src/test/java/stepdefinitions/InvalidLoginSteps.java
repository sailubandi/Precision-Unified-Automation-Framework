package stepdefinitions;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import PageObjects.LoginPage;
import constants.FrameworkConstants;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logger.LogManager;
import utils.ExcelUtils;

public class InvalidLoginSteps {
	ExcelUtils ex=new ExcelUtils(FrameworkConstants.ROOT_PATH+ "/src/test/resources/Book1.xlsx");
	private static final Logger log = LogManager.getLogger(RegisterSteps.class);
	LoginPage login = new LoginPage();
	 @When("user enters incorrect email and password")
	    public void enterInvalidCredentials() throws IOException {
		 log.info("entering incorrect eamil and password");
 		login.enterLoginDetails(ex.getCellData("Sheet1", 2, 0), ex.getCellData("Sheet1", 2, 1));
	    }
	 @Then("error message should be displayed")
	    public void verifyErrorMessage() {
		 log.info("verifying error message is displayed or not");
		 Assert.assertTrue(login.isErrorMessageVisible());
	    }

}

package PageObjects;


import org.openqa.selenium.By;
import utils.WaitUtils;

public class HomePage extends Basepage {

    private By logo = By.xpath("//img[@alt='Website for automation practice']");
    private By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    public boolean isHomeVisible() {
        return WaitUtils.waitForElement(logo).isDisplayed();
    }

    public void clickSignupLogin() {
        WaitUtils.waitForElement(signupLoginBtn).click();
    }
}
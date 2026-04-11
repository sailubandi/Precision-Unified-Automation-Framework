package PageObjects;


import org.openqa.selenium.By;
import utils.WaitUtils;

public class HomePage extends Basepage {

    private By logo = By.xpath("//img[@alt='Website for automation practice']");
    private By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By productbtn=By.xpath("//a[@href='/products']");
    private By cartBtn = By.xpath("//a[@href='/view_cart']");
    public boolean isHomeVisible() {
        return WaitUtils.waitForElement(logo).isDisplayed();
    }

    public void clickSignupLogin() {
        WaitUtils.waitForElement(signupLoginBtn).click();
    }
    public void clickOnProduct() {
        WaitUtils.waitForElement(productbtn).click();
    }
    public void clickCart() {
        WaitUtils.waitForElement(cartBtn).click();
    }
}
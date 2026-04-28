package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        // Handle cart modal that might be blocking the cart button
        By cartModal = By.id("cartModal");
        try {
            // Try to close modal if present by clicking close button or escape
            WebElement modal = driver.findElement(cartModal);
            if (modal.isDisplayed()) {
                // Try to find and click close button in modal
                try {
                    WebElement closeButton = modal.findElement(By.cssSelector(".modal-header .close, .close, [data-dismiss='modal']"));
                    closeButton.click();
                    Thread.sleep(500);
                } catch (Exception ex) {
                    // If no close button, try clicking outside modal
                    driver.findElement(By.tagName("body")).click();
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            // Modal might not be present, continue
        }
        
        // Use JavaScript click to bypass any remaining interference
        WebElement cartElement = driver.findElement(cartBtn);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", cartElement);
    }
}
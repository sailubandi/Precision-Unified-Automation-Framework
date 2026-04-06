package PageObjects;

import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.WaitUtils;

public class ProductPage extends Basepage {

    private By products = By.cssSelector(".product-image-wrapper");
    private By addToCartBtn = By.xpath(".//a[contains(text(),'Add to cart')]");
    private By continueBtn = By.xpath("//button[text()='Continue Shopping']");
    private By viewCartBtn = By.xpath("//u[text()='View Cart']");
    private By productName = By.cssSelector(".productinfo p");
    private By productPrice = By.cssSelector(".productinfo h2");

    private HashMap<String, HashMap<String, Integer>> cartMap = new HashMap<>();
    private int grandTotal = 0;

    public boolean isProductPageVisible() {
        return WaitUtils.waitForElement(products).isDisplayed();
    }

    public void addProduct(int index) {

        List<WebElement> productList = WaitUtils.waitForAllVisibleElements(products);

        if (index > productList.size()) {
            throw new RuntimeException("Invalid product index: " + index);
        }

        WebElement product = productList.get(index - 1);

        String name = product.findElement(productName).getText();
        int price = Integer.parseInt(
                product.findElement(productPrice).getText().replaceAll("[^0-9]", "")
        );

        if (cartMap.containsKey(name)) {
            HashMap<String, Integer> inner = cartMap.get(name);
            int newQty = inner.get("quantity") + 1;
            int newTotal = newQty * inner.get("price");
            inner.put("quantity", newQty);
            inner.put("total", newTotal);
        } else {
            HashMap<String, Integer> inner = new HashMap<>();
            inner.put("price", price);
            inner.put("quantity", 1);
            inner.put("total", price);
            cartMap.put(name, inner);
        }

        grandTotal += price;

        Actions act = new Actions(driver);
        act.moveToElement(product).pause(500).perform();

        WebElement addBtn = product.findElement(addToCartBtn);
        WaitUtils.waitForElementToBeClickable(addBtn);

        act.moveToElement(addBtn).click().perform();
    }

    public void clickContinuebtn() {
        WaitUtils.waitForElementToBeClickable(continueBtn).click();
    }

    public void clickViewCart() {
        WaitUtils.waitForElementToBeClickable(viewCartBtn).click();
    }

    public HashMap<String, HashMap<String, Integer>> getSelectedProducts() {
        return cartMap;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

 
}
package PageObjects;

import java.util.*;
import org.openqa.selenium.*;
import utils.WaitUtils;

public class CartPage extends Basepage {

    private By cartItems = By.xpath("//tr[contains(@id,'product-')]");
    private By productName = By.xpath(".//td[@class='cart_description']/h4/a");
    private By productPrice = By.xpath(".//td[@class='cart_price']/p");
    private By productQuantity = By.xpath(".//td[@class='cart_quantity']/button");
    private By productTotal = By.xpath(".//td[@class='cart_total']/p");
    private By deleteBtn = By.xpath(".//a[@class='cart_quantity_delete']");
    private By checkoutBtn = By.xpath("//a[contains(text(),'Proceed To Checkout')]");

    private ProductPage productPage;
    private String productname;
    
    public CartPage(ProductPage productPage) {
        this.productPage = productPage;
    }
    public boolean isCartPageDisplayed() {
        return WaitUtils.waitForElement(checkoutBtn).isDisplayed();
    }
     public HashMap<String, HashMap<String, Integer>> getCartUIData() {

        List<WebElement> items = WaitUtils.waitForAllVisibleElements(cartItems);

        HashMap<String, HashMap<String, Integer>> uiCart = new HashMap<>();

        for (WebElement item : items) {

            String name = item.findElement(productName).getText().trim();
            int price = Integer.parseInt(item.findElement(productPrice).getText().replaceAll("[^0-9]", ""));
            int qty = Integer.parseInt(item.findElement(productQuantity).getText().replaceAll("[^0-9]", ""));
            int total = Integer.parseInt(item.findElement(productTotal).getText().replaceAll("[^0-9]", ""));

            HashMap<String, Integer> inner = new HashMap<>();
            inner.put("price", price);
            inner.put("quantity", qty);
            inner.put("total", total);

            uiCart.put(name, inner);
        }

        return uiCart;
    }

     public boolean verifyCartMatchesLocal() {

        HashMap<String, HashMap<String, Integer>> ui = getCartUIData();
        HashMap<String, HashMap<String, Integer>> local = productPage.getSelectedProducts();

        if (ui.size() != local.size()) return false;

        for (String name : ui.keySet()) {

            if (!local.containsKey(name))
                return false;

            HashMap<String, Integer> uiInner = ui.get(name);
            HashMap<String, Integer> localInner = local.get(name);

            if (!uiInner.equals(localInner))
                return false;
        }

        return true;
    }
     public boolean verifyProducts()
     {
    	 HashMap<String, HashMap<String, Integer>> ui = getCartUIData();
         HashMap<String, HashMap<String, Integer>> local = productPage.getSelectedProducts();

         if (ui.size() != local.size()) return false;

         for (String name : ui.keySet()) {

             if (!local.containsKey(name))
                 return false;
         }

         return true;
     }
     public boolean isProductDeleted()
     {
    	 HashMap<String, HashMap<String, Integer>> ui = getCartUIData();

         Set<String>s=ui.keySet();
         if(s.contains(productname))
         return false;
         return true;
     }

    
    public boolean verifyGrandTotal() {

        HashMap<String, HashMap<String, Integer>> ui = getCartUIData();

        int sum = 0;

        for (String product : ui.keySet()) {
            sum += ui.get(product).get("total");
        }

        return sum == productPage.getGrandTotal();
    }
}
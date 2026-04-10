package stepdefinitions;

import io.cucumber.java.en.*;
import logger.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import PageObjects.HomePage;
import PageObjects.ProductPage;
import PageObjects.CartPage;

public class TC5_RemoveFromCartSteps {

    HomePage home = new HomePage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage(product);

    private static final Logger log = LogManager.getLogger(TC5_RemoveFromCartSteps.class);


    @When("user adds products to cart")
    public void user_adds_products_to_cart() {
        log.info("User adds products to cart");
        home.clickOnProduct();
        product.addProduct(1);
        product.clickContinuebtn();
        product.addProduct(2);
    }

    @When("user clicks Cart button")
    public void user_clicks_cart_button() {
        log.info("User clicks Cart button");
        home.clickCart();
    }

    @Then("cart page should be displayed")
    public void cart_page_should_be_displayed() {
        log.info("Verifying cart page is displayed");
        Assert.assertTrue(cart.isCartPageDisplayed());
    }

    @When("user clicks X button of a product")
    public void user_clicks_x_button_of_product() {
        log.info("User clicks delete (X) button of product");

        cart.removeProduct(0);
    }

    @Then("product should be removed from cart")
    public void product_should_be_removed_from_cart() {
        log.info("Verifying product is removed from cart");

        Assert.assertEquals(cart.isProductDeleted(),true);
    }
}
package stepdefinitions;

import io.cucumber.java.en.*;
import logger.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import PageObjects.HomePage;
import PageObjects.ProductPage;
import PageObjects.CartPage;

public class TC4_AddToCartSteps {

    HomePage home = new HomePage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage(product);

    private static final Logger log = LogManager.getLogger(TC1_RegisterSteps.class);

    @When("user clicks on Products button")
    public void user_clicks_on_products_button() {
        log.info("User clicks on Products button");
        home.clickOnProduct();
    }

    @When("user hovers over first product and adds to cart")
    public void user_hovers_over_first_product_and_adds_to_cart() {
        log.info("User hovers over first product and adds it to cart");
        product.addProduct(1);
    }

    @When("user clicks Continue Shopping button")
    public void user_clicks_continue_shopping_button() {
        log.info("User clicks Continue Shopping button");
        product.clickContinuebtn();
    }

    @When("user hovers over second product and adds to cart")
    public void user_hovers_over_second_product_and_adds_to_cart() {
        log.info("User hovers over second product and adds it to cart");
        product.addProduct(2);
    }

    @When("user clicks View Cart button")
    public void user_clicks_view_cart_button() {
        log.info("User clicks View Cart button");
        product.clickViewCart();
    }

    @Then("both products should be visible in cart")
    public void both_products_should_be_visible_in_cart() {
        log.info("Verifying both products are visible in the cart");
        Assert.assertEquals(cart.verifyProducts(), true);
    }

    @Then("verify their prices quantity and total price")
    public void verify_their_prices_quantity_and_total_price() {
        log.info("Verifying prices, quantity and total price of products");
        Assert.assertEquals(cart.verifyCartMatchesLocal(), true);
    }
}
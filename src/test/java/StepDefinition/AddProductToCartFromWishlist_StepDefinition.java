package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddProductToCartFromWishlist_StepDefinition extends BaseClass{
    @Given("User is on Homepage")
    public void user_is_on_homepage() {
        setup();
    }
    @When("User search for product")
    public void user_search_for_product() {
        pageFactory.getAddProductToCartFromWishlist().searchProduct();
    }

    @When("User clicks on product and add product to cart")
    public void add_product_to_cart() {
        pageFactory.getAddProductToCartFromWishlist().addProductToCart();
    }

    @Then("Product is added to cart successfully")
    public void add_product_to_cart_verification() throws InterruptedException {
        pageFactory.getAddProductToCartFromWishlist().addProductToCartVerification();
        close();
    }
}
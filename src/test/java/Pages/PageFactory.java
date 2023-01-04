package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private AddProductToCartFromWishlist AddToCart;


    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }
    public AddProductToCartFromWishlist getAddProductToCartFromWishlist() {
        if (AddToCart == null) {
            AddToCart = new AddProductToCartFromWishlist(driver);
        }
        return AddToCart;
    }

}
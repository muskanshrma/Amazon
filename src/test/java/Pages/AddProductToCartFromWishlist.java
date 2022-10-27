package Pages;

import Enums.SearchProduct_Amazon;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static StepDefinition.BaseClass.prop;

public class AddProductToCartFromWishlist {

    WebDriver driver;
    WebDriverWait wait;

    By searchSection = By.xpath("//div//input[@type='text']");
    By searchButton = By.xpath("//input[@type='submit']");
    String product = "(//span[contains(text(),'%s')])[1]";
    By productTitle = By.xpath("//span[contains(@class,'product-title')]");
    By productSpecs = By.xpath("//span[@class='selection']");
    By addToCartButton = By.xpath("//input[@name='submit.add-to-cart']");
    By customerQuestions = By.xpath("//div[@class='a-fixed-left-grid a-spacing-small']");
    By cartButton = By.xpath("//span[contains(@class,'attach-button-large attach-cart-button')]");
    By cartItemVerify = By.xpath("(//span[@class='a-truncate-cut'])[1]");
    By productQuantity = By.xpath("//span[contains(@class,'quantity')]");
    String productQty = "//a[@id='quantity_%s']";
    By orderTotal =  By.xpath("//div[@data-name='Subtotals']");

    public AddProductToCartFromWishlist(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchProduct() {
        driver.findElement(searchSection).sendKeys(prop.getProperty("searchProduct"));
        driver.findElement(searchButton).click();
    }

    public void addProductToCart() {
        driver.findElement(By.xpath(String.format(product, SearchProduct_Amazon.SearchProduct.getResourcesName()))).click();
        String mainWindow = driver.getWindowHandle();
        ArrayList<String> childTabWindow = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(mainWindow);
        System.out.println(childTabWindow);
        driver.switchTo().window(String.valueOf(childTabWindow.get(1)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        String productName = driver.findElement(productTitle).getText();
        System.out.println("Product name: " + productName);
        List<WebElement> allProducts = driver.findElements(productSpecs);
        for (WebElement allElements : allProducts) {
            String productInfo = allElements.getText();
            System.out.println("Product size and color: ");
            System.out.println(productInfo);
        }
        driver.findElement(addToCartButton).click();
    }

    public void addProductToCartVerification() {

        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        driver.findElement(cartButton).click();
        String actual = driver.findElement(cartItemVerify).getText();
        Assert.assertEquals(actual, SearchProduct_Amazon.SearchProduct.getResourcesName());
        driver.findElement(productQuantity).click();
        driver.findElement(By.xpath(String.format(productQty, SearchProduct_Amazon.product_quantity.getResourcesName()))).click();
        String orderQuantity = driver.findElement(orderTotal).getText();
        System.out.println("Order Total amount: " + orderQuantity);
    }
}
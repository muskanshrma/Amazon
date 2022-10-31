package StepDefinition;

import Enums.SearchProduct_Amazon;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class AddProductToCartFromWishlist_StepDefinition extends BaseClass{

    By searchSection = By.xpath("//div//input[@type='text']");
    By searchButton = By.xpath("//input[@type='submit']");
    String product = "(//span[contains(text(),'%s')])[1]";
    By productTitle = By.xpath("//span[contains(@class,'product-title')]");
    By productSizes = By.xpath("//p[contains(@class,'a-text-left ')]");
    By productColors = By.xpath("//div[@id='variation_color_name']//following::span[@class='selection']");
    By addToCartButton = By.xpath("//input[@name='submit.add-to-cart']");
    By productImages = By.xpath("//img[@class='imgSwatch']");
    By customerQuestions = By.xpath("//h2[contains(@class,'askWidgetTitle')]//following::div[@class='a-fixed-left-grid-inner']");
    By sideCartButton = By.xpath("//span[contains(@class,'attach-cart-button')]");
    By goToCartButton =By.xpath("//div[@id='nav-cart-count-container']");
    By cartItemVerify = By.xpath("(//h1//following::span[@class='a-list-item'])[1]");
    By productQuantity = By.xpath("//span[contains(@class,'quantity')]");
    String productQty = "//a[@id='quantity_%s']";
    By orderTotal =  By.xpath("//div[@data-name='Subtotals']");
    By proceedToBuy = By.xpath("//input[@name='proceedToRetailCheckout']");
    By userEmail = By.xpath("//input[@name='email']");
    By continueButton = By.xpath("//input[@type='submit']");
    By userPassword = By.xpath("//input[@name='password']");

    @Given("User is on Homepage")
    public void user_is_on_homepage() {
        setup();
    }

    @When("User search for product")
    public void user_search_for_product() {
        driver.findElement(searchSection).sendKeys(prop.getProperty("searchProduct"));
        driver.findElement(searchButton).click();
    }

    @When("User clicks on product")
    public void go_to_product() {
        driver.findElement(By.xpath(String.format(product, SearchProduct_Amazon.SearchProduct.getResourcesName()))).click();
        String mainWindow = driver.getWindowHandle();
        ArrayList<String> childTabWindow = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(mainWindow);
        System.out.println(childTabWindow);
        driver.switchTo().window(String.valueOf(childTabWindow.get(1)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        String productName = driver.findElement(productTitle).getText();
        System.out.println("Product name: " + productName);
        System.out.println("Product sizes available: ");
        List<WebElement> allProducts = driver.findElements(productSizes);
        for (WebElement allElements : allProducts) {
            String productInfo = allElements.getText();
            System.out.println(productInfo);
        }
        System.out.println("Product colors available: ");
        String productColour = driver.findElement(productColors).getText();
        System.out.println(productColour);
        List<WebElement> allProductsColors = driver.findElements(productColors);
        for (WebElement allElementsColors : allProductsColors) {
            Actions action = new Actions(driver);
            WebElement element = driver.findElement(productImages);
            action.moveToElement(element).perform();
            String productInfoColors = allElementsColors.getText();
            System.out.println(productInfoColors);
        }
        List<WebElement> allCustomerQueries = driver.findElements(customerQuestions);
        for (WebElement allElements : allCustomerQueries) {
            String custQuestions = allElements.getText();
            System.out.println(custQuestions);
        }
    }

    @When("User adds product to cart")
    public void add_product_to_cart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    @Then("Product is added to cart successfully")
    public void add_product_to_cart_verification() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(sideCartButton));
            driver.findElement(sideCartButton).click();
        } catch(NoSuchElementException e){
            wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
            driver.findElement(goToCartButton).click();
        }
        String actual = driver.findElement(cartItemVerify).getText();
        Assert.assertEquals(actual, SearchProduct_Amazon.SearchProduct.getResourcesName());
    }
    @Then("User increases the quantity")
    public void user_increases_quantity() throws InterruptedException {
        driver.findElement(productQuantity).click();
        driver.findElement(By.xpath(String.format(productQty, SearchProduct_Amazon.product_quantity.getResourcesName()))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderTotal));
        String orderQuantity = driver.findElement(orderTotal).getText();
        System.out.println("Order Total amount: " + orderQuantity);
    }

    @Then("User clicks on proceed to buy button")
    public void user_proceeds_to_buy_product(){
        driver.findElement(proceedToBuy).click();
    }

    @Then("User enters account details")
    public void user_enters_account_details(DataTable table){
        List<List<String>> data = Collections.singletonList(table.values());
        driver.findElement(userEmail).sendKeys(data.get(0).get(1));
        driver.findElement(continueButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userPassword));
        driver.findElement(userPassword).sendKeys(data.get(1).get(1));
    }
}
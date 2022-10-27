package StepDefinition;

import Pages.PageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public static Properties prop;
    public static PageFactory pageFactory;
    public static WebDriver driver;

    public void setup() {
        try {
            prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//TestData//resources.properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            pageFactory=new PageFactory(driver);
        } else if (browserName.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            pageFactory=new PageFactory(driver);
        }
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }
    public void close() {
        driver.close();
    }
}
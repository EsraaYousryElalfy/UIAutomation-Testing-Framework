package cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import selenium.driver.BrowserFactory;
import selenium.driver.SeleniumWebDriver;

public class GlobalHooks {
    public static WebDriver driver;

    @Before(order = 0)
    public static void getBrowser() {
        driver = BrowserFactory.openBrowser();
    }

    @After(order = 0)
    public static void stopBrowser(){
        driver.quit();
    }
}

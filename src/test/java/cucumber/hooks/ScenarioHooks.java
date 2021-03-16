package cucumber.hooks;

import Utilities.Helper;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import selenium.pages.HomePage;

public class ScenarioHooks {
    private static WebDriver driver;
    @Before(order = 1)
    public void goToURL() {
        HomePage homePage = new HomePage();
        homePage.openPage();
    }

    @AfterStep
    public void takeScreenshotOnFailure(ITestResult result)
    {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                System.out.println("Failed, And Took Screenshot");
                Helper.CaptureScreenshot(driver,result.getName());
            case ITestResult.SUCCESS:
                System.out.println("Success, And Took Screenshot");
                Helper.CaptureScreenshot(driver,result.getName());
            case ITestResult.SKIP:
                System.out.println("Skipped, And Took Screenshot");
                Helper.CaptureScreenshot(driver,result.getName());
            default:
            System.out.println("Unknown error, And Took Screenshot");
            Helper.CaptureScreenshot(driver, result.getName());
        }
    }
}

package selenium.driver;

import constants.Locator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumWebDriver {

    private static WebDriver driver;
    private static JavascriptExecutor jse;
    private static Select select;
    private static Actions actions;
    private static Alert alert;
    private static WebDriverWait wait;
    private static final Integer NUM_RETRYS = 3;

    public static void openURL(String seleniumURL) {
        driver.get(seleniumURL);
    }
    public static String getURL() {
        return driver.getCurrentUrl();
    }
    public static void scrollDown() {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,1000)");
    }
    public static void scrollToBottom() {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public static void scrollToUp(){
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-1000)");
    }
    public static void scrollToElement(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findElement(by));
    }
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected static void switchToAlert()
    {
        driver.switchTo().alert();
    }
    protected static void acceptAlert() {
        alert.accept();
        //driver.switchTo().alert().accept();
    }
    public static String getTextFromAlert() {
        return driver.switchTo().alert().getText();
        // return alert.getText();
    }
    public static void dismissAlert() {
        alert.dismiss();
        //driver.switchTo().alert().dismiss();
    }

    public static WebElement findElement(By by) {
        return driver.findElement(by);
    }
    public static void clickOn(By by) {
        findElement(by).click();
    }
    public static void clickOn(WebElement element) {
        element.click();
    }
    public static void sendKeys(By by, String text) {
        findElement(by).sendKeys(text);
    }
    public static void sendKeysAndClear(By by, String text) {
        findElement(by).clear();
        findElement(by).sendKeys(text);
    }
    public static void waitVisibilityOfElement(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds /* seconds */);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static void waitInVisibilityOfElement(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds /* seconds */);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    protected static void forceClick(WebElement element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].click();", element);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void forceClick(By by) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                WebElement element = driver.findElement(by);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].click();", element);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    public static void forceClickByID(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('" + element.getAttribute("id") + "').click();");
    }
    public static void forceClickByID(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('" + element.getAttribute("id") + "').click();");
    }
    public static void moveMouseToElement(Locator locator) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(locator.by());
        action.moveToElement(we).build().perform();
    }
    public static boolean switchTab(String tabName) {
        List<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        for (int i = 0; i < tabs2.size(); i++) {
            if (!driver.getTitle().equals(tabName)) {
                driver.switchTo().window(tabs2.get(i));
            }
        }
        return driver.getTitle().equals(tabName);
    }
    public static void selectFromListByValue(By by, String option) {
        Select select = new Select(findElement(by));
        select.selectByVisibleText(option);
    }
    public static String selectRandomlyFromDropList(By by) {
        boolean staleElement = true;
        int numberTries = 0;
        String selectedOption = "";
        do {
            try {
                Random rand = new Random();
                List<WebElement> options = driver.findElements(by);
                int selectOption = rand.nextInt(options.size());
                selectedOption = options.get(selectOption).getText().trim();
                scrollToElement(options.get(selectOption));
                options.get(selectOption).click();
                staleElement = false;
            } catch (StaleElementReferenceException ex) {
                if (numberTries == NUM_RETRYS) {
                    throw ex;
                }
            }
        } while (staleElement);
        return selectedOption;
    }
    protected static void switchWindows() {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
        }
    }
    protected static void actionClick(WebElement element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                actions = new Actions(driver);
                actions.moveToElement(element).click().build().perform();
                pageLoadingWait(driver,30);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }

    }
    protected static void actionClick(By element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                actions = new Actions(driver);
                actions.moveToElement(driver.findElement(element)).click().build().perform();
                pageLoadingWait(driver,30);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }

    }
    protected static void elementClick(WebElement clickElement) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                clickElement.click();
                pageLoadingWait(driver,30);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void elementClick(By element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                driver.findElement(element).click();
                pageLoadingWait(driver,30);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void hoverOnElement(WebElement element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                actions = new Actions(driver);
                actions.moveToElement(element).perform();
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void hoverOnElement(By element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                actions = new Actions(driver);
                actions.moveToElement(driver.findElement(element)).perform();
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void setElement(WebElement element , String textElement) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                clearElement(element);
                element.sendKeys(textElement);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void setElement(By element , String textElement) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                clearElement(element);
                driver.findElement(element).sendKeys(textElement);
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void clearElement(WebElement element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                element.clear();
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void clearElement(By element) {
        Boolean staleElement = true;
        while (staleElement) {
            try {

                driver.findElement(element).clear();
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void actionSetElement(WebElement element, String text) {
        actions = new Actions(driver);
        clearElement(element);
        actions.moveToElement(element).sendKeys(text).build().perform();
    }
    protected static void actionSetElement(By element, String text) {
        actions = new Actions(driver);
        clearElement(element);
        actions.moveToElement(driver.findElement(element)).sendKeys(text).build().perform();
    }
    protected static void selectEqualFromList(List<WebElement> list, String option) {
        list.forEach(element -> {
            if (element.getText().equals(option)) {
                forceClick(element);
                pageLoadingWait(driver,30);
            }
        });
    }
    // select from list by while condition
    protected static void selectContainsFromList(List<WebElement> list, String option) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                list.forEach(element -> {
                    if (element.getText().contains(option)) {
                        forceClick(element);
                        pageLoadingWait(driver,30);                    }
                });
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    // select from list by for loop
    protected static void selectContainsFromList2(List<WebElement> list, String option) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                for (WebElement element : list) {
                    if (element.getText().equals(option)) {
                        forceClick(element);
                        pageLoadingWait(driver,30);
                        break;
                    }
                }
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void elementIsVisibleWait(WebElement element, int timeOuts) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                wait = new WebDriverWait(driver, timeOuts);
                wait.until(ExpectedConditions.visibilityOf(element));
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void elementIsVisibleWait(By element, int timeOuts) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                wait = new WebDriverWait(driver, timeOuts);
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void elementsAreVisibleWait(List<WebElement> list, int timeOuts) {
        wait = new WebDriverWait(driver, timeOuts);
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }
    protected static void waitUntilPageLoaded() {
        wait.until((ExpectedCondition<Boolean>)
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    protected static void pageLoadingWait(WebDriver driver, int timeOuts) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, timeOuts);
        wait.until(pageLoadCondition);
    }
    protected static void elementIsClickableWait(WebElement element, int timeOuts) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                wait = new WebDriverWait(driver, timeOuts);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void elementIsClickableWait(By element, int timeOuts) {
        Boolean staleElement = true;
        while (staleElement) {
            try {
                wait = new WebDriverWait(driver, timeOuts);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }
    protected static void implicitWait(int timeOuts) {
        driver.manage().timeouts().implicitlyWait(timeOuts, TimeUnit.SECONDS);
    }
    protected static boolean elementIsSelected(WebElement element) {
        try {
            element.isSelected();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    protected static boolean elementIsSelected(By element) {
        try {
            driver.findElement(element).isSelected();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    protected static boolean elementIsDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    protected static boolean elementIsDisplayed(By element) {
        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    protected static boolean isAlertPresent() {
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException Ex)
        {
            return false;
        }
    }

        /*public static void waitUntilElementIsPresent(WebDriver driver, final By by, int timeOuts) {
        try{
            wait = new WebDriverWait(driver, timeOuts);
            wait.ignoring(java.util.NoSuchElementException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}

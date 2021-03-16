package selenium.locatores;

import constants.Locator;
import org.openqa.selenium.By;

public enum PageLocators implements Locator {
    //HOME_ICON (By.xpath("")),
    ;

    private By locator;


    PageLocators(By locator) {
        this.locator = locator;
    }

    @Override
    public By by() {
        return locator;
    }
}

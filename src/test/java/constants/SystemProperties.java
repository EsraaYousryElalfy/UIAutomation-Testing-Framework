package constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {
    private static final Properties SYSTEM_PROPS = loadSystemProperties();

    private static Properties loadSystemProperties() {
        Properties systemProps = new Properties();
        try {
            InputStream appConfigIn = SystemProperties.class.getClassLoader().getResourceAsStream("system.properties");
            if (appConfigIn == null) {
                throw new IllegalArgumentException("system.properties not found");
            }
            systemProps.load(appConfigIn);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return systemProps;
    }

    public static String getSeleniumBrowser() {
        String seleniumBrowser = SYSTEM_PROPS.getProperty("selenium.browser");
        if (seleniumBrowser == null) {
            throw new IllegalArgumentException("selenium.browser configuration not found in system.properties");
        } else {
            return seleniumBrowser;
        }
    }

    public static String getSeleniumURL() {
        String seleniumBrowser = SYSTEM_PROPS.getProperty("host.url");
        if (seleniumBrowser == null) {
            throw new IllegalArgumentException("selenium.url configuration not found in system.properties");
        } else {
            return seleniumBrowser;
        }
    }

    public static String getRestAssuredUrl() {
        String seleniumBrowser = SYSTEM_PROPS.getProperty("apiRestAssured.url");
        if (seleniumBrowser == null) {
            throw new IllegalArgumentException("restassured.url configuration not found in system.properties");
        } else {
            return seleniumBrowser;
        }
    }
}


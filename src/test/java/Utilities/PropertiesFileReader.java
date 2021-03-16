package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileReader {

    public Properties getProperty()
    {
        FileInputStream inputStream = null;
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream("Resources/browser-config.properties"));
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
        return properties;
    }
}

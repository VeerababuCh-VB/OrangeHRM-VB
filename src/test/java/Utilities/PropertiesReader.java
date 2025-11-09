package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static Properties prop;

    // Load properties from the config file
    public static void loadProperties() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/Properties/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get property value by key
    public static String get(String key) {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(key);
    }
}
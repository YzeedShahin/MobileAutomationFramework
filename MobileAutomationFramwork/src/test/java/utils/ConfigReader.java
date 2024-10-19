package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties = loadProperties();

    private Properties loadProperties() {
        Properties prop = new Properties();
        String configPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" +File.separator + "config.properties";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(configPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}

package base;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;
import utils.TestDataReader;



public class BaseTest {

    //public ConfigReader configReader = new ConfigReader();
    public TestDataReader testData = new TestDataReader();
    public static AppiumDriver driver;


    @BeforeMethod
    public void setup() {
        driver = DriverManager.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    public String getData(String key) {
        return testData.getValue(key);
    }

}

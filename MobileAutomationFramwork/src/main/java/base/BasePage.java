package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;



public class BasePage {
    protected static AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

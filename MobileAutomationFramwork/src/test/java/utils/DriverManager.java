package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class DriverManager {

    private static final ConfigReader configReader = new ConfigReader();
    private static AppiumDriver driver;

    public static AppiumDriver initDriver() {
        String platform = configReader.getProperty("platform").toLowerCase().trim();
        // configurations for iOS will be added once ready
        return switch (platform) {
            case "android" -> initAndroidDriver();
            case "ios" -> throw new RuntimeException("iOS is not supported yet, please use Android for now.");
            default -> {
                System.out.println("Unsupported platform: " + platform + ". Defaulting to Android.");
                yield initAndroidDriver();
            }
        };
    }

    private static AppiumDriver initAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(configReader.getProperty("platform"));
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName(configReader.getProperty("deviceName"));
        options.setApp(System.getProperty("user.dir") + File.separator + "apps"
                + File.separator + configReader.getProperty("app"));
        try {
            driver = new AndroidDriver(new URI(configReader.getProperty("appiumServer")).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException("Error initializing Android driver", e);
        }
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(configReader.getProperty("wait"))));
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

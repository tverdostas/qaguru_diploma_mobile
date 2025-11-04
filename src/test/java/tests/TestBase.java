package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.AllowWindow;
import pageobjects.SignInPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    protected AndroidDriver driver;
    protected SignInPage signInPage;

/*    @BeforeAll
    static void beforeAll(){
        Configuration.browser = CustomMobileDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }*/

    @BeforeEach
    void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:appPackage", "com.bitrix24.android");
        caps.setCapability("appium:appActivity", "com.bitrix24.android.BX24Activity");
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
            signInPage = new SignInPage(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(5000); // подождать 5 секунд перед закрытием
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}

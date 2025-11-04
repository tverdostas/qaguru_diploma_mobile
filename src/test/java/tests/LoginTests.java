package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.AllowWindow;
import pageobjects.SignInPage;

import java.net.URL;
import java.time.Duration;

public class LoginTests extends TestBase {

    private SignInPage SignInPage;
    // private AllowWindow allowWindow;

/*    @BeforeEach
    void setUp() {
        super.setUp(); // ← обязательно!
        // ваш дополнительный код
    }*/

    private final String PORTAL_ADDRESS = System.getProperty("portalName");
    private final String EMAIL = System.getProperty("userLogin");
    private final String PASSWORD = System.getProperty("userPassword");

/*    @BeforeEach
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setApp("/path/to/your/app.apk")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        SignInPage = new SignInPage(driver);
    }*/

    @Test
    public void successfulLogin(){
        System.out.println("Driver in test: " + driver);
        signInPage.enterAddressButtonDisplayed();
        signInPage.tapToAddressButton();
        signInPage.fillPortalAddress(PORTAL_ADDRESS);
        signInPage.pressContinueButton();
        signInPage.fillEmail(EMAIL);
        signInPage.pressContinueButton();
        signInPage.fillEmail(PASSWORD);
        signInPage.pressContinueButton();

/*        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("com.bitrix24.android:id/some_id")));

*/
        new AllowWindow(driver).waitForAllowWindow();
        new AllowWindow(driver).tapToAllowButton();

        signInPage.waitForCrmHeader();
        signInPage.crmHeaderIsDisplayed();
    }
}

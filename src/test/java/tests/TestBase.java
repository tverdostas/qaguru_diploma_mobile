package tests;

import com.codeborne.selenide.WebDriverRunner;
import config.MobileConfig;
import helpers.Attach;
import helpers.MobileWebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pageobjects.SignInPage;

import static helpers.Attach.getSessionId;
import static io.restassured.RestAssured.sessionId;

public class TestBase {

    protected AndroidDriver driver;
    protected SignInPage signInPage;
    String sessionId = getSessionId();

    private static final MobileConfig config = ConfigFactory.create(
            MobileConfig.class,
            System.getProperties(),
            System.getenv()
    );

   @BeforeEach
    void setUp() {
        // Создаём драйвер через провайдер (или вручную через UiAutomator2Options)
        try {
            MobileWebDriverProvider provider = new MobileWebDriverProvider();
            driver = (AndroidDriver) provider.get();
            if (driver == null) {
                throw new IllegalStateException("Driver is null!");
            }
            WebDriverRunner.setWebDriver(driver);
        } catch (Exception e) {
            e.printStackTrace(); // <-- Убедитесь, что вы видите полную ошибку
            throw new RuntimeException("Driver setup failed", e);
        }
    }
    @AfterEach
    void tearDown() {
        
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.attachVideo(sessionId);
        
        try {
            Thread.sleep(5); // подождать 5 секунд перед закрытием
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver != null) {
            WebDriverRunner.closeWebDriver();
            driver.quit();
        }
    }
}

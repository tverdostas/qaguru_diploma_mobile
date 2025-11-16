package helpers;

import com.codeborne.selenide.Selenide;
import config.MobileConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.BrowserstackHelper.getBrowserstackVideoUrl;

public class Attach {

    private static final MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    private static final String videoStorageUrl = config.videoStorageUrl();

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        WebDriver driver = getWebDriver();
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    /**
     * Собирает логи консоли браузера ТОЛЬКО если текущий контекст — WEBVIEW.
     * В нативном контексте (NATIVE_APP) логи не запрашиваются.
     */
    public static void browserConsoleLogs() {
        WebDriver driver = getWebDriver();

        if (driver instanceof AppiumDriver) {
            String currentContext = ((AndroidDriver) driver).getContext();
            if (currentContext != null && currentContext.toLowerCase().contains("webview")) {
                try {
                    String logs = String.join("\n", Selenide.getWebDriverLogs("browser"));
                    attachAsText("Browser console logs", logs);
                } catch (Exception e) {
                    attachAsText("Browser console logs", "Failed to retrieve browser logs: " + e.getMessage());
                }
            } else {
                attachAsText("Browser console logs", "Skipped: current context is " + currentContext);
            }
        } else {
            // Для обычных веб-тестов (не Appium)
            try {
                String logs = String.join("\n", Selenide.getWebDriverLogs("browser"));
                attachAsText("Browser console logs", logs);
            } catch (Exception e) {
                attachAsText("Browser console logs", "Not available: " + e.getMessage());
            }
        }
    }

    /**
     * Возвращает HTML-вложение с видео из BrowserStack.
     * Работает только если sessionId соответствует формату UUID (как у BrowserStack).
     */
    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }

    public static String getVideoUrl(String sessionId) {
            return getBrowserstackVideoUrl(sessionId);
    }

/*    public static String getWebVideoUrl(String sessionId) {
        try {
            return new URL(videoStorageUrl + "/" + sessionId + ".mp4") + "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
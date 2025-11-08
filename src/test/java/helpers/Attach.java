package helpers;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {

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
    public static String addVideo() {
        String sessionId = Selenide.sessionId() != null ? Selenide.sessionId().toString() : null;
        if (sessionId != null && sessionId.matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")) {
            String videoUrl = "https://app-automate.browserstack.com/s3-upload/" + sessionId + "/video.mp4";
            return "<html><body><video width='100%' height='100%' controls autoplay>" +
                    "<source src='" + videoUrl + "' type='video/mp4'>" +
                    "</video></body></html>";
        } else {
            return "<html><body>Video not available (not a BrowserStack session)</body></html>";
        }
    }
}
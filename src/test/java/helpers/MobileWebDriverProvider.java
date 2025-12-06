package helpers;

import config.MobileConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.function.Supplier;

public class MobileWebDriverProvider implements Supplier<AppiumDriver> {

    private final MobileConfig config;

    public MobileWebDriverProvider() {
        this.config = ConfigFactory.create(MobileConfig.class, System.getProperties());
        System.out.println("Driver URL: " + config.driverUrl().trim());
        System.out.println("Device: " + config.deviceName());
    }

    @Override
    public AppiumDriver get() {
        AppiumDriver driver = createDriver();
        // Опционально: задержка для стабильности запуска
        new WebDriverWait(driver, Duration.ofSeconds(10));
        return driver;
    }

    private AppiumDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(config.deviceName())
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity())
                .setNoReset(config.noReset())
                .setAppWaitForLaunch(false)
                .setAppWaitDuration(Duration.ofSeconds(60));


        // Для BrowserStack — добавляем через setCapability (т.к. это кастомные capabilities)
        if (config.driverUrl().contains("browserstack")) {
            options.setCapability("build", config.browserstackBuildName());
            options.setCapability("app", config.browserstackApp());
            options.setCapability("deviceName", config.deviceName());
            options.setCapability("setAppWaitForLaunch", config.setAppWaitForLaunch());
        }

        // Логирование всех capabilities перед созданием драйвера
        System.out.println("Настройки capabilities:");
        options.asMap().forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });

        try {

            // Теперь используем config для получения значений
            String username = config.browserstackUser();
            String accessKey = config.browserstackKey();
            //String driverUrl = config.driverUrl();


            // Кодируем ключ (на случай, если в нём есть спецсимволы вроде '@' или '&')
            String encodedKey = URLEncoder.encode(accessKey, StandardCharsets.UTF_8);

            // Формируем URL в правильном формате
            String driverUrl = "https://" + username + ":" + encodedKey + "@hub-cloud.browserstack.com/wd/hub";

            // Для отладки — покажи итоговый URL (без ключа)
            System.out.println("Connecting to BrowserStack as user: " + driverUrl);
            System.out.println("Driver URL: https://hub-cloud.browserstack.com/wd/hub");

            // Создаём драйвер
            return new AndroidDriver(new URL(driverUrl), options);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid Appium URL or credentials!", e);
        }
    }
}
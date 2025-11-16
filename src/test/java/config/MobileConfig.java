package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
        "classpath:${config}.properties"
})
public interface MobileConfig extends Config {

    @Key("platform.version")
    @DefaultValue("13.0")
    String platformVersion();

    @Key("user.login")
    String userLogin();

    @Key("user.password")
    String userPassword();

    @Key("portal.name")
    String portalName();

    @Key("platform.name")
    @DefaultValue("Android")
    String platformName();

    @Key("automation.name")
    @DefaultValue("UiAutomator2")
    String automationName();

    @Key("device.name")
        // @DefaultValue("emulator-5556")
    String deviceName();

    @Key("app.package")
    @DefaultValue("com.bitrix24.android")
    String appPackage();

    @Key("app.activity")
    @DefaultValue("com.bitrix24.android.BX24Activity")
    String appActivity();

    // Для BrowserStack
    @Key("browserstack.user")
    String browserstackUser();

    @Key("browserstack.key")
    String browserstackKey();

    @Key("browserstack.build.name")
    @DefaultValue("Bitrix24 Mobile Tests")
    String browserstackBuildName();

    @Key("browserstack.app")
    @DefaultValue("bs://490fd51699f846b0736c908983f9749f5f9cc30d")
    String browserstackApp();

    // URL драйвера (Appium hub или BrowserStack endpoint)
    @Key("driver.url")
    @DefaultValue("https://hub-cloud.browserstack.com/wd/hub")
    String driverUrl();

    @Key("no.reset")
    @DefaultValue("true")
    boolean noReset();

    @Key("set.app.wait.for.launch")
    @DefaultValue("true")
    boolean setAppWaitForLaunch();

    @Key("video.storage.url")
    @DefaultValue("https://selenoid.autotests.cloud/video/")
    String videoStorageUrl();
}

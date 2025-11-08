package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
        "classpath:${config}.properties"
})
public interface MobileConfig extends Config {

    @Key("platformVersion")
    @DefaultValue("13.0")
    String platformVersion();

    @Key("platformName")
    @DefaultValue("Android")
    String platformName();

    @Key("automationName")
    @DefaultValue("UiAutomator2")
    String automationName();

    @Key("deviceName")
    // @DefaultValue("emulator-5556")
    String deviceName();

    @Key("appPackage")
    @DefaultValue("com.bitrix24.android")
    String appPackage();

    @Key("appActivity")
    @DefaultValue("com.bitrix24.android.BX24Activity")
    String appActivity();

    // Для BrowserStack
    @Key("browserstack.user")
    String browserstackUser();

    @Key("browserstack.key")
    String browserstackKey();

    @Key("browserstack.buildName")
    @DefaultValue("Bitrix24 Mobile Tests")
    String browserstackBuildName();

    @Key("browserstack.app")
    @DefaultValue("bs://490fd51699f846b0736c908983f9749f5f9cc30d")
    String browserstackApp();

    // URL драйвера (Appium hub или BrowserStack endpoint)
    @Key("driverUrl")
    @DefaultValue("https://hub-cloud.browserstack.com/wd/hub")
    String driverUrl();

    @Key("noReset")
    @DefaultValue("true")
    boolean noReset();

    @Key("setAppWaitForLaunch")
    @DefaultValue("true")
    boolean setAppWaitForLaunch();
}

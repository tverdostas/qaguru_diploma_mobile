package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllowWindow extends BasePage {
    private final By ALLOW_BUTTON = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button");
    private final By ALLOW_WINDOW = AppiumBy.id("com.android.permissioncontroller:id/grant_dialog");

    public AllowWindow(AndroidDriver driver) {
        super(driver);
    }

    @Step("Разрешить уведомления в системном окне \"Allow Bitrix24 to send you notifications?\"")
    public void allowNotifications() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ALLOW_WINDOW));
        driver.findElement(ALLOW_BUTTON).click();
    }
}

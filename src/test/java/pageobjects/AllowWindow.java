package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AllowWindow extends BasePage {
    private final String ALLOW_BUTTON = "com.android.permissioncontroller:id/permission_allow_button";
    private final String ALLOW_WINDOW = "com.android.permissioncontroller:id/grant_dialog";

    public AllowWindow(AndroidDriver driver) {
        super(driver);
        System.out.println("Driver in AllowWindow: " + driver);        // должен быть не null
        System.out.println("Inherited driver: " + this.driver);        // должен быть не null
    }

    @Step
    public void tapToAllowButton() {
        driver.findElement(AppiumBy.id(ALLOW_BUTTON)).click();
    }

    @Step
    public void waitForAllowWindow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        AppiumBy.id(ALLOW_WINDOW)));
    }
}

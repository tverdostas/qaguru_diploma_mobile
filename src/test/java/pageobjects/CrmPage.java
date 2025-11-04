package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CrmPage extends BasePage {
    public CrmPage(AndroidDriver driver) {
        super(driver);
    }

    private final String CRM_HEADER = "new UiSelector().text(\"CRM \")";

    public void crmHeaderIsDisplayed() {
        driver.findElement(AppiumBy.androidUIAutomator(CRM_HEADER)).isDisplayed();
    }
    @Step
    public void waitForCrmHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator(CRM_HEADER)
        ));
    }
}

package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CrmPage extends BasePage {
    public CrmPage(AndroidDriver driver) {
        super(driver);
    }

    private final By CRM_HEADER = AppiumBy.androidUIAutomator("new UiSelector().text(\"CRM \")");

    @Step("Убедиться, что заголовок страницы \"CRM\" отображается")
    public void verifyCrmHeaderIsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CRM_HEADER));
    }
}

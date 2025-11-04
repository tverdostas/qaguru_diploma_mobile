package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Локаторы (лучше выносить в отдельные константы или использовать AppiumBy.ByAndroidUIAutomator)
    private final String CREATE_FOR_FREE_BUTTON = "com.bitrix24.android:id/btnCreate";
    private final String SIGN_IN_BUTTON = "com.bitrix24.android:id/btnEnter";
    private final String ENTER_ADDRESS_BUTTON = "com.bitrix24.android:id/btnAddPortal";
    private final String ENTER_ADDRESS_FIELD = "com.bitrix24.android:id/input";
    private final String ENTER_EMAIL_FIELD = "com.bitrix24.android:id/input";
    private final String CRM_HEADER = "new UiSelector().text(\"CRM \")";
    private final String CONTINUE_BUTTON = "com.bitrix24.android:id/btnNext";


    public SignInPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
/*        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));*/
    }

    // Методы для взаимодействия

    @Step
    public boolean enterAddressButtonDisplayed() {
        return driver.findElement(AppiumBy.id(ENTER_ADDRESS_BUTTON)).isDisplayed();
    }
    @Step
    public void tapToAddressButton() {
        driver.findElement(AppiumBy.id(ENTER_ADDRESS_BUTTON)).click();
    }

    public void fillPortalAddress(String PORTAL_ADDRESS) {
        fillInput(By.id(ENTER_ADDRESS_FIELD), PORTAL_ADDRESS);
    }

    @Step
    public void pressContinueButton() {
        driver.findElement(AppiumBy.id(CONTINUE_BUTTON)).click();
    }

    public void fillEmail(String EMAIL) {
        fillInput(By.id(ENTER_EMAIL_FIELD), EMAIL);
    }

    public void crmHeaderIsDisplayed() {
        driver.findElement(AppiumBy.androidUIAutomator(CRM_HEADER)).isDisplayed();
    }
    @Step
    public void waitForCrmHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator(CRM_HEADER)
        ));
    }

/*    public void clickGetStarted() {
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id(GET_STARTED_BUTTON_ID))
        );
        button.click();
    }*/
}

package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Локаторы (лучше выносить в отдельные константы или использовать AppiumBy.ByAndroidUIAutomator)
    public final By CREATE_FOR_FREE_BUTTON = AppiumBy.id("com.bitrix24.android:id/btnCreate");
    private final String SIGN_IN_BUTTON = "com.bitrix24.android:id/btnEnter";
    public By ENTER_ADDRESS_BUTTON = AppiumBy.id("com.bitrix24.android:id/btnAddPortal");
    private final String ENTER_ADDRESS_FIELD = "com.bitrix24.android:id/input";
    private final String ENTER_EMAIL_FIELD = "com.bitrix24.android:id/input";
    private final String CONTINUE_BUTTON = "com.bitrix24.android:id/btnNext";
    private final String CANNOT_SIGN_IN = "com.bitrix24.android:id/btnHelp";
    public final By MAIN_LOGO = AppiumBy.id("com.bitrix24.android:id/logo");
    private final String PAGE_TITLE_SIGN_IN_USING_ADDRESS = "com.bitrix24.android:id/title";
    private final String MESSAGE = "android:id/message";
    public final By HELP_BUTTON = AppiumBy.androidUIAutomator("text(\"Help\")");
    public final By CLOSE_BUTTON = AppiumBy.androidUIAutomator("text(\"Close\")");
    public final By HELP_HEADER = AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign in using address\").instance(1)");

    public SignInPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Методы для взаимодействия

    @Step
    @DisplayName("Кнопка \"Enter address\" отображается на экране")
    public boolean enterAddressButtonDisplayed() {
        return driver.findElement(ENTER_ADDRESS_BUTTON).isDisplayed();
    }

    @Step
    @DisplayName("Элемент отображается на экране")
    public boolean theElementIsDisplayed() {
        return driver.findElement(ENTER_ADDRESS_BUTTON).isDisplayed();
    }


/*    @Step
    @DisplayName("Тап по кнопке \"Enter address\"")
    public void tapToAddressButton() {
        driver.findElement(AppiumBy.id(ENTER_ADDRESS_BUTTON)).click();
    }*/
    @Step
    @DisplayName("Заполнить адрес портала Б24")
    public void fillPortalAddress(String PORTAL_ADDRESS) {
        fillInput(By.id(ENTER_ADDRESS_FIELD), PORTAL_ADDRESS);
    }

    @Step
    @DisplayName("Тап по кнопке \"Continue\"")
    public void pressContinueButton() {
        driver.findElement(AppiumBy.id(CONTINUE_BUTTON)).click();
    }

    @Step
    @DisplayName("Ввести email")
    public void fillEmail(String EMAIL) {
        fillInput(By.id(ENTER_EMAIL_FIELD), EMAIL);
    }

    @Step
    @DisplayName("Заголовок текущей страницы {0}")
    public boolean checkPageHeader(String expectedText) {
        try {
            By titleLocator = AppiumBy.id(PAGE_TITLE_SIGN_IN_USING_ADDRESS);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(titleLocator, expectedText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step
    @DisplayName("Отображено сообщение {0}")
    public boolean checkPageMessage(String expectedText) {
        try {
            By titleLocator = AppiumBy.id(MESSAGE);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(titleLocator, expectedText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

/*    public void clickGetStarted() {
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id(GET_STARTED_BUTTON_ID))
        );
        button.click();
    }*/
}

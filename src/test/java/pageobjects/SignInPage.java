package pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By CREATE_FOR_FREE_BUTTON = AppiumBy.id("com.bitrix24.android:id/btnCreate");
    private final By SIGN_IN_BUTTON = AppiumBy.id("com.bitrix24.android:id/btnEnter");
    private final By ENTER_ADDRESS_BUTTON = AppiumBy.id("com.bitrix24.android:id/btnAddPortal");
    private final By ENTER_ADDRESS_FIELD = AppiumBy.id("com.bitrix24.android:id/input");
    private final By ENTER_EMAIL_FIELD = AppiumBy.id("com.bitrix24.android:id/input");
    private final By ENTER_PASSWORD_FIELD = AppiumBy.id("com.bitrix24.android:id/input");
    private final By CONTINUE_BUTTON = AppiumBy.id("com.bitrix24.android:id/btnNext");
    private final By CANNOT_SIGN_IN = AppiumBy.id("com.bitrix24.android:id/btnHelp");
    private final By MAIN_LOGO = AppiumBy.id("com.bitrix24.android:id/logo");
    private final By PAGE_TITLE = AppiumBy.id("com.bitrix24.android:id/title");
    private final By MESSAGE = AppiumBy.id("android:id/message");
    private final By HELP_BUTTON = AppiumBy.androidUIAutomator("text(\"Help\")");
    private final By CLOSE_BUTTON = AppiumBy.androidUIAutomator("text(\"Close\")");
    private final By HELP_HEADER = AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign in using address\").instance(1)");
    private final By HELP_TEXT_IN_BORDER = AppiumBy.androidUIAutomator("new UiSelector().text(\"You can enter Bitrix24 address either with https:// or without it. If you don't know your Bitrix24 address, ask your Bitrix24 administrator or colleagues.\")");

    public SignInPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Кнопка \"Enter address\" отображается на экране")
    public boolean enterAddressButtonDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(ENTER_ADDRESS_BUTTON));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Заполнить адрес портала Б24")
    public void fillPortalAddress(String PORTAL_ADDRESS) {
        fillInput(ENTER_ADDRESS_FIELD, PORTAL_ADDRESS);
    }

    @Step("Тап по кнопке \"Continue\"")
    public void pressContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Тап по кнопке \"Enter address\"")
    public void pressButtonEnterAddress() {
        driver.findElement(ENTER_ADDRESS_BUTTON).click();
    }

    @Step("Тап по кнопке \"Help\"")
    public void pressHelpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(HELP_BUTTON));
        driver.findElement(HELP_BUTTON).click();
    }

    @Step("Тап по кнопке \"Close\"")
    public void pressCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(CLOSE_BUTTON));
        driver.findElement(CLOSE_BUTTON).click();
    }

    @Step("Текст с подсказкой в бордере отображается")
    public void textHelpInBorder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(HELP_TEXT_IN_BORDER));
    }

    @Step("Кнопка \"Create for free\" отображена")
    public void createForFreeButtonIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_FOR_FREE_BUTTON));
    }

    @Step("Ввести email")
    public void fillEmail(String EMAIL) {
        fillInput(ENTER_EMAIL_FIELD, EMAIL);
    }

    @Step("Ввести password")
    public void fillCorrectPassword(String PASSWORD) {
        fillInput(ENTER_PASSWORD_FIELD, PASSWORD);
    }

    @Step("Проверка: заголовок текущей страницы {expectedText}")
    public boolean checkPageHeader(String expectedText) {
        try {
            By titleLocator = PAGE_TITLE;
            wait.until(ExpectedConditions.textToBePresentInElementLocated(titleLocator, expectedText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Отображено сообщение {0}")
    public boolean checkPageMessage(String expectedText) {
        try {
            By titleLocator = MESSAGE;
            wait.until(ExpectedConditions.textToBePresentInElementLocated(titleLocator, expectedText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Главный лого отображен на странице")
    public void mainLogoIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_LOGO));
    }
}

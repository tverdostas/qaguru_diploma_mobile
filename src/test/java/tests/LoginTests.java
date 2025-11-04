package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pageobjects.AllowWindow;
import pageobjects.CrmPage;
import pageobjects.SignInPage;

public class LoginTests extends TestBase {

    private SignInPage SignInPage;

    private final String PORTAL_ADDRESS = System.getProperty("portalName");
    private final String EMAIL = System.getProperty("userLogin");
    private final String PASSWORD = System.getProperty("userPassword");

    @Test
    @DisplayName("Успешный логин")
    public void successfulLogin(){

        System.out.println("Driver in test: " + driver);

        signInPage.enterAddressButtonDisplayed();
        signInPage.clickElement(signInPage.ENTER_ADDRESS_BUTTON, "Enter address");
        signInPage.checkPageHeader("Sign in using address");
        signInPage.fillPortalAddress(PORTAL_ADDRESS);
        signInPage.pressContinueButton();
        signInPage.checkPageHeader("Sign in to");
        signInPage.fillEmail(EMAIL);
        signInPage.pressContinueButton();
        signInPage.checkPageHeader("Enter password");
        signInPage.fillEmail(PASSWORD);
        signInPage.pressContinueButton();

        new AllowWindow(driver).waitForAllowWindow();
        new AllowWindow(driver).tapToAllowButton();

        new CrmPage(driver).waitForCrmHeader();
        new CrmPage(driver).crmHeaderIsDisplayed();

    }

    @Test
    @DisplayName("Корректное сообщение отображено при вводе несуществующего портала Б24")
    public void NonExistentPortalReturnError(){
        signInPage.enterAddressButtonDisplayed();
        signInPage.clickElement(signInPage.ENTER_ADDRESS_BUTTON, "Enter address");
        signInPage.checkPageHeader("Sign in using address");
        signInPage.fillPortalAddress("qwe123.bitrix24.ru");
        signInPage.pressContinueButton();

        signInPage.checkPageMessage("Unknown server response. Please try again later.");
    }

    @Test
    @DisplayName("Кнопка Help открывает страницу с помощью")
    public void HelpButtonIsDisplayedAndWorking(){
        signInPage.enterAddressButtonDisplayed();
        signInPage.clickElement(signInPage.ENTER_ADDRESS_BUTTON, "Enter address");
        signInPage.checkPageHeader("Sign in using address");
        signInPage.theElementIsDisplayed(signInPage.HELP_BUTTON, "Кнопка Help");

        signInPage.clickElement(signInPage.HELP_BUTTON, "Кнопка Help");
        signInPage.theElementIsDisplayed(signInPage.HELP_HEADER, "Заголовок страницы Help");
    }

    @Test
    @DisplayName("Кнопка Close отображается и закрывает окно логина по клику")
    public void CloseButtonIsDisplayedAndWorking(){
        signInPage.enterAddressButtonDisplayed();
        signInPage.clickElement(signInPage.ENTER_ADDRESS_BUTTON, "Enter address");
        signInPage.checkPageHeader("Sign in using address");
        signInPage.theElementIsDisplayed(signInPage.HELP_BUTTON, "Кнопка Help");

        signInPage.clickElement(signInPage.CLOSE_BUTTON, "Кнопка Close");
        signInPage.theElementIsDisplayed(signInPage.CREATE_FOR_FREE_BUTTON, "Кнопка Create for Free");
    }
}

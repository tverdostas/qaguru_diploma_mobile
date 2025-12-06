package tests;

import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobjects.AllowWindow;
import pageobjects.CrmPage;
import pageobjects.SignInPage;

public class LoginTests extends TestBase {

    private static final MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    private final String PORTAL_ADDRESS = config.portalName();
    private final String EMAIL = config.userLogin();
    private final String PASSWORD = config.userPassword();

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        signInPage = new SignInPage(driver);
    }

    @Test
    @DisplayName("Успешный логин")
    public void successfulLogin(){

        System.out.println("Driver in test: " + driver);

        // Логирование значений для отладки
        System.out.println("Portal: " + PORTAL_ADDRESS);
        System.out.println("Email: " + EMAIL);

        signInPage.enterAddressButtonDisplayed();
        signInPage.pressButtonEnterAddress();
        signInPage.checkPageHeader("Sign in using address");
        signInPage.fillPortalAddress(PORTAL_ADDRESS);
        signInPage.pressContinueButton();
        signInPage.checkPageHeader("Sign in to");
        signInPage.fillEmail(EMAIL);
        signInPage.pressContinueButton();
        signInPage.checkPageHeader("Enter password");
        signInPage.fillCorrectPassword(PASSWORD);
        signInPage.pressContinueButton();

        new AllowWindow(driver).allowNotifications();
        new CrmPage(driver).verifyCrmHeaderIsVisible();
    }

    @Test
    @DisplayName("Корректное сообщение отображено при вводе несуществующего портала Б24")
    public void NonExistentPortalReturnError(){
        signInPage.enterAddressButtonDisplayed();
        signInPage.pressButtonEnterAddress();
        signInPage.checkPageHeader("Sign in using address");
        signInPage.fillPortalAddress("qwe123.bitrix24.ru");
        signInPage.pressContinueButton();

        signInPage.checkPageMessage("Unknown server response. Please try again later.");
    }

    @Test
    @DisplayName("Кнопка Help открывает страницу с помощью")
    public void HelpButtonIsDisplayedAndWorking(){
        signInPage.enterAddressButtonDisplayed();
        signInPage.pressButtonEnterAddress();
        signInPage.checkPageHeader("Sign in using address");
        signInPage.pressHelpButton();

        signInPage.textHelpInBorder();
    }

    @Test
    @DisplayName("Кнопка Close отображается и закрывает окно логина по клику")
    public void CloseButtonIsDisplayedAndWorking(){
        signInPage.enterAddressButtonDisplayed();
        signInPage.pressButtonEnterAddress();
        signInPage.checkPageHeader("Sign in using address");
        signInPage.pressHelpButton();

        signInPage.pressCloseButton();
        signInPage.createForFreeButtonIsDisplayed();
    }

    @Test
    @DisplayName("Лого отображается на главной странице")
    public void LogoIsDisplayed() {
        signInPage.mainLogoIsDisplayed();
    }
}
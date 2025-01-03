package saucedemo;

import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import utilities.BaseTests;
import utilities.Gestures;
import utilities.Logs;

public class LoginTests extends BaseTests {
    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        loginPage.waitPageToLoad();
    }

    @Test(groups = {regression, smoke})
    public void invalidCredentialsTest() {
        loginPage.fillForm("locked_out_user", "secret_sauce");
        loginPage.verifyErrorMessage("Sorry, this user has been locked out.");
    }

    @Test(groups = {regression, smoke})
    public void verifyLoginPageTest() {
        loginPage.verfyPage();
    }

    @Test(groups = {regression, smoke})
    public void loginTabTest() {
        Logs.info("Hacer tab en el label de standard_user");
        Gestures.tap(driver.findElement(AppiumBy.androidUIAutomator("text(\"standard_user\")")));
        Logs.info("Hacer tab en el boton de login");
        Gestures.tap(driver.findElement(AppiumBy.accessibilityId("test-LOGIN")));

        //Logs.info("Esperar que cargue la pagina de shopping");
        //sleep(1500);

        Logs.info("Verificar que estamos en la pagina de shopping");
        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("test-PRODUCTS")).isDisplayed());
    }

    @Test(groups = {regression})
    public void verifyLabelTest() {
        Logs.info("haciendo swipe para ver los labels");
        final var canvas = driver.findElement(AppiumBy.accessibilityId("test-Login"));
        Gestures.swipeVertical(50, 50, 20, canvas);

        loginPage.verifyLabels();
    }
}

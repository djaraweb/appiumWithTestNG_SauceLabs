package saucedemo;

import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Gestures;
import utilities.Logs;

public class LoginTests extends BaseTests {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
//        Logs.debug("Esperando que cargue la pagina");
//        sleep(1000);
    }

    @Test(groups = {regression, smoke})
    public void invalidCredentialsTest() {
        Logs.info("Escribiendo en el username input");
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("locked_out_user");

        Logs.info("Escribiendo en el password input");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();

        Logs.info("Verificando mensaje de Error");
        final var messageError = driver.findElement(AppiumBy.androidUIAutomator("description(\"test-Error message\").childSelector(className(\"android.widget.TextView\"))"));
        softAssert.assertTrue(messageError.isDisplayed());
        softAssert.assertEquals(messageError.getText(), "Sorry, this user has been locked out.");

        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void verifyPageTest() {
        Logs.info("Verificando pagina de Login");
        final var usernameInput = driver.findElement(AppiumBy.accessibilityId("test-Username"));
        final var passwordInput = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        final var loginBtn = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));

        softAssert.assertTrue(usernameInput.isDisplayed());
        softAssert.assertTrue(passwordInput.isDisplayed());
        softAssert.assertTrue(loginBtn.isDisplayed());
        softAssert.assertAll();
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

        Logs.info("Verificando los labels");
        final var standardUserLabel = driver.findElement(
                AppiumBy.androidUIAutomator("text(\"standard_user\")")
        );
        final var lockedOutUser = driver.findElement(
                AppiumBy.androidUIAutomator("text(\"locked_out_user\")")
        );

        final var problemUser = driver.findElement(
                AppiumBy.androidUIAutomator("text(\"problem_user\")")
        );

        final var secretSauceLabel = driver.findElement(
                AppiumBy.androidUIAutomator("text(\"secret_sauce\")")
        );

        softAssert.assertTrue(standardUserLabel.isDisplayed());
        softAssert.assertTrue(lockedOutUser.isDisplayed());
        softAssert.assertTrue(problemUser.isDisplayed());
        softAssert.assertTrue(secretSauceLabel.isDisplayed());
        softAssert.assertAll();
    }
}

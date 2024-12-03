package saucedemo;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Logs;

public class LoginTests extends BaseTests {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.debug("Esperando que cargue la pagina");
        sleep(1000);
    }

    @Test
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

    @Test
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
}

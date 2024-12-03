package saucedemo;

import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Logs;

public class BurgerMenuTests extends BaseTests {

    @BeforeMethod
    public void setUp() {
        Logs.debug("Esperando que cargue la pagina");
        sleep(1000);

        Logs.info("Escribiendo en el username input");
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");

        Logs.info("Escribiendo en el password input");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();

        Logs.info("Espero que cargue la pagina de Shopping");
        sleep(2000);

        Logs.info("Haciendo click en el burger menu para abrirlo");
        driver.findElement(AppiumBy.accessibilityId("test-Menu")).click();

        Logs.info("Esperando que cargue el burger Menu");
        sleep(1000);
    }

    @Test
    public void logoutTest() {
        Logs.info("Haciendo click en el boton de logout");
        driver.findElement(AppiumBy.accessibilityId("test-LOGOUT")).click();

        Logs.info("Espero que redirija a la pagina de Login");
        sleep(1500);

        Logs.info("Verifico que estoy en la pagina de Login");
        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("test-Username")).isDisplayed());
    }

    @Test
    public void closeXTest() {
        Logs.info("Haciendo click en el boton de la X");
        driver.findElement(AppiumBy.accessibilityId("test-Close")).click();

        Logs.info("Espero que redirija a la pagina de shopping");
        sleep(1500);

        Logs.info("Verifico que estoy en la pagina de Shoppin");
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")"))
                .isDisplayed());
    }
}

package saucedemo;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Logs;

public class ShoppingTests extends BaseTests {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
//        Logs.debug("Esperando que cargue la pagina");
//        sleep(1000);

        Logs.info("Escribiendo en el username input");
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");

        Logs.info("Escribiendo en el password input");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();

//        Logs.info("Espero que cargue la pagina de Shopping");
//        sleep(2000);
    }

    @Test (groups = {regression,smoke})
    public void verifyPageTest() {
        Logs.info("Verificando la pagina de Shopping");
        final var titleProducts = driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")"));
        final var botonFiltro = driver.findElement(AppiumBy.accessibilityId("test-Modal Selector Button"));
        final var botonLista = driver.findElement(AppiumBy.accessibilityId("test-Toggle"));
        final var listItems = driver.findElement(AppiumBy.accessibilityId("test-PRODUCTS"));

        softAssert.assertTrue(titleProducts.isDisplayed());
        softAssert.assertTrue(botonFiltro.isDisplayed());
        softAssert.assertTrue(botonFiltro.isEnabled());
        softAssert.assertTrue(botonLista.isDisplayed());
        softAssert.assertTrue(botonLista.isEnabled());
        softAssert.assertTrue(listItems.isDisplayed());

        softAssert.assertAll();

    }

    @Test (groups = {regression,smoke})
    public void filterTest() {
        Logs.info("Hago click en el boton de filtro");
        driver.findElement(AppiumBy.accessibilityId("test-Modal Selector Button")).click();

//        Logs.info("Espero que cargue la pagina de opciones");
//        sleep(1000);

        Logs.info("Selecciono la opcion price low to high");
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Price (low to high)\")")).click();

//        Logs.info("Espero que se refresque la pagina");
//        sleep(1000);

        Logs.info("Verifico la información del 1er item");
        final var titulo = driver.findElements(AppiumBy.accessibilityId("test-Item title")).get(0).getText();
        final var precioDolar = driver.findElements(AppiumBy.accessibilityId("test-Price")).get(0).getText();

        final var precioSinDolar = Double.parseDouble(precioDolar.replace("$", ""));

        softAssert.assertEquals(titulo, "Sauce Labs Onesie");
        softAssert.assertEquals(precioSinDolar, 7.99);
        softAssert.assertAll();
        
    }
}

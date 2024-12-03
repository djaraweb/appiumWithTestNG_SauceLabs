package saucedemo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Logs;

public class ItemDetailTests extends BaseTests {

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

        Logs.info("Hago click en la 1era imagen");
        driver.findElements(AppiumBy.androidUIAutomator("description(\"test-Item\").childSelector(className(\"android.widget.ImageView\"))")).get(0).click();

//        Logs.info("Espero que cargue el detalle del item");
//        sleep(1000);

    }

    @Test (groups = {regression,smoke})
    public void verifyItemTest() {
        Logs.info("Verificando la pagina de item details");
        final var backProductsButton = driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS"));
        final var image = driver.findElement(AppiumBy.androidUIAutomator("description(\"test-Inventory item page\").childSelector(className(\"android.widget.ImageView\"))"));
        final var infoList = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]//android.widget.TextView"));
        final var title = infoList.get(0);
        final var description = infoList.get(1);
        final var price = driver.findElement(AppiumBy.accessibilityId("test-Price"));

        softAssert.assertTrue(backProductsButton.isDisplayed());
        softAssert.assertTrue(backProductsButton.isEnabled());
        softAssert.assertTrue(image.isDisplayed());
        softAssert.assertTrue(title.isDisplayed());
        softAssert.assertTrue(description.isDisplayed());
        softAssert.assertTrue(price.isDisplayed());

        softAssert.assertAll();

    }

    @Test (groups = {regression,smoke})
    public void backToProductsTest() {
        Logs.info("Hacer click en back to products");
        driver.findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS")).click();

//        Logs.info("Espero que cargue la pagina");
//        sleep(1000);

        Logs.info("Verifico que estoy en la pagina de shopping");
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")")).isDisplayed());
    }

    @Test (groups = {regression,smoke})
    public void pressBackTest() {
        Logs.info("Presionar el boton atras del celular");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

//        Logs.info("Espero que cargue la pagina");
//        sleep(1000);

        Logs.info("Verifico que estoy en la pagina de shopping");
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")")).isDisplayed());
    }
}

package saucedemo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.ContextUtilities;
import utilities.Logs;

public class DeepLinksTests extends BaseTests {

    @Test (groups = {regression,smoke})
    public void completeCheckoutTest() {
        Logs.info("Voy a la pagina de complete con el checkout usando el deeplink");
        driver.get("swaglabs://complete");

//        Logs.info("Espero que cargue la pagina de complete Checkout");
//        sleep(1500);

        Logs.info("Click en el boton Back Home");
        driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();

//        Logs.info("Espero que cargue la pagina de shopping");
//        sleep(1500);

        Logs.info("verificamos que cargue la pagina de shopping");
        Assert.assertTrue(
                driver.findElement(AppiumBy.androidUIAutomator("text(\"PRODUCTS\")")).isDisplayed()
        );
    }

    @Test (groups = {regression,smoke})
    public void itemNumero4Test() {
        Logs.info("Navegamos al detalle del item 4 usando deeplink");
        driver.get("swaglabs://swag-item/4");

//        Logs.info("Espero que cargue la pagina del detalle del producto");
//        sleep(1500);

        Logs.info("Verifico que el nombre del item sea Sauce Labs Onesie");
        final var infoList = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]//android.widget.TextView"));
        final var title = infoList.get(0);
        Assert.assertEquals(
                title.getText(), "Sauce Labs Onesie"
        );
    }

    @Test (groups = {regression,smoke})
    public void webViewTest() {
        Logs.info("Navego a la pagina de webview usando deeplink");
        driver.get("swaglabs://webview");

//        Logs.info("Espero que cargue la pagina");
//        sleep(1500);

        Logs.info("Escribo la pagina a navegar");
        driver.findElement(AppiumBy.accessibilityId("test-enter a https url here..."))
                .sendKeys("www.saucedemo.com");

        Logs.info("Hago click eb el boton GO TO SITE");
        driver.findElement(AppiumBy.accessibilityId("test-GO TO SITE")).click();

//        Logs.info("Espero que cargue la pagina web");
//        sleep(1500);

        Logs.info("Cambiando de contexto a webview");
        ContextUtilities.switchWebContext();

        Logs.info("Verifico que el username sea visible");
        Assert.assertTrue(
                driver.findElement(By.id("user-name")).isDisplayed()
        );

        Logs.info("Cambiando contexto a native app");
        ContextUtilities.switchNativeApp();

        Logs.info("Presionar el boton atras del celular");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

//        Logs.info("Espero que cargue la pagina");
//        sleep(1000);

        Logs.info("verifico que haya regresado a la pagina de naviteweb");
        Assert.assertTrue(
                driver.findElement(AppiumBy.accessibilityId("test-enter a https url here...")).isDisplayed()
        );
    }
}

package saucedemo;

import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Gestures;
import utilities.Logs;

public class YourCartTests extends BaseTests {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navegar a la página usando el deeplink");
        driver.get("swaglabs://cart/0,1");
    }

    @Test(groups = {regression})
    public void deleteScrollTest() {
        Logs.info("Haciendo scroll de derecha a izquierda");
        final var canvas = driver.findElements(AppiumBy.accessibilityId("test-Item")).get(0);
        Gestures.swipeHorizontal(50, 60, 20, canvas);

        Logs.info("Haciendo click en el tacho");
        driver.findElement(AppiumBy.accessibilityId("test-Delete")).click();

        Logs.info("Verificando que haya 1 elemento en el carrito");
        final var counter = driver.findElement(AppiumBy.androidUIAutomator("description(\"test-Cart\").childSelector(className(\"android.widget.TextView\"))"));
        Assert.assertEquals(Integer.parseInt(counter.getText()), 1);
    }
}

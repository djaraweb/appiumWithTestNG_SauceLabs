package saucedemo;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Gestures;
import utilities.Logs;

public class DrawingTests extends BaseTests {
    private WebElement canvas;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.debug("Navegando a la pagina usando deeplink");
        driver.get("swaglabs://drawing");

        canvas = driver.findElement(AppiumBy.androidUIAutomator(
                "text(\"Signature Pad demo\").childSelector(className(\"android.widget.Image\"))"
        ));
    }

    @Test(groups = {regression})
    public void lineaVerticalTest() {
        Gestures.swipeVertical(50, 20, 60, canvas);
        sleep(2000);
    }

    @Test(groups = {regression})
    public void lineaHorizontalTest() {
        Gestures.swipeHorizontal(50, 10, 90, canvas);
        sleep(2000);
    }

    @Test(groups = {regression})
    public void simboloSumaTest() {
        Gestures.swipeHorizontal(50, 10, 90, canvas);
        Gestures.swipeVertical(50, 20, 60, canvas);
        sleep(2000);
    }

    @Test(groups = {regression})
    public void backslashTest() {
        Gestures.swipe(20, 20, 80, 80, canvas);
        sleep(2000);
    }

    @Test(groups = {regression})
    public void slashTest() {
        Gestures.swipe(80, 20, 20, 80, canvas);
        sleep(2000);
    }

    @Test(groups = {regression})
    public void crossTest() {
        Gestures.swipe(20, 20, 80, 80, canvas);
        Gestures.swipe(80, 20, 20, 80, canvas);
        sleep(2000);
    }
}

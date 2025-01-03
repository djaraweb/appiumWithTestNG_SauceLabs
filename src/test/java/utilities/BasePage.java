package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    private final static int defaultTimeout = 5;
    private final int timeOut;
    protected final SoftAssert softAssert;

    public BasePage(int timeOut) {
        this.timeOut = timeOut;
        softAssert = new SoftAssert();
    }

    public BasePage() {
        this(defaultTimeout);
    }

    protected AndroidDriver getDriver() {
        return new DriverProvider().get();
    }

    protected WebElement waitForDisplayed(By locator, int time) {
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForDisplayed(By locator) {
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(this.timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitPage(By locator, String pageName) {
        Logs.info("Esperando que la pagina %s cargue", pageName);
        waitForDisplayed(locator, timeOut);
        Logs.info("%s ha cargado satisfactoriamente", pageName);
    }

    protected WebElement find(By locator) {
        return getDriver().findElement(locator);
    }

    protected List<WebElement> findAll(By locator) {
        return getDriver().findElements(locator);
    }

    protected void pressBack() {
        getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
    }

    protected void sleep(long timeMs) {
        try {
            Logs.info("Esperando por %d ms", timeMs);
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            Logs.error("Error al esperar: %s", e.getLocalizedMessage());
        }
    }

    public abstract void waitPageToLoad(); // esperar que carge la pagina

    public abstract void verfyPage(); // verificar la UI de la pagina

}

package page;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class LoginPage extends BasePage {
    private final By usernameInput = AppiumBy.accessibilityId("test-Username");
    private final By passwordInput = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private final By errorMessage = AppiumBy.androidUIAutomator("description(\"test-Error message\").childSelector(className(\"android.widget.TextView\"))");
    private final By standardUserLabel = AppiumBy.androidUIAutomator("text(\"standard_user\")");
    private final By lockedOutUser = AppiumBy.androidUIAutomator("text(\"locked_out_user\")");
    private final By problemUser = AppiumBy.androidUIAutomator("text(\"problem_user\")");
    private final By secretSauceLabel = AppiumBy.androidUIAutomator("text(\"secret_sauce\")");

    @Override
    @Step("Esperando que cargue la pagina de login")
    public void waitPageToLoad() {
        waitPage(usernameInput, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de login")
    public void verfyPage() {
        softAssert.assertTrue(find(usernameInput).isDisplayed());
        softAssert.assertTrue(find(passwordInput).isDisplayed());
        softAssert.assertTrue(find(loginButton).isDisplayed());

        softAssert.assertAll();
    }

    @Step("Rellenando formulario de Login")
    public void fillForm(String username, String password) {
        Logs.info("Escribiendo el username");
        find(usernameInput).sendKeys(username);

        Logs.info("Escribiendo el password");
        find(passwordInput).sendKeys(password);

        Logs.info("Haciendo click en el boton de Login");
        find(loginButton).click();
    }

    @Step("Verificando el mensaje de error")
    public void verifyErrorMessage(String text) {
        final var errorMessageElement = find(errorMessage);
        softAssert.assertTrue(errorMessageElement.isDisplayed());
        softAssert.assertEquals(errorMessageElement.getText(), text);

        softAssert.assertAll();
    }

    @Step("Verificando labels en la pagina")
    public void verifyLabels() {
        Logs.info("Verificando los labels");
        softAssert.assertTrue(find(standardUserLabel).isDisplayed());
        softAssert.assertTrue(find(lockedOutUser).isDisplayed());
        softAssert.assertTrue(find(problemUser).isDisplayed());
        softAssert.assertTrue(find(secretSauceLabel).isDisplayed());
        softAssert.assertAll();
    }
}

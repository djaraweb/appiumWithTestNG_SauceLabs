package utilities;

import io.appium.java_client.android.AndroidDriver;

public class ContextUtilities {

    public static AndroidDriver getDriver() {
        return new DriverProvider().get();
    }

    public static void switchWebContext() {
        final var driver = getDriver();
        final var setContext = driver.getContextHandles();
        Logs.debug("Set Context : %s", setContext);
        for (var context : setContext) {
            if (context.contains("WEBVIEW")) {
                Logs.info("Cambiando de contexto a webview");
                driver.context(context);
            }
        }
    }

    public static void switchNativeApp() {
        final var driver = getDriver();
        final var setContext = driver.getContextHandles();
        Logs.debug("Set Context : %s", setContext);
        for (var context : setContext) {
            if (context.contains("NATIVE_APP")) {
                Logs.info("Cambiando de contexto a native app");
                driver.context(context);
            }
        }
    }
}

package utilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class Gestures {
    private static final PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "pointer");

    public static AndroidDriver getDriver() {
        return new DriverProvider().get();
    }

    public static void tab(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        // 1. Muevo el puntero hacia el centro del elemento
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        centerPoint
                )
        );
        // 2. Toco la pantalla del celular
        sequence.addAction(
                pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );
        //3. Agrego una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(1000))
        );

        // 4. Dejo de tocar la pantalla del celular
        sequence.addAction(
                pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );
        // 5 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico
    }

    public static void doubleTab(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        for (var i = 0; i < 2; i++) {
            // 1. Muevo el puntero hacia el centro del elemento
            sequence.addAction(
                    pointer.createPointerMove(
                            Duration.ofMillis(1000),
                            PointerInput.Origin.viewport(),
                            centerPoint
                    )
            );
            // 2. Toco la pantalla del celular
            sequence.addAction(
                    pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
            );
            //3. Agrego una breve pausa
            sequence.addAction(
                    new Pause(pointer, Duration.ofMillis(1000))
            );

            // 4. Dejo de tocar la pantalla del celular
            sequence.addAction(
                    pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
            );
        }
        // 5 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico
    }

    public static void longTab(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        // 1. Muevo el puntero hacia el centro del elemento
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        centerPoint
                )
        );
        // 2. Toco la pantalla del celular
        sequence.addAction(
                pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );
        //3. Agrego una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(3500))
        );

        // 4. Dejo de tocar la pantalla del celular
        sequence.addAction(
                pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );
        // 5 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico
    }

    private static Point getCenterPoint(WebElement element) {
        final var ubication = element.getLocation();
        final var dimentions = element.getSize();

        final var centerx = ubication.getX() + (dimentions.getWidth() / 2);
        final var centery = ubication.getY() + (dimentions.getHeight() / 2);
        return new Point(centerx, centery);
    }

    public static void dragAndDrop(
            WebElement elementOrigin,
            WebElement elementDestiny
    ){
        final var origincenterPoint = getCenterPoint(elementOrigin);
        final var destinycenterPoint = getCenterPoint(elementDestiny);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        // 1. Mover el puntero hacia el centro del origen
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),
                        origincenterPoint
                )
        );

        // 2. Tocamos la pantalla
        sequence.addAction(
                pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );

        // 3. Agregamos una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(1000))
        );

        // 4. Arrastramos/movemos al elemento destino
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        destinycenterPoint
                )
        );

        // 5. Agregamos una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(1500))
        );

        // 6. Dejamos de tocar la pantalla
        sequence.addAction(
                pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );

        // 7 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico

    }
}

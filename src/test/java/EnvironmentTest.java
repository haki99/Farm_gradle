import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 *
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
class EnvironmentTest  extends ApplicationTest {

    /**Starts the javafx
     *
     * @param PrimaryStage set the primary stage where the javafx will draw on
     */
    @Override
    public void start(Stage PrimaryStage) {
        Displayer d = new Displayer();
        Player p = new Player();

        d.initialize(PrimaryStage, p);
        d.Main_Menu();
    }

    /**
     * Tests that the ingame starts correctly
     */
    @Test
    @DisplayName("Enviroment load test")
    public void mainmenutest(){
        clickOn("#Bplay");
        clickOn("#textField").write("name");
        clickOn("#diff");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#Bstart");
    }
}
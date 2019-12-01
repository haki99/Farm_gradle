import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
class DisplayerTest extends ApplicationTest{

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
     * Tests the buttons of the main menu
     */
    @Test
    @DisplayName("Main menu play button test")
    public void mainmenutest(){
        verifyThat("#Bplay", hasText("Játék"));
        verifyThat("#Bload", hasText("Betöltés"));
        verifyThat("#Bexit", hasText("Kilépés"));
    }

    /**
     * Tests the main menu's play button and the setup menu's back button
     */
    @Test
    @DisplayName("main menu play button click test")
    void mainmenutosetuptest() {
        clickOn("#Bplay");
        verifyThat("#Bstart", hasText("Játék"));
        verifyThat("#Bback", hasText("Vissza"));
    }

    /**
     * Tests the setup menu's textfield
     */
    @Test
    @DisplayName("setup menu textfield test")
    void setupgame_textfield_test(){
        clickOn("#Bplay");
        clickOn("#textField").write("name");
        verifyThat("#textField", TextInputControlMatchers.hasText("name"));
    }

    /**
     * Tests the setup menu's combobox
     */
    @Test
    @DisplayName("setup menu combobox existence test")
    void setupgame_combobox_test(){
        clickOn("#Bplay");
        clickOn("#diff");
        verifyThat("#diff", ComboBoxMatchers.hasItems(3));
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        verifyThat("#diff", ComboBoxMatchers.hasSelectedItem("Könnyű"));
    }

    /**
     * Tests the close of the program from the main menu
     */
    @Test
    @DisplayName("Quit from main menu test")
    void mainmenu_quit_test() {
        clickOn("#Bexit");
        clickOn("#yes");
    }
}
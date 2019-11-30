import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


class DisplayerTest extends ApplicationTest{

    @Override
    public void start(Stage PrimaryStage) {
        Displayer d = new Displayer();
        Player p = new Player();

        d.initialize(PrimaryStage, p);
        d.Main_Menu();
    }

    @Test
    @DisplayName("Main menu play button test")
    public void mainmenutest(){
        verifyThat("#Bplay", hasText("Játék"));
        verifyThat("#Bload", hasText("Betöltés"));
        verifyThat("#Bexit", hasText("Kilépés"));
    }

    @Test
    @DisplayName("main menu play button click test")
    void mainmenutosetuptest() {
        clickOn("#Bplay");
        verifyThat("#Bstart", hasText("Játék"));
        verifyThat("#Bback", hasText("Vissza"));
    }

    @Test
    @DisplayName("setup menu textfield test")
    void setupgame_textfield_test(){
        clickOn("#Bplay");
        clickOn("#textField").write("name");
        verifyThat("#textField", TextInputControlMatchers.hasText("name"));
    }

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

    @Test
    @DisplayName("setup menu combobox select test")
    void setupgame_comboboxselect_test() {
        clickOn("#Bplay");
        clickOn("#diff");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        verifyThat("#diff", ComboBoxMatchers.hasSelectedItem("Könnyű"));
    }
}
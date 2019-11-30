import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
public class Main extends Application{
	
	Player player = new Player();
    
	/**
	 * Initialize and starts the JavaFX
	 * 
	 * @param args JavaFX args
	 */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the main program, and opens the main menu
     */
    @Override
    public void start(Stage PrimaryStage) 
    {
    	Displayer displayer = new Displayer();
    	
    	displayer.initialize(PrimaryStage, player);
    	
    	displayer.Main_Menu();
    }
}
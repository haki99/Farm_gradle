import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
public class Displayer {
	private Stage window;
	private Player player;
	
	/**
	 * Initialize and creates the window. Add title, icon to it and
	 * sets the size and align of the window.
	 * 
	 * On close request calls the closeProgram function
	 * 
	 * @param PrimaryStage gets the actual stage
	 * @param p the player
	 */
	public void initialize(Stage PrimaryStage, Player p) {
		Image traktor = new Image(getClass().getResource("/Pics/traktor.gif").toExternalForm());
		
		player = p;
		window = PrimaryStage;
    	window.setTitle("Farm");
    	window.getIcons().add(traktor);
    	
    	window.centerOnScreen();
        window.setMaxHeight(800);
        window.setMaxWidth(1200);
        
        window.setOnCloseRequest((event) -> {
        	event.consume();
        	player.save();
        	closeProgram();
        });
	}

	/**
	 * Draws the main menu's background and add 3 button to it.
	 * Sets the actions of the buttons.
	 */
	public void Main_Menu() {
    	
		Button Bplay = new Button("Játék");
		Bplay.setId("Bplay");
		Button Bload = new Button("Betöltés");
		Bload.setId("Bload");
    	Button Bexit = new Button("Kilépés");
		Bexit.setId("Bexit");
		
		Bplay.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                window.setScene(Setup_Game());
          }
		});
		
		Bload.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
            	load();
          }
		});
		
    	Bexit.setOnAction((event) -> {
    		event.consume();
    		player.save();
    		closeProgram();
    	});
		
    	VBox vbox = new VBox(5);
    	vbox.getChildren().addAll(Bplay, Bload, Bexit);
    	
    	StackPane main = new StackPane ();
    	main.setPrefSize(1200, 800);
    	
    	BackgroundSize bsize = new BackgroundSize(1504, 1000, false, false, false, false);
    	
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResource("Pics/main_background.jpg").toExternalForm()),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsize);
        main.setBackground(new Background(myBI));
    	
        main.getChildren().add(vbox);
        vbox.setAlignment(Pos.CENTER);
        
        Scene main_menu = new Scene(main, 1200, 800);
        
        window.setScene(main_menu);
        window.show();
	}
	
	/**
	 * In case of load, it loads the players data from the 'farm_save' named file.
	 */
	public void load() {
		try
        {
            FileInputStream fis = new FileInputStream("farm_save");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            player = (Player) ois.readObject();

            ois.close();
            fis.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

		player.grow_since();
		
		int x = 0;
		int y = 0;
		
		switch(player.getdiff()) {
			case 1: x = 5; y = 5; break;
			case 2: x = 10; y = 8; break;
			case 3: x = 20; y = 8; break;
		}
		
	    Environment environment  = new Environment();
	    environment.initialize(window);
		environment.draw_enviroment(window);	
		environment.draw_soil(window, player, x, y);
		environment.smart_garden(player, x, y, environment, window);
		environment.draw_garden(window, player);
	}
	
	/**
	 * It closes the program, and shuts down the javafx.
	 */
	public void closeProgram() {
    	Boolean answer = ConfirmBox.display("Vigyázat!", "Biztos ki akarsz lépni? \nA játékállás mentésre kerül.");
    	player.save();
    	if(answer) window.close();
    }
	
	/**
	 * Draws the setup game menu, and add an observable list to it to set the difficulty of the game, a text field
	 * to write the players name and 2 button.
	 * 
	 * @return Scene
	 */
	public Scene Setup_Game() {
		ObservableList<String> difficulty = FXCollections.observableArrayList("Könnyű","Közepes","Nehéz");
		ComboBox<String> diff_ch = new ComboBox<String>(difficulty);
		diff_ch.setId("diff");
		
		Label label1 = new Label("Név:");
		TextField textField = new TextField ();
		textField.setId("textField");
		textField.setMaxWidth(200);
		
		Button Bback = new Button("Vissza");
		Button Bstart = new Button("Játék");
		Bstart.setId("Bstart");
		Bback.setId("Bback");
		
		Bback.setOnAction((event) -> {
			Main_Menu();
    	});
		
		Bstart.setOnAction((event) -> {  	
			int x = 0;
			int a = 0;
			int b = 0;
			switch(diff_ch.getValue()) {
				case "Könnyű": x = 1; a = 5; b = 5; break;
				case "Közepes": x = 2; a = 10; b = 8;  break;
				case "Nehéz": x = 3; a = 20; b = 8;  break;
				default: break;
			}
			
        	player.set_data(textField.getText(), x, a, b);
    		Game_display();
    	});
		
		GridPane setup_game = new GridPane ();
		setup_game.setVgap(5);
		setup_game.setHgap(5);
		setup_game.setPadding(new Insets(10, 10, 10, 10));
		setup_game.setAlignment(Pos.CENTER);
		
		setup_game.add(label1, 0, 0);
		setup_game.add(textField, 1, 0);
		setup_game.add(diff_ch, 1, 1);
		setup_game.add(Bstart, 1, 2);
		setup_game.add(Bback, 1, 3);
		
		BackgroundSize bsize = new BackgroundSize(1504, 1000, false, false, false, false);
    	
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResource("Pics/main_background.jpg").toExternalForm()),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsize);
        
        setup_game.setBackground(new Background(myBI));		
		Scene sgame = new Scene(setup_game, 1200, 800);
		
		return sgame;
	}
	
	/**
	 * Calls the environment class to draw the ingame textures.
	 */
	public void Game_display() {
		int x = 0;
		int y = 0;
		
		switch(player.getdiff()) {
			case 1: x = 5; y = 5; break;
			case 2: x = 10; y = 8; break;
			case 3: x = 20; y = 8; break;
		}
		
	    Environment environment  = new Environment();
	    environment.initialize(window);
		environment.draw_enviroment(window);	
		environment.draw_soil(window, player, x, y);
		environment.smart_garden(player, x, y, environment, window);
	}
}

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
public class Environment {
	private GraphicsContext gc;
	private Canvas canvas;
	private Group root;
	
	/**Initialize the environment and sets the drawing canvas and graphichs context
	 * 
	 * @param window get the actual window to draw on it
	 */
	public void initialize(Stage window) {
		root = new Group();
        canvas = new Canvas(1200, 800);
	    gc = canvas.getGraphicsContext2D();
	    
	    root.getChildren().addAll(canvas);
	    
		Scene ingame = new Scene(root,1200, 800);
        window.setScene(ingame);
	}
	
	/**Loads the picture and draws the statics on the screen.
	 * 
	 * @param window get the actual window to draw on it
	 */
	public void draw_enviroment(Stage window) {    
        
        Image sand = new Image(getClass().getResource("Pics/Environment/Sand/sand.png").toExternalForm());
		Image sand_right = new Image(getClass().getResource("Pics/Environment/Sand/right.png").toExternalForm());
		Image sand_bottom = new Image(getClass().getResource("Pics/Environment/Sand/bottom.png").toExternalForm());
		Image sand_bottom_right = new Image(getClass().getResource("Pics/Environment/Sand/bottom_right.png").toExternalForm());
		Image sand_bottom_small_right = new Image(getClass().getResource("Pics/Environment/Sand/bottom_small_right.png").toExternalForm());
		Image boat = new Image(getClass().getResource("Pics/Environment/Other/fishing_boat.png").toExternalForm());
		Image sd_left = new Image(getClass().getResource("Pics/Environment/Sand/sand_left.png").toExternalForm());
		Image sd_top = new Image(getClass().getResource("Pics/Environment/Sand/sand_top.png").toExternalForm());
		Image sd_top_left = new Image(getClass().getResource("Pics/Environment/Sand/sand_top_left.png").toExternalForm());
		Image sd_top_left_small = new Image(getClass().getResource("Pics/Environment/Sand/sand_top_left_small.png").toExternalForm());
		Image grass_middle = new Image(getClass().getResource("Pics/Environment/Grass/grass_middle.png").toExternalForm());
		Image grass_left = new Image(getClass().getResource("Pics/Environment/Grass/grass_left.png").toExternalForm());
		Image grass_bottom = new Image(getClass().getResource("Pics/Environment/Grass/grass_bottom.png").toExternalForm());
		Image grass_bottom_left = new Image(getClass().getResource("Pics/Environment/Grass/grass_bottom_left.png").toExternalForm());
		Image grass_right_inside = new Image(getClass().getResource("Pics/Environment/Grass/grass_right_inside.png").toExternalForm());
		
		Rectangle background = new Rectangle(0, 0, 1200, 800);
		Rectangle tenger1 = new Rectangle(0, 0, 160, 256);
		Rectangle tenger2 = new Rectangle(0, 0, 512, 160);
	    
	    //z�ld h�tt�r
	    gc.setFill(Color.rgb(47, 129, 54));
        gc.fillRect(background.getLayoutX(),      
        		background.getLayoutY(), 
        		background.getWidth(), 
        		background.getHeight());
	    
        //tenger
	    gc.setFill(Color.rgb(21, 108, 153));
        gc.fillRect(tenger1.getLayoutX(),      
        		tenger1.getLayoutY(), 
        		tenger1.getWidth(), 
        		tenger1.getHeight());
        
        gc.fillRect(tenger2.getLayoutX(),      
        		tenger2.getLayoutY(), 
        		tenger2.getWidth(), 
        		tenger2.getHeight());
        
        //sarok homokok a tengernél
        gc.drawImage(sand_bottom_right, 160, 256);
        gc.drawImage(sand_bottom_right, 512, 160);
        gc.drawImage(sand_bottom_small_right, 160, 160);
        
        //jobb partszakasz
        for(int i = 0; i < 5; i++) {
        	gc.drawImage(sand_right, 512, 0 + i * 32);
        }
        
        //kicsi jobb partszakasz
        for(int i = 0; i < 2; i++) {
        	gc.drawImage(sand_right, 160, 192 + i * 32);
        }
        
        for(int i = 0; i < 10; i++) {
        	gc.drawImage(sand_bottom, 192 + i * 32, 160);
        }
        
        //kicsi als� partszakasz
        for(int i = 0; i < 5; i++) {
        	gc.drawImage(sand_bottom, 0 + i * 32, 256);
        }
        
        //als� partszakasz
        for(int i = 0; i < 11; i++) {
        	for(int j = 0; j < 3; j++) {
            	gc.drawImage(sand, 192 + i * 32, 192  + j * 32);
            }
        }
        
        //part bels� szakaszok
        for(int i = 0; i < 9; i++) {
        	gc.drawImage(sand, 544, 0 + i * 32);
        }
        
        for(int i = 0; i < 8; i++) {
        	gc.drawImage(sand, 576, 0 + i * 32);
        }
        
        for(int i = 0; i < 7; i++) {
        	gc.drawImage(sand, 608, 0 + i * 32);
        }
        
        
        for(int i = 0; i < 12; i++) {
        	gc.drawImage(sand, 0 + i * 32, 288);
        }

        for(int i = 0; i < 8; i++) {
        	gc.drawImage(sand, 0 + i * 32, 320);
        }
        
        
        //haj�
        gc.drawImage(boat, 64, 64);
        
        //homok-f� hat�r
        for(int i = 0; i < 7; i++) {
        	gc.drawImage(sd_left, 640, 0 + i * 32);
        }
        
        for(int i = 0; i < 8; i++) {
        	gc.drawImage(sd_top, 0 + i * 32, 352);
        }
        
        for(int i = 0; i < 3; i++) {
        	gc.drawImage(sd_top, 288 + i * 32, 320);
        }
        
        for(int i = 0; i < 5; i++) {
        	gc.drawImage(sd_top, 416 + i * 32, 288);
        }
        
        gc.drawImage(sd_top_left, 384, 288);
        gc.drawImage(sd_top_left, 256, 320);
        gc.drawImage(sd_top_left_small, 256, 352);
        gc.drawImage(sd_top_left_small, 384, 320);
        gc.drawImage(sd_top_left_small, 576, 288);
        gc.drawImage(sd_top_left, 576, 256);
        gc.drawImage(sd_top_left_small, 608, 256);
        gc.drawImage(sd_top_left, 608, 224);
        gc.drawImage(sd_top_left_small, 640, 224);
        
        //F�
        for(int i = 0; i < 8; i++) {
        	for(int j = 0; j < 10; j++) {
        		gc.drawImage(grass_middle, 960 + i * 32, 0 + j * 32);
        	}
        }
        
        for(int i = 0; i < 5; i++) {
        	for(int j = 0; j < 4; j++) {
        		gc.drawImage(grass_middle, 1056 + i * 32, 320 + j * 32);
        	}
        }
        
        for(int i = 0; i < 3; i++) {
        	for(int j = 0; j < 2; j++) {
        		gc.drawImage(grass_middle, 1120 + i * 32, 448 + j * 32);
        	}
        }
        
        for(int i = 0; i < 10; i++) {
        	gc.drawImage(grass_left, 928, 0 + i * 32);
        }
        
        gc.drawImage(grass_right_inside, 1088, 448);

        gc.drawImage(grass_left, 1088, 480);

        gc.drawImage(grass_right_inside, 1024, 320);
        
        for(int i = 0; i < 3; i++) {
        	gc.drawImage(grass_left, 1024, 352 + i * 32);
        }
        
        for(int i = 0; i < 10; i++) {
        	gc.drawImage(grass_bottom, 1120 + i * 32, 512);
        }
        
        gc.drawImage(grass_bottom, 1056, 448);
        
        for(int i = 0; i < 2; i++) {
        	gc.drawImage(grass_bottom, 960 + i * 32, 320);
        }
        
        gc.drawImage(grass_bottom_left, 928, 320);
        gc.drawImage(grass_bottom_left, 1024, 448);
        gc.drawImage(grass_bottom_left, 1088, 512);
        
	    window.show();
	}
	
	/**Draws the garden which size is depends on the players difficulty choice
	 * 
	 * @param window get the actual window to draw on it
	 * @param player gets the player to use its data for the gardens size
	 * @param x the top left x pos
	 * @param y the top left y pos
	 */
	public void draw_soil(Stage window, Player player, int x, int y) {

		Image soil_top_left = new Image(getClass().getResource("Pics/Environment/Garden/soil_top_left.png").toExternalForm());
		Image soil_top_right = new Image(getClass().getResource("Pics/Environment/Garden/soil_top_right.png").toExternalForm());
		Image soil_bottom_left = new Image(getClass().getResource("Pics/Environment/Garden/soil_bottom_left.png").toExternalForm());
		Image soil_bottom_right = new Image(getClass().getResource("Pics/Environment/Garden/soil_bottom_right.png").toExternalForm());
		Image soil_top = new Image(getClass().getResource("Pics/Environment/Garden/soil_top.png").toExternalForm());
		Image soil_bottom = new Image(getClass().getResource("Pics/Environment/Garden/soil_bottom.png").toExternalForm());
		Image soil_left = new Image(getClass().getResource("Pics/Environment/Garden/soil_left.png").toExternalForm());
		Image soil_right = new Image(getClass().getResource("Pics/Environment/Garden/soil_right.png").toExternalForm());
		Image soil = new Image(getClass().getResource("Pics/Environment/Garden/soil_line.png").toExternalForm());
		Image hole = new Image(getClass().getResource("Pics/Environment/Garden/hole.png").toExternalForm());
		
		//Bal felső blokk
		gc.drawImage(soil_top_left, 224, 416);
		
		//Jobb felső blokk
		gc.drawImage(soil_top_right, 256 + x * 32, 416);
		
		//Bal alsó blokk
		gc.drawImage(soil_bottom_left, 224, 448 + y * 32);
		
		//Jobb alsó blokk
		gc.drawImage(soil_bottom_right, 256 + x * 32, 448 + y * 32);
		
		for(int i = 0; i < x; i++) {
			gc.drawImage(soil_top, 256 + i * 32, 416);
		}
		
		for(int i = 0; i < x; i++) {
			gc.drawImage(soil_bottom, 256 + i * 32, 448 + y * 32);
		}
		
		for(int j = 0; j < y; j++) {
			gc.drawImage(soil_left, 224, 448 + j * 32);
		}
		
		for(int j = 0; j < y; j++) {
			gc.drawImage(soil_right, 256 + x * 32, 448 + j * 32);
		}

		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				gc.drawImage(soil, 256 + i * 32, 448 + j * 32);
				gc.drawImage(hole, 257 + i * 32, 413 + j * 32);
			}
		}
	}
	
	/**Draw a selected plant in the given position
	 * 
	 * @param window get the actual window to draw on it
	 * @param a the x pos for the plant
	 * @param b the y pos for the plant
	 * @param type what type of plant to draw
	 */
	public void draw_plant(Stage window, int a, int b, String type) {
		String url = "";
		switch(type) {
			case "paradicsom1": url = "Pics/Environment/Garden/Plants/paradicsom1.png"; break;
			case "paradicsom2": url = "Pics/Environment/Garden/Plants/paradicsom2.png"; break;
			case "paradicsom3": url = "Pics/Environment/Garden/Plants/paradicsom3.png"; break;
			case "paradicsom4": url = "Pics/Environment/Garden/Plants/paradicsom4.png"; break;
			case "kukorica1": url = "Pics/Environment/Garden/Plants/kukorica1.png"; break;
			case "kukorica2": url = "Pics/Environment/Garden/Plants/kukorica2.png"; break;
			case "kukorica3": url = "Pics/Environment/Garden/Plants/kukorica3.png"; break;
			case "kukorica4": url = "Pics/Environment/Garden/Plants/kukorica4.png"; break;
			default: break;
		}
		
		//0,0-ra alakítva a mátrix kezdése
		a -= 8;
		b -= 14;
		
		Image plant = new Image(url);
		
		//Elso palanta koordinatai: 258, 408
		gc.drawImage(plant, 258 + a * 32, 408 + b * 32);
		
        window.show();
	}
	
	/**This function calls the controller class functions
	 * 
	 * @param player to use the players data
	 * @param x the position of the first tile
	 * @param y the position of the first tile
	 * @param environment with this can the controller class call to draw
	 * @param window get the actual window to draw on it
	 */
	public void smart_garden(Player player, int x, int y, Environment environment, Stage window) {
		Controller controller = new Controller();
		GridPane kert = controller.garden(player, x, y, environment, window);
		root.getChildren().addAll(kert);
		
		controller.growcycle(player, window, this);
	}
	
	/**Redraw the garden by the players stored data
	 * 
	 * @param window get the actual window to draw on it
	 * @param player to use the players data
	 */
	public void draw_garden(Stage window, Player player) {
		int x = 0;
		int y = 0;
		
		switch(player.getdiff()) {
			case 1: x = 5; y = 5; break;
			case 2: x = 10; y = 8; break;
			case 3: x = 20; y = 8; break;
		}
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				if(player.get_plant(i, j) != null) {
					this.draw_plant(window, j + 8, i + 14, player.get_plant(i, j).get_name() + player.get_plant(i, j).get_growth_level());
				}
			}
		}
	}
}

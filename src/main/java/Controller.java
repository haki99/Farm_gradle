import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
public class Controller {
	/** Makes the garden clickable, and sets the ContextMenu for the player. The player with this can plant and harvest.
	 * 
	 * @param player to use the players data
	 * @param x the first tiles position
	 * @param y the first tiles position
	 * @param environment with this can the function draw elements
	 * @param window the actual used window
	 * @return returns the gridpane which is drawed to the screen
	 */
	public GridPane garden(Player player, int x, int y, Environment environment, Stage window) {
		
		x += 8;
		y += 14;

		GridPane kert = new GridPane();
		
		//segéd matrix
		kert.setGridLinesVisible(false);
		
		for (int i = 0; i < 37; i++) {
	         ColumnConstraints column = new ColumnConstraints(32);
	         kert.getColumnConstraints().add(column);
	     }
		
		for (int i = 0; i < 24; i++) {
	         RowConstraints row = new RowConstraints(32);
	         kert.getRowConstraints().add(row);
	     }
		
		ContextMenu menu = new ContextMenu();
        MenuItem par_ultetes_menu = new MenuItem("Paradicsom ültetés");
		MenuItem kuk_ultetes_menu = new MenuItem("Kukorica ültetés");
        MenuItem aratas = new MenuItem("Aratás");
        menu.getItems().addAll(par_ultetes_menu, kuk_ultetes_menu, aratas);
		
		for (int i = 8 ; i < x ; i++) {
            for (int j = 14; j < y; j++) {
            	int a = i;
            	int b = j;
            	Pane cell = new Pane();
                cell.setOnMouseClicked(e -> {
                	e.consume();
                	
            		int u = a - 8;
            		int z = b - 14;
            		
            		if(player.get_plant(z, u) == null) {

            			par_ultetes_menu.setOnAction(evt -> {
               		
                			String type = "paradicsom1";
                		
                			environment.draw_plant(window, a, b, type);
                		
                			Plant p = new Plant("paradicsom", 5, 4);
                		
                			player.add_plant(p, z, u);
                		});
						kuk_ultetes_menu.setOnAction(evt -> {

							String type = "kukorica1";

							environment.draw_plant(window, a, b, type);

							Plant p = new Plant("kukorica", 5, 4);

							player.add_plant(p, z, u);
						});
            		}
            		else if(player.get_plant(z, u).get_growth_level() == player.get_plant(z, u).get_maxgrowth()){
            			aratas.setOnAction(evt -> {
            				evt.consume();
            				
            				player.remove_plant(z, u);
            				
            				int l = 0;
            				int k = 0;
            				
            				switch(player.getdiff()) {
            					case 1: l = 5; k = 5; break;
            					case 2: l = 10; k = 8; break;
            					case 3: l = 20; k = 8; break;
            				}
            				
            				environment.draw_soil(window, player, l, k);
            				environment.draw_garden(window, player);
            			});
            		}
            		
                    menu.show(cell, e.getScreenX(), e.getScreenY());
                });
                kert.add(cell, a, b);
            }
        }

		return kert;
	}
	
	/**The cycle which grows the plants and increase their growth level
	 * 
	 * @param player to use the players data
	 * @param window the actual window
	 * @param e the used environment
	 */
	public void growcycle(Player player, Stage window, Environment e) {
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			
			player.grow_plants();
			player.check_grow(window, e);
			
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}

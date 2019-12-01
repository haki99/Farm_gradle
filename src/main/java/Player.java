import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int diff;
	ArrayList<ArrayList<Plant>> garden_matrix; 
	private int a;
	private int b;
	
	/**Sets the datas of the player
	 * 
	 * @param n name of the player
	 * @param d the chosen difficulty
	 * @param x the size of the garden
	 * @param y the size of the garden
	 */
	public void set_data(String n, int d, int x, int y) {
		name = n;
		diff = d;
		a = y;
		b = x;
		garden_matrix = new ArrayList<ArrayList<Plant>>(); 
		
		for(int i = 0; i < a; i++) {
			garden_matrix.add(new ArrayList<Plant>() );
			for(int j = 0; j < b; j++) {
				garden_matrix.get(i).add(null);
			}
		}
	}
	
	/**Returns the chosen difficulty
	 * 
	 * @return the chosen difficulty
	 */
	public int getdiff() {
		return diff;
	}
	
	/**Returns the player's name
	 * 
	 * @return the player's name
	 */
	public String getname() {
		return name;
	}
	
	/**gets the plant from the given cell
	 * 
	 * @param i coordinate of the cell
	 * @param j coordinate of the cell
	 * @return returns the plant
	 */
	public Plant get_plant(int i, int j) {
		return garden_matrix.get(i).get(j);
	}
	
	/**Removes the plant from the given position
	 * 
	 * @param i the position fo the plant
	 * @param j the position fo the plant
	 */
	public void remove_plant(int i, int j) {
		garden_matrix.get(i).set(j, null);
	}
	
	/**Adds a plant to the given cell
	 * 
	 * @param p the plant
	 * @param i the position
	 * @param j the position
	 */
	public void add_plant(Plant p, int i, int j) {
		garden_matrix.get(i).set(j, p);
	}
	
	/**Grows the planted plants in the garden
	 * 
	 */
	public void grow_plants() {
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				if(garden_matrix.get(i).get(j) != null) {
					if(this.get_plant(i, j).get_growth_level() < this.get_plant(i, j).get_maxgrowth()) {
						this.get_plant(i, j).grow();
					}
				}
			}
		}
	}
	
	/**Checks if the plant is growed enough, if yes then draw the next stage on it
	 * 
	 * @param window the actual used window
	 * @param e the enviroment to draw
	 */
	public void check_grow(Stage window, Environment e) {
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				if(garden_matrix.get(i).get(j) != null) {
					if(this.get_plant(i, j).get_growth_level() < this.get_plant(i, j).get_maxgrowth()) {
						if(this.get_plant(i, j).get_growth() >= this.get_plant(i, j).get_grow_time()) {
							
							this.get_plant(i, j).erase_growth();
							this.get_plant(i, j).incr_growthlevel();
							
							int x = i + 14;
							int y = j + 8;
							String url = "";

							if(this.get_plant(i, j).get_name().contains("paradicsom")){
								switch (this.get_plant(i, j).get_growth_level()){
									case 2:url = "paradicsom2"; break;
									case 3:url = "paradicsom3"; break;
									case 4:url = "paradicsom4"; break;
								}
							}

							if(this.get_plant(i, j).get_name().contains("kukorica")){
								switch (this.get_plant(i, j).get_growth_level()){
									case 2:url = "kukorica2"; break;
									case 3:url = "kukorica3"; break;
									case 4:url = "kukorica4"; break;
								}
							}
							e.draw_plant(window, y, x, url);
						}
					}
				}
			}
		}
	}
	
	/**Serialize the player class
	 * 
	 */
	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("farm_save");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
		}
		catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

	/*Checks if the plants are growed since the last save
	*
	 */
	public void grow_since(){
		File file = new File("farm_save");

		if(file.lastModified() < System.currentTimeMillis()){
			long elapsed_time = System.currentTimeMillis() - file.lastModified();
			elapsed_time = elapsed_time/10000;
			for(int i = 0; i < a; i++) {
				for (int j = 0; j < b; j++) {
					if (garden_matrix.get(i).get(j) != null) {
						for(int x = 0; x < elapsed_time; x++){
							if(garden_matrix.get(i).get(j).get_growth_level() < 4){
								garden_matrix.get(i).get(j).incr_growthlevel();
							}
						}
					}
				}
			}
		}
	}
	

	/**Write out the player class ArrayList for test purposes
	* 
	*/
	public void write_array() {
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				if(garden_matrix.get(i).get(j) != null) System.out.print("[" + garden_matrix.get(i).get(j).get_growth() + "]");
				else  System.out.print("[" + "]");
			}
			System.out.println();
		}
		System.out.println();
	}
}

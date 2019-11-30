import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.stage.Stage;

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
	
	/**
	 * 
	 * @param n
	 * @param d
	 * @param x
	 * @param y
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
	
	/**
	 * 
	 * @return
	 */
	public int getdiff() {
		return diff;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getname() {
		return name;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Plant get_plant(int i, int j) {
		return garden_matrix.get(i).get(j);
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void remove_plant(int i, int j) {
		garden_matrix.get(i).set(j, null);
	}
	
	/**
	 * 
	 * @param p
	 * @param i
	 * @param j
	 */
	public void add_plant(Plant p, int i, int j) {
		garden_matrix.get(i).set(j, p);
	}
	
	/**
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
	
	/**
	 * 
	 * @param window
	 * @param e
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
							
							switch (this.get_plant(i, j).get_growth_level()){
								case 2:url = "paradicsom2"; break;
								case 3:url = "paradicsom3"; break;
								case 4:url = "paradicsom4"; break;
							}
							e.draw_plant(window, y, x, url);
						}
					}
				}
			}
		}
	}
	
	/**
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
	

	/**
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
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		result = prime * result + diff;
		result = prime * result + ((garden_matrix == null) ? 0 : garden_matrix.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		if (diff != other.diff)
			return false;
		if (garden_matrix == null) {
			if (other.garden_matrix != null)
				return false;
		} else if (!garden_matrix.equals(other.garden_matrix))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

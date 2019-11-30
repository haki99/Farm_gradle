import java.io.Serializable;

public class Plant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int growth;
	private int grow_time;
	private int growth_level;
	private int max_growth;
	
	public Plant(String n, int t, int u) {
		name = n;
		grow_time = t;
		max_growth = u;
		growth = 0;
		growth_level = 1;
	}
	
	public String get_name() {
		return name;
	}
	
	public int get_grow_time() {
		return grow_time;
	}
	
	public int get_growth() {
		return growth;
	}
	
	public int get_maxgrowth() {
		return max_growth;
	}
	
	public int get_growth_level() {
		return growth_level;
	}
	
	public void incr_growthlevel() {
		growth_level += 1;
	}
	
	public void erase_growth() {
		growth = 0;
	}
	
	public void grow() {
		growth += 1;
	}
	
	@Override
    public String toString() {
		return name + " " + growth + " " + grow_time + " " + growth_level + " " + max_growth;
	}
}

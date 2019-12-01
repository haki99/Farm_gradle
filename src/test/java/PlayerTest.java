import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
class PlayerTest {

	/**
	 * Tests the initialize of the array. (If not empty then throws fail)
	 */
	@Test
	@DisplayName("Player null test")
	void nulltest() {
		Player p = new Player();
		
		p.set_data("test", 1, 5, 5);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(p.get_plant(i, j) == null);
				else fail("The garden is not empty!");
			}
		}
	}

	/**
	 * Tests that the players garden is plantable
	 */
	@Test
	@DisplayName("Player test with plants - planting")
	void playerplantingtest() {
		Player p = new Player();
		Plant plant = new Plant("anyad", 1, 1);
		p.set_data("test", 1, 5, 5);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i % 2 == 0 && j % 2 == 0)p.add_plant(plant, i, j);
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i % 2 == 0 && j % 2 == 0 && p.get_plant(i, j) == null) fail("The garden is not filled up with plants correctly!");
			}
		}
	}

	/**
	 * Tests the plants growing
	 */
	@Test
	@DisplayName("Player test with plants - growing")
	void playergrowingtest() {
		Player p = new Player();
		Plant plant = new Plant("anyad", 1, 1);
		p.set_data("test", 1, 5, 5);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i % 2 == 0 && j % 2 == 0)p.add_plant(plant, i, j);
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i % 2 == 0 && j % 2 == 0) p.grow_plants();
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(p.get_plant(i, j) != null) {
					if(p.get_plant(i, j).get_growth_level() < p.get_plant(i, j).get_maxgrowth()) {
						if(p.get_plant(i, j).get_growth_level() != 2) fail("The garden is not growed correctly!");
					}
				}
			}
		}
	}

	/**
	 * Tests the serialization of the player class
	 */
	@Test
	@DisplayName("Player save test")
	void playersavetest() {
		Player p = new Player();
		Plant plant = new Plant("anyad", 1, 1);
		p.set_data("test", 1, 5, 5);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i % 2 == 0 && j % 2 == 0)p.add_plant(plant, i, j);
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i % 2 == 0 && j % 2 == 0) p.grow_plants();
			}
		}
		
		p.save();
		
		File file = new File("d:/STORE/Egyetem/Java programs/Farm/farm_save");
		if(!file.exists()) fail("The player is not saved!");
	}
}
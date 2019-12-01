import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * @author Hofi
 * @version 1.0
 * @since   2019-11-06
 */
class PlantTest {

	/**
	 * Tests that a plant grows
	 */
	@Test
	@DisplayName("Plant Grow Test")
	void plantgrowtest() {
		Plant plant = new Plant("plant", 10, 4);
		
		for(int i = 0; i < 3; i++) {
			plant.incr_growthlevel();
		}
		
		if(plant.get_growth_level() != 4) fail("The plant is not growth as expected!");
	}

	/**
	 * Tests that a plants growth is erasable
	 */
	@Test
	@DisplayName("Plant Erase Growth Test")
	void planterasegrowthtest() {
		Plant plant = new Plant("plant", 10, 4);
		
		for(int i = 0; i < 3; i++) {
			plant.incr_growthlevel();
		}
		
		plant.erase_growth();
		
		if(plant.get_growth() != 0) fail("The plant's growth is not erased well!");
	}
}

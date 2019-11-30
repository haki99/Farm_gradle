import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlantTest {

	@Test
	@DisplayName("Plant Grow Test")
	void plantgrowtest() {
		Plant plant = new Plant("plant", 10, 4);
		
		for(int i = 0; i < 3; i++) {
			plant.incr_growthlevel();
		}
		
		if(plant.get_growth_level() != 4) fail("The plant is not growth as expected!");
	}
	
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

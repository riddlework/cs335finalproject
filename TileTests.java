import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TileTests {

	Tile exampleTile;
	Tile exampleTile2;

	@BeforeEach
	void setUp() throws Exception {
		exampleTile = new Tile('Q', 123);
		exampleTile2 = new Tile();
	}


	// tests letter getter
	@Test
	void testGetLetter() {
		assertEquals(exampleTile.getLetter(), 'Q');
		
	}

	// tests points getter
	@Test
	void testGetPoints() {
		assertEquals(exampleTile.getPoints(), 123);
	}

}

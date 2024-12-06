import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DrawPileTests {

	DrawPile testPile;
	DrawPile empty;
	
	@BeforeEach
	void setUp() throws Exception {
			
		testPile = new DrawPile();
		empty = new DrawPile();
		
		for (int i=0; i<98; i++) {
			empty.drawTile();
		}
		
	}

	/*
	 * tests the functionality of drawing one tile from the pile
	 * checks both empty/non-empty piles
	 */
	@Test
	void testDrawTile() {
		assertDoesNotThrow(() -> testPile.drawTile());
		assertThrows(InvalidDrawException.class, () -> empty.drawTile());
	}
	
	
	/*
	 * tests the functionality of drawing multiple tiles from the pile
	 * checks drawing from list regularly / verifies size
	 * checks drawing from empty list
	 * checks drawing too many tiles from list
	 */
	@Test
	void testDrawTiles() throws InvalidDrawException {
		assertDoesNotThrow(() -> testPile.drawTiles(4));
		assertEquals(testPile.drawTiles(4).size(), 4);
		assertThrows(InvalidDrawException.class, () -> empty.drawTiles(2));
		assertThrows(InvalidDrawException.class, () -> testPile.drawTiles(10));
	}
	
	
	/*
	 * tests the get size method on empty/non-empty piles
	 */
	@Test
	void testGetSize() {
		assertEquals(testPile.getSize(), 98);
		assertEquals(empty.getSize(), 0);
	}
	
	
	@Test
	void testAddTiles() {
		assertEquals(testPile.getSize(), 98);
		
		ArrayList<Tile> list = new ArrayList<>();
		list.add(new Tile('!', 1));
		list.add(new Tile('@', 2));
		list.add(new Tile('#', 3));
		
		testPile.addTiles(list);
		assertEquals(testPile.getSize(), 101);
	}
	
	
	
	
	

}

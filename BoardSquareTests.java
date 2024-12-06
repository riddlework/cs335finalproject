import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardSquareTests {

	BoardSquare b1;

	@BeforeEach
	void setUp() throws Exception {
		b1 = new BoardSquare();
	}

	
	
	@Test
	void testSetType() {
		assertEquals(b1.getType(), squareType.NONE);
		b1.setType(squareType.DOUBLE_LETTER);
		assertEquals(b1.getType(), squareType.DOUBLE_LETTER);
	}
	
	@Test
	void testSetTile() {
		assertEquals(b1.getTile(), null);
		Tile t = new Tile('T', 100);
		b1.setTile(t);
		assertNotEquals(b1.getTile(), null);
	}
	
	@Test
	void testRemoveTile() {
		Tile t = new Tile('T', 100);
		b1.setTile(t);
		assertNotEquals(b1.getTile(), null);
		b1.removeTile();
		assertEquals(b1.getTile(), null);
	}
	
	@Test
	void testReadTile() {
		Tile t = new Tile('T', 100);
		b1.setTile(t);
		assertNotEquals(b1.getTile(), null);
		Tile result = b1.readTile();
		assertNotEquals(b1.getTile(), null);
		assertEquals(result, t);
	}
	
	@Test
	void testHasTile() {
		assertEquals(b1.hasTile(), false);
		b1.setTile(new Tile('T', 100));
		assertEquals(b1.hasTile(), true);
	}
	
	@Test
	void testGetScore() {
		b1.setType(squareType.DOUBLE_LETTER);
		b1.setTile(new Tile('T', 100));
		assertEquals(b1.getScore(), 200);
		
		b1.setType(squareType.TRIPLE_LETTER);
		assertEquals(b1.getScore(), 300);
		assertEquals(b1.getScore(), 100);
	}
	
	
	@Test
	void testGetLetter() {
		b1.setTile(new Tile('T', 100));
		assertEquals(b1.getLetter(), 'T');
	}
	
	

}

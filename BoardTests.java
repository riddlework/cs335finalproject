import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTests {

	Board board;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		board = new Board(); 
		
		
	}

	@Test
	void testAddTile() {
		assertNull(board.getBoardSquare(3, 2).getTile());
		board.addTile(new Tile('T', 10) , 3, 2);
		assertNotNull(board.getBoardSquare(3, 2).getTile());
	}
	
	
	@Test
	void testRemoveTile() {
		board.addTile(new Tile('T', 10), 5, 5);
		assertNotNull(board.getBoardSquare(5, 5).getTile());
		board.removeTile(5, 5);
		assertNull(board.getBoardSquare(5, 5).getTile());
	}
	
	
	@Test
	void testReadTile() {
		Tile t = new Tile('T', 5);
		board.addTile(t, 7, 9);
		assertEquals(board.readTile(7, 9), t);
	}
	
	
	@Test 
	void testGetScore() {
		board.addTile(new Tile('T', 123), 12, 14);
		assertEquals(board.getScore(12, 14), 123);
	}

}

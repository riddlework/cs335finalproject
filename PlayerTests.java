
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTests {

	Player player1;
	Player player2;
	Player player3;
	DrawPile testPile;

	@BeforeEach
	void setUp() throws Exception {
		testPile = new DrawPile();

		ArrayList<Tile> hand1 = testPile.drawTiles(7);
		ArrayList<Tile> hand2 = testPile.drawTiles(2);
		ArrayList<Tile> emptyHand = testPile.drawTiles(0);
		

		player1 = new Player("Alice", hand1);
		player2 = new Player("Bob", hand2);
		player3 = new Player("Chris", emptyHand);
	}


	/*
	 * tests the functionality of adding a new tile to a players hand
	 * checks both the case where the players hand is already full and when it is not
	 */
	@Test
	void testNewTile() throws InvalidDrawException {

		// players hand is full
		Tile newTile = testPile.drawTile();
		assertThrows(InvalidHandException.class, () -> player1.newTile(newTile));

		// players hand is not full
		assertEquals(player2.getSize(), 2);
		assertDoesNotThrow(() -> player2.newTile(newTile));
		assertEquals(player2.getSize(), 3);
	}


	/*
	 * checks that the size getting method returns the proper size of the players hand
	 */
	@Test
	void testGetSize() {
		assertEquals(player1.getSize(), 7);
		assertEquals(player2.getSize(), 2);
		assertEquals(player3.getSize(), 0);
	}


	@Test
	void testPrintHand() {
		player1.printHand();
	}
	
	@Test
	void testShuffle() {
		ArrayList<Tile> one = player1.getHand();
		player1.shuffleHand();
		ArrayList<Tile> two = player1.getHand();
		assertNotEquals(one.get(0).getLetter(), two.get(0).getLetter());
	}

	
	@Test
	void testGetScore() {
		assertEquals(player1.getScore(), 0);
		player1.setScore(100);
		assertEquals(player1.getScore(), 100);
	}
	
	

}

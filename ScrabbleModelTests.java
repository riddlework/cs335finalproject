import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScrabbleModelTests {

	ScrabbleModel model; 
	
	@BeforeEach
	void setUp() throws Exception {
		model = new ScrabbleModel();
	}

	
	
	@Test
	void testPlayerTurnHorizontal() {
		
		HashMap<Point, Character> details = new HashMap<>();
		details.put(new Point(7, 7), 'a');
		details.put(new Point(8, 7), 'b');
		details.put(new Point(9, 7), 'd');
		details.put(new Point(10, 7), 'o');
		details.put(new Point(11, 7), 'm');
		details.put(new Point(12, 7), 'e');
		details.put(new Point(13, 7), 'n');
		
		ArrayList<Tile> explicitHand = new ArrayList<>();
		explicitHand.add(new Tile('a', 1));
		explicitHand.add(new Tile('b', 1));
		explicitHand.add(new Tile('d', 1));
		explicitHand.add(new Tile('o', 1));
		explicitHand.add(new Tile('m', 1));
		explicitHand.add(new Tile('e', 1));
		explicitHand.add(new Tile('n', 1));
		
		model.setPlayerHand(explicitHand);
		model.setStartP1();
		model.playerTurn(details);
		
		assertEquals(model.getCurPlayerScore(), 8);
		assertEquals(model.getWinner(), 1);
	}

	
	@Test
	void testPlayerTurnVertical() {
		
		HashMap<Point, Character> details = new HashMap<>();
		details.put(new Point(7, 7),'a');
		details.put(new Point(7, 8), 'b');
		details.put(new Point(7, 9), 'd');
		details.put(new Point(7, 10),'o');
		details.put(new Point(7, 11),'m');
		details.put(new Point(7, 12),'e');
		details.put(new Point(7, 13),'n');
		
		ArrayList<Tile> explicitHand = new ArrayList<>();
		explicitHand.add(new Tile('a', 1));
		explicitHand.add(new Tile('b', 1));
		explicitHand.add(new Tile('d', 1));
		explicitHand.add(new Tile('o', 1));
		explicitHand.add(new Tile('m', 1));
		explicitHand.add(new Tile('e', 1));
		explicitHand.add(new Tile('n', 1));
		
		model.setPlayerHand(explicitHand);
		model.setStartP1();
		model.playerTurn(details);
	}
	
	
	@Test
	void testPlayerTurnSingle() {
		
		HashMap<Point, Character> details = new HashMap<>();
		details.put(new Point(7, 7),'a');
	
		ArrayList<Tile> explicitHand = new ArrayList<>();
		explicitHand.add(new Tile('a', 1));
		explicitHand.add(new Tile('d', 1));
		explicitHand.add(new Tile('d', 1));
		explicitHand.add(new Tile('o', 1));
		explicitHand.add(new Tile('m', 1));
		explicitHand.add(new Tile('e', 1));
		explicitHand.add(new Tile('n', 1));
		
		model.setPlayerHand(explicitHand);
		model.setStartP1();
		model.playerTurn(details);
	}
	
	
	
	@Test 
	void testSwitchPlayers() {
		Player first = model.getCurPlayer();
		model.switchPlayers();
		Player second = model.getCurPlayer();
		assertNotEquals(first, second);
		
	}
	
	
	@Test 
	void testShuflle() {
		ArrayList<Tile> oghand = model.getPlayerHand(model.getCurPlayer());
		model.shufflePlayerHand();
		ArrayList<Tile> newhand = model.getPlayerHand(model.getCurPlayer());
		assertNotEquals(oghand.get(0).getLetter(), newhand.get(0).getLetter());
	}
	
	@Test
	void testGetPlayerScores() {
		assertEquals(model.getPlayerOneScore(), 0);
		assertEquals(model.getPlayerTwoScore(), 0);
	}
	
}

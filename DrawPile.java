/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: DrawPile.java
 * Course: CSC335
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A class that represents the drawpile--contains all the letters in the game of Scrabble
 * with methods to draw without replacement and to swap tiles
 */
public class DrawPile {

	// instance variables
	private	ArrayList<Tile> pool;

	/**
	 * Initialize the drawpile--add all the necessary letters according to the rules
	 */
	public DrawPile() {
		
		pool = new ArrayList<Tile>();
		HashMap<Character, Integer> initQ = new HashMap<>();
		HashMap<Character, Integer> initP = new HashMap<>();
		
		// create a HashMap of the quantities of each letter
		initQ.put('A', 9);
		initQ.put('B', 2);
		initQ.put('C', 2);
		initQ.put('D', 4);
		initQ.put('E', 12);
		initQ.put('F', 2);
		initQ.put('G', 3);
		initQ.put('H', 2);
		initQ.put('I', 9);
		initQ.put('J', 1);
		initQ.put('K', 1);
		initQ.put('L', 4);
		initQ.put('M', 2);
		initQ.put('N', 6);
		initQ.put('O', 8);
		initQ.put('P', 2);
		initQ.put('Q', 1);
		initQ.put('R', 6);
		initQ.put('S', 4);
		initQ.put('T', 6);
		initQ.put('U', 4);
		initQ.put('V', 2);
		initQ.put('W', 2);
		initQ.put('X', 1);
		initQ.put('Y', 2);
		initQ.put('Z', 1);
		
		// create a HaskMap of the points of each letter
		initP.put('A', 1);
		initP.put('B', 3);
		initP.put('C', 3);
		initP.put('D', 2);
		initP.put('E', 1);
		initP.put('F', 4);
		initP.put('G', 2);
		initP.put('H', 4);
		initP.put('I', 1);
		initP.put('J', 8);
		initP.put('K', 5);
		initP.put('L', 1);
		initP.put('M', 3);
		initP.put('N', 1);
		initP.put('O', 1);
		initP.put('P', 3);
		initP.put('Q', 10);
		initP.put('R', 1);
		initP.put('S', 1);
		initP.put('T', 1);
		initP.put('U', 1);
		initP.put('V', 4);
		initP.put('W', 4);
		initP.put('X', 8);
		initP.put('Y', 4);
		initP.put('Z', 10);
		
		//iterate over each key in the quantities and add the proper amount of new tiles
		for (Character key : initQ.keySet()) {
			Integer value = initQ.get(key);
		    for (int i = 0; i<value; i++) {
		    	Tile curTile = new Tile(key, initP.get(key));
		    	pool.add(curTile);
		    }
		}

		// shuffle the draw pile
		Collections.shuffle(pool);
	}

	/**
	 * Return the number of tiles in the drawpile
	 * @return An integer--the number of tiles in the drawpile
	 */
	public int getSize() { return pool.size(); }

	/**
	 * removes one tile and returns it to the player that called the method
	 * @pre player must have less than 7 tiles in their hand
	 * @return the first tile object in the shuffled list
	 */
	public Tile drawTile() throws InvalidDrawException {
		
		if (pool.isEmpty()) throw new InvalidDrawException("Not enough Tiles left.");
		
		// get the first slot of the ArrayList to return
		Tile retTile = pool.remove(0);
		
		//move each tile down one index slot
		for (int i=0; i<pool.size()-1; i++) {
			pool.add(i, pool.remove(i+1));
		}
		
		return retTile;
	}
	
	/**
	 * removes x tiles and gives them to the player that called the method
	 * @pre cannot pass a number greater than what the player can hold
	 * @param x = number of tiles to return
	 * @returns an ArrayList of x tiles
	 */
	public ArrayList<Tile> drawTiles(int x) throws InvalidDrawException {
		
		ArrayList<Tile> retTiles = new ArrayList<Tile>();
		
		// throw if there are not enough tiles left
		if (x > pool.size()) throw new InvalidDrawException("Not enough Tiles left.");
		
		// throw if player tries to draw more than 7 tiles
		else if (x > 7) throw new InvalidDrawException("Hand can only hold 7 tiles.");
		
		else {
			
			// add the proper number of tiles to the return list
			for (int i=0; i<x; i++) {
				retTiles.add(pool.get(i));
			}
			
			//move each tile down one index slot
			for (int i=0; i < pool.size() - x; i++) {pool.add(i, pool.remove(i+x));}					
		}
		
		return retTiles;
	}

	/**
	 * Add tiles back to the draw pile from a players hand
	 * @param tiles The Tile objects to add back into the draw pile
	 */
	public void addTiles(ArrayList<Tile> tiles) {
		for (Tile t: tiles) pool.add(t);
	}
}

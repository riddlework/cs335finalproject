import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/*
 * TO ANY GROUP MEMBERS READING THIS CODE, I HAVE NOT FINISHED THIS CLASS OR TESTED THESE METHODS SO 
 * DO NOT MOVE FORWARD WITH THIS CODE. I INTEND TO MAKE MORE IMPROVEMENTS ASAP. I AM JUST PUSHING THESE 
 * SO THAT YOU CAN SEE WHAT I HAVE BEEN WORKIG ON AND YOU DONT WASTE YOUR TIME REPEATING WORK
 * 
 *  :)
 */

/*
 * Author(s): Ben Yurek ... add names here
 * File: DrawPile.java
 * Course: csc335
 * Description: draw pile class
 */

public class DrawPile {

	// instance variables
	private		ArrayList<Tile>					pool;
	
	/*
	 * constructor 
	 */
	public DrawPile() {
		
		HashMap<Character, Integer> initQ = new HashMap<>();
		HashMap<Character, Integer> initP = new HashMap<>();
		
		// create a HashMap of the quantities of each letter
		initQ.put('a', 9);
		initQ.put('b', 2);
		initQ.put('c', 2);
		initQ.put('d', 4);
		initQ.put('e', 12);
		initQ.put('f', 2);
		initQ.put('g', 3);
		initQ.put('h', 2);
		initQ.put('i', 9);
		initQ.put('j', 1);
		initQ.put('k', 1);
		initQ.put('l', 4);
		initQ.put('m', 2);
		initQ.put('n', 6);
		initQ.put('o', 8);
		initQ.put('p', 2);
		initQ.put('q', 1);
		initQ.put('r', 6);
		initQ.put('s', 4);
		initQ.put('t', 6);
		initQ.put('u', 4);
		initQ.put('v', 2);
		initQ.put('w', 2);
		initQ.put('x', 1);
		initQ.put('y', 2);
		initQ.put('z', 1);
		
		// create a HaskMap of the points of each letter
		initP.put('a', 1);
		initP.put('b', 3);
		initP.put('c', 3);
		initP.put('d', 2);
		initP.put('e', 1);
		initP.put('f', 4);
		initP.put('g', 2);
		initP.put('h', 4);
		initP.put('i', 1);
		initP.put('j', 8);
		initP.put('k', 5);
		initP.put('l', 1);
		initP.put('m', 3);
		initP.put('n', 1);
		initP.put('o', 1);
		initP.put('p', 3);
		initP.put('q', 10);
		initP.put('r', 1);
		initP.put('s', 1);
		initP.put('t', 1);
		initP.put('u', 1);
		initP.put('v', 4);
		initP.put('w', 4);
		initP.put('x', 8);
		initP.put('y', 4);
		initP.put('z', 10);
		
		//iterate over each key in the quantities and add the proper amount of new tiles
		for (Character key : initQ.keySet()) {
			Integer value = initQ.get(key);
		    for (int i = 0; i<value; i++) {
		    	Tile curTile = new Tile(key, initP.get(key));
		    	pool.add(curTile);
		    }
		}
	}	
	
	
	
	/*
	 * removes one tile and gives it to the player that called the method
	 * @Returns: the first tile in the list
	 * 
	 * @Post : cannot call unless the player hand has an empty slot
	 */
	public Tile drawTile() throws InvalidDrawException {
		
		if(pool.isEmpty()) throw new InvalidDrawException("Not enough Tiles left.");
		
		// get the first slot of the ArrayList to return
		Tile retTile = pool.remove(0);
		
		//move each tile down one index slot
		for (int i=0; i<pool.size(); i++) {
			pool.add(i, pool.remove(i+1));
		}
		
		return retTile;
	}
	
	
	
	/*
	 * removes x tiles and gives them to the player that called the method
	 * @Param: x = number of tiles to return
	 * @Returns: an ArrayList of x tiles
	 * 
	 * @Pre : the cannot pass a number greater than what the player can hold
	 */
	public ArrayList<Tile> drawTile(int x) throws InvalidDrawException {
		
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
	
	
	
	/*
	 * shuffle the draw pile 
	 * probably will never use this method
	 */
	public void shufflePile() {
		Collections.shuffle(pool);
	}
	
	
	
	/*
	 * return the number of tiles remaining to the client code
	 */
	public int getSize() {
		return pool.size();
	}
	
	
	/*
	 * print the contents of the draw pile in order
	 * 
	 * for the purposes of testing the above methods and maybe other things
	 */
	public void dumpBag() {
		for (int i=0; i<pool.size(); i++) {
			Tile cur = pool.get(i);
			System.out.println(i + ".   Letter : " + cur.getLetter() + "   Points   : " + cur.getPoints());
		}
	}
	
	
	
	
	
	
	
	
	
}

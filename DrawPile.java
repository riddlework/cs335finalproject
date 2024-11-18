import java.util.ArrayList;
import java.util.Collections;


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
	private ArrayList<Tile> pool;
	private int size;
	
	// constructor
	public DrawPile(int tileQuantity) {
		
		size = tileQuantity;
		
		for (int i=0; i<tileQuantity; i++) {
			
			// add a random tile?
			// have a specific number of each? could use some sort 
			// of dictionary to know what we need to add  
	
		}
	}	
	
	
	/*
	 * removes one tile and gives it to the player that called the method
	 */
	public Tile drawTile() {
		
		Tile retTile = pool.remove(0);
		
		//move each tile down one index slot
		for (int i=0; i<size; i++) {
			pool.add(i, pool.remove(i+1));
		}
		
		return retTile;
	}
	
	/*
	 * removes x tiles and gives them to the player that called the method
	 */
	public ArrayList<Tile> drawTile(int x) {
		
		ArrayList<Tile> retTiles = new ArrayList<Tile>();
		
		// probably want to create another exception for these
		if (x > size) {
			System.out.println("not enough tiles left in the draw pile");
		}
		else if (x > 7) { 
			System.out.println("too many tiles for your hand");
		}
		else {
			for (int i=0; i<x; i++) {
				retTiles.add(pool.get(i));
			}
		
			//move each tile down one index slot
			for (int i=0; i<size; i++) {
				
				//implement the interior of this loop
				
			}
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
		return size;
	}
	
	
}

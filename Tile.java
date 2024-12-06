
/*
 * TO ANY GROUP MEMBERS READING THIS CODE, I HAVE NOT FINISHED THIS CLASS OR TESTED THESE METHODS SO 
 * DO NOT MOVE FORWARD WITH THIS CODE. I INTEND TO MAKE MORE IMPROVEMENTS ASAP. I AM JUST PUSHING THESE 
 * SO THAT YOU CAN SEE WHAT I HAVE BEEN WORKIG ON AND YOU DONT WASTE YOUR TIME REPEATING WORK
 * 
 *  :)
 */



/*
 * Author(s): Ben Yurek ... add names here
 * File: Tile.java
 * Course: csc335
 * Description: tile object class
 */


final class Tile {

	// instance variables
	// both fields are primitive types so can be declared public
	private char letter;
	private int points; 
	
	// empty tile constructor
	public Tile() {
		
	}
	
	// constructor
	public Tile(char c, int p) {
		letter = c;
		points = p;
	}
	
	// return this tile's letter
	public char getLetter() { return letter; }
	
	// return this tile's point value
	public int getPoints() { return points; }
	
	
}



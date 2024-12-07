/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: Tile.java
 * Course: CSC335
 */

/**
 * This class represents a scrabble tile--has a letter and a point
 */
final class Tile {

	// instance variables
	private char letter;
	private int points; 
	

	/**
	 * A constructor -- empty because there is nothing to initialize
	 */
	public Tile() {
		
	}

	/**
	 * Create a new Tile object
	 * @param c The letter that the tile represents
	 * @param p The integer point value of the tile
	 */
	public Tile(char c, int p) {
		letter = c;
		points = p;
	}
	
	/**
	 * return the tile's letter--a char
	 */
	public char getLetter() { return letter; }

	/**
	 * return the integer point value of the tile
	 * @return an integer--the point value of the tile
	 */
	public int getPoints() { return points; }
}



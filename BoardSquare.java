/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: BoardSquare.java
 * Course: CSC335
 */

/**
 * A class that represents a BoardSquare--has a Tile and a score type
 */
public class BoardSquare {
    // instance variables
    private Tile tile = null;
    private squareType type;

   
    // constructor

    /**
     * create a new BoardSquare object with type as NONE
     */
    public BoardSquare() { type = squareType.NONE; }

    /**
     * Set the type of the BoardSquare
     * @param type The score type
     */
    public void setType(squareType type) { this.type = type; }
    
    /**
     * Add a tile to the boardsquare
     * @param tile The tile object to be added
     */
    public void setTile(Tile tile) { this.tile = tile; }
    
    /**
     * Remove the tile from the boardsquare and return it
     * @return A Tile object--that which was removed from the boardsquare
     */
    public Tile removeTile() {
    	Tile temp = tile;
    	tile = null;
    	return temp;
    }
    
    /**
     * Read the tile off of the boardsquare
     * @return The tile object belonging to the boardSquare
     */
    public Tile readTile()  { return tile; }

    /**
     * Check to see if the boardsquare has a tile
     * @return a boolean--true if the boardsquare has a tile and false otherwise
     */
    public boolean hasTile() {
    	if (tile != null) return true;
    	return false;
    }

    /**
     * return the score associated with the boardsquare, factoring in score type
     * @pre boardsquare must have a tile
     * @post returns the score with the multiplier factored in (if any)
     * @return an integer--the score associated with the boardsquare
     */
    public int getScore() {   	
    	assert(hasTile());
 
    	int score = 0; 
    	if (type == squareType.DOUBLE_LETTER) {
    		score += (2*tile.getPoints());
    		type = squareType.NONE;
    	} else if (type == squareType.TRIPLE_LETTER) {
    		score += (3*tile.getPoints());
    		type = squareType.NONE;
    	} else {
    		score += tile.getPoints();
    	} return score;
    }

    /**
     * Return the letter that the boardsquare has
     * @pre boardsquare must have a tile
     * @post returns the char that the tile associate with the boardsquare has
     * @return a char--the letter that the tile associated with the boardsquare has
     */
    public char getLetter() {
        assert(hasTile());
        return this.tile.getLetter();
    }
    
}


/**
 * An enumeration detailing the different possibilities for score type
 */
enum squareType {
    NONE,
    CENTER,
    DOUBLE_LETTER,
    TRIPLE_LETTER;
}



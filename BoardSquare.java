import java.util.Optional;

public class BoardSquare {
    private Tile tile = null;
    private boolean canPlace;
    private squareType type;

   
    // constructor
    public BoardSquare() {
        type = squareType.NONE;
    }
    
    // sets the point multiplier type
    public void setType(squareType type) {
    	this.type = type;
    }
    
    //adds tile to the board at this coordinate
    public void setTile(Tile tile) {
    	this.tile = tile;
    }
    
    // removes tile and returns it
    public Tile removeTile() {
    	Tile temp = tile;
    	tile = null;
    	return temp;
    }
    
    //returns tile without removing
    public Tile readTile() {
    	return tile;
    }

    //returns boolean if there is a tile on this square
    public boolean hasTile() {
    	if (tile != null) return true;
    	return false;
    }
    
    
    /**
     * @pre : has a tile.
     * @post : will return the score with the multiplier added
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

    public char getLetter() {
        assert(hasTile());
        return this.tile.getLetter();
    }
    
}



enum squareType {
    NONE,
    CENTER,
    DOUBLE_LETTER,
    TRIPLE_LETTER;
}



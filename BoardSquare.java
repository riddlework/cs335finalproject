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
    
}



enum squareType {
    NONE,
    CENTER,
    DOUBLE_LETTER,
    TRIPLE_LETTER;
}



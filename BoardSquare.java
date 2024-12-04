import java.util.Optional;

public class BoardSquare {
    private Optional<Tile> tile;
    private boolean canPlace;
    private squareType type;

   
    // constructor
    public BoardSquare() {
        tile = Optional.empty();
        type = squareType.NONE;
    }
    
    // sets the point multiplier type
    public void setType(squareType type) {
    	this.type = type;
    }
    
    //adds tile to the board at this coordinate
    public void setTile(Optional<Tile> tile) {
    	this.tile = tile;
    }
    
    // removes tile and returns it
    public Optional<Tile> removeTile() {
    	Optional<Tile> temp = tile;
    	tile = Optional.empty();
    	return temp;
    }
    
    //returns tile without removing
    public Optional<Tile> readTile() {
    	return tile;
    }

}



enum squareType {
    NONE,
    CENTER,
    DOUBLE_LETTER,
    TRIPLE_LETTER;
}



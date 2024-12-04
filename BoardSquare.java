import java.util.Optional;

public class BoardSquare {
    private Optional<Tile> tile;
    private boolean canPlace;
    private squareType type;

   

    public BoardSquare() {
        tile = Optional.empty();
        type = squareType.NONE;
    }
    
    
    public void setType(squareType type) {
    	this.type = type;
    }
    
    
    public void setTile(Optional<Tile> tile) {
    	this.tile = tile;
    }
    
    
    public Optional<Tile> removeTile() {
    	Optional<Tile> temp = tile;
    	tile = Optional.empty();
    	return temp;
    }
    

    public char getLetter() {
        if (tile.isPresent()) return tile.get().getLetter();
        else return ' ';
    }


}



enum squareType {
    NONE,
    CENTER,
    DOUBLE_LETTER,
    TRIPLE_LETTER;
}



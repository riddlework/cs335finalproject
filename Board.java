import java.util.ArrayList;
import java.util.Optional;

public class Board {
	
	// Instance variables 
    private ArrayList<ArrayList<BoardSquare>> board;

    
    // Constructor
    public Board() {
        
        // Create 15 rows in the Board
        board = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ArrayList<BoardSquare> boardRow = new ArrayList<>();
            for (int j=0; i<15; i++) {
            	BoardSquare sq = new BoardSquare();
            	boardRow.add(sq);
            }
            board.add(boardRow);
        }
        
        // Add the BoardSquare types
        board.get(0).get(3).setType(squareType.DOUBLE_LETTER);
        board.get(0).get(11).setType(squareType.DOUBLE_LETTER);
        board.get(2).get(6).setType(squareType.DOUBLE_LETTER);
        board.get(3).get(0).setType(squareType.DOUBLE_LETTER);
        board.get(3).get(7).setType(squareType.DOUBLE_LETTER);
        board.get(3).get(14).setType(squareType.DOUBLE_LETTER);
        board.get(6).get(2).setType(squareType.DOUBLE_LETTER);
        board.get(6).get(6).setType(squareType.DOUBLE_LETTER);
        board.get(6).get(8).setType(squareType.DOUBLE_LETTER);
        board.get(6).get(12).setType(squareType.DOUBLE_LETTER);
        board.get(7).get(2).setType(squareType.DOUBLE_LETTER);
        board.get(7).get(6).setType(squareType.DOUBLE_LETTER);
        board.get(7).get(8).setType(squareType.DOUBLE_LETTER);
        board.get(7).get(12).setType(squareType.DOUBLE_LETTER);
        board.get(11).get(0).setType(squareType.DOUBLE_LETTER);
        board.get(11).get(7).setType(squareType.DOUBLE_LETTER);
        board.get(11).get(14).setType(squareType.DOUBLE_LETTER);
        board.get(12).get(6).setType(squareType.DOUBLE_LETTER);
        board.get(12).get(8).setType(squareType.DOUBLE_LETTER);
        board.get(14).get(3).setType(squareType.DOUBLE_LETTER);
        board.get(14).get(11).setType(squareType.DOUBLE_LETTER);
         
        board.get(1).get(5).setType(squareType.TRIPLE_LETTER);
        board.get(1).get(9).setType(squareType.TRIPLE_LETTER);
        board.get(5).get(1).setType(squareType.TRIPLE_LETTER);
        board.get(5).get(5).setType(squareType.TRIPLE_LETTER);
        board.get(5).get(9).setType(squareType.TRIPLE_LETTER);
        board.get(5).get(13).setType(squareType.TRIPLE_LETTER);
        board.get(9).get(1).setType(squareType.TRIPLE_LETTER);
        board.get(9).get(5).setType(squareType.TRIPLE_LETTER);
        board.get(9).get(9).setType(squareType.TRIPLE_LETTER);
        board.get(9).get(13).setType(squareType.TRIPLE_LETTER);
        board.get(13).get(5).setType(squareType.TRIPLE_LETTER);
        board.get(13).get(9).setType(squareType.TRIPLE_LETTER);
    }

    
    // add tile to the board
    public void addTile(Optional<Tile> tile, int x, int y) {
    	board.get(x).get(y).setTile(tile);
    }
    
    //remove tile from the board
    public Optional<Tile> removeTile(int x, int y) {
    	return board.get(x).get(y).removeTile();
    }
    
    
    public Optional<Tile> readTile(int x, int y) {
    	return board.get(x).get(y).readTile();
    }
    
    
    
}

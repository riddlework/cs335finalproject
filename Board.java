/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: Board.java
 * Course: CSC335
 */
import java.util.ArrayList;

/**
 * This class represents the Scrabble board. It contains all the boardsquares
 * and handles all the special scoring.
 */
public class Board {
	
	// Instance variables 
    private ArrayList<ArrayList<BoardSquare>> board;

    
    // Constructor

    /**
     * Initialize the board--create the 2D ArrayList of BoardSquare objects
     * and modify the squares with special score types.
     */
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
        // double letter squares
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

        // triple letter squares
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

        // center square
        board.get(7).get(7).setType(squareType.CENTER);
    }

    /**
     * Add a tile to the board.
     * @param tile The Tile object to be added.
     * @param x An integer--the x-coordinate at which the tile will be added
     * @param y An integer--the y-coordinate at which the tile will be added
     */
    public void addTile(Tile tile, int x, int y) { board.get(x).get(y).setTile(tile); }
    
    /**
     * Remove a tile from the board and return it
     * @param x An integer--the x-coordinate at which the tile will be removed
     * @param y An integer--the y-coordinate at which the tile will be removed
     * @return A tile object--the tile that was removed
     */
    public Tile removeTile(int x, int y) { return board.get(x).get(y).removeTile(); }

    /**
     * Return the tile object at the given coordinate
     * @param x An integer--the x-coordinate of the tile to be returned
     * @param y An integer--the y-coordinate of the tile to be returned
     * @return The tile object at the given coordinate
     */
    public Tile readTile(int x, int y) { return board.get(x).get(y).readTile(); }

    /**
     * Return the BoardSquare object at the given coordinate
     * @param x An integer--the x-coordinate of the BoardSquare to be returned
     * @param y An integer--the y-coordinate of the BoardSquare to be returned
     * @return The BoardSquare object at the given coordinate
     */
    public BoardSquare getBoardSquare(int x, int y) { return board.get(x).get(y); }


    /**
     * Return the score of the BoardSquare object at the given coordinate
     * @param x An integer -- the x-coordinate of the BoardSquare to be scored
     * @param y An integer -- the y-coordinate of the BoardSquare to be scored
     * @return The score of the BoardSquare object at the given coordinate
     */
    public int getScore(int x, int y) { return board.get(x).get(y).getScore(); }
}

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<BoardSquare>> board;


    public Board() {
        // create board
        board = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ArrayList<BoardSquare> boardRow = new ArrayList<>();
            board.add(boardRow);
        }
    }

}

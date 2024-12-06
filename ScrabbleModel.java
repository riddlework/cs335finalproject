import java.util.ArrayList;

public class ScrabbleModel {
    private Board board;
    private Player p1;
    private Player p2;
    private DrawPile drawPile;
    private ArrayList<String> dictionary;

    public ScrabbleModel() {
        // initialize dictionary
        board = new Board();
        try {
            p1 = Player("p1", drawPile.drawTiles(7));
            p2 = Player("p2", drawPile.drawTiles(7));
        } catch (InvalidDrawException e) {

        }

    }

    private void initDictionary() {

    }

    // methods:
    // validateWord
    // placeLetter
    //


    public void validateWords(int x1, int y1, int x2, int y2) throws InvalidPlacementException {
        ArrayList<String> words = new ArrayList<>();
        // check whether the given string is valid
        if (x1 == x2) {
            // vertical word
            String vWord = "";
            int vScore = 0;
            for (int i = y1; i <= y2 ; i++) {
                BoardSquare bs = board.getBoardSquare(x1,i);
                vWord += bs.getLetter();
                vScore += bs.getScore();
            }

        } else {
            // horizontal word

        }

        // remember to calculate the points too
    }

    public void calculateScore(int x1, int y1, int x2, int y2) {

    }

    /**
     *
     * @param x1 horizontal starting index
     * @param y1
     * @param x2
     * @param y2
     * @param op
     */
    public void findWords(int x1, int y1, int x2, int y2, boolean op) { return; }


    public static void main(String[] args) {
        // initialize dictionary
    }

    private enum WordOrientation {
        VERTICAL,
        HORIZONTAL;
    }

    private enum Operation {
        VALIDATE_WORD,
        CALCULATE_SCORE;
    }
}


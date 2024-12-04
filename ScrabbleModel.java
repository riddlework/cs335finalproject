import java.util.ArrayList;

public class ScrabbleModel {
    private Board board;
    private Player p1;
    private Player p2;
    private DrawPile drawPile;

    public ScrabbleModel() {
        board = new Board();
        try {
            p1 = Player("p1", drawPile.drawTiles(7));
            p2 = Player("p2", drawPile.drawTiles(7));
        } catch (InvalidDrawException e) {

        }

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
            for (int i = y1; i <= y2 ; i++) {
                Tile tile = board.readTile(x1,i);
                vWord += board.readTile(x1,i)
            }

        } else {
            // horizontal word

        }

        // remember to calculate the points too
    }

    public static void main(String[] args) {

    }
}

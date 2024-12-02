public class ScrabbleModel {
    private Board board;
    private Player p1;
    private Player p2;
    private DrawPile drawPile;

    public ScrabbleModel() {
        board = new Board();
        p1 = Player("p1", drawPile.drawTiles(7));
        p2 = Player("p2", drawPile.drawTiles(7));
    }

    // methods:
    // validateWord
    // placeLetter
    //

    public static void main(String[] args) {

    }
}

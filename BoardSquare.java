import java.util.Optional;

public class BoardSquare {
    private Optional<Tile> tile;

    public BoardSquare() {
        tile = Optional.empty();
    }

    public char getLetter() {
        if (tile.isPresent()) return tile.getLetter();
        else return ' ';
    }


}

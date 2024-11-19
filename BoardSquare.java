import java.util.Optional;

public class BoardSquare {
    private Optional<Tile> tile;
    private boolean canPlace;
    private squareType squareType;

    private enum squareType {
        NONE,
        CENTER,
        DOUBLE_LETTER,
        TRIPLE_LETTER;
    }

    public BoardSquare() {
        tile = Optional.empty();
    }

    public char getLetter() {
        if (tile.isPresent()) return tile.get().getLetter();
        else return ' ';
    }


}

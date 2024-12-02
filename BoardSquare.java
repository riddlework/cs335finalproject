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

    public BoardSquare(squareType type) {
        tile = Optional.empty();
        type = squareType;
    }

    public char getLetter() {
        if (tile.isPresent()) return tile.get().getLetter();
        else return ' ';
    }


}

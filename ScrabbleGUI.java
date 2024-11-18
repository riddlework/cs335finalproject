// Lucas Dargert

public class ScrabbleGUI {

    // private static final boolean textBasedInterface = false;
    // private TileBag tb = new TileBag();   (Stack?)
    // private Board board = new Board();   (2D Array)

    public ScrabbleGUI() {
        // Player 1 given a hand (ArrayList) of 7 tiles, set to visible
        // Player 2 given a hand (ArrayList) of 7 tiles, set to invisible
        // End game counter = 0;

        // Board is definied, each square clickable to select
        // If square selected, buttons for each Tile in hand are visible
        // If button clicked, tile will be placed
        // Validity of placement checked on end turn button

        
    }

    // private void endTurn() {
        // Check number of tiles selected
        // If 0 and end game counter == 1, end game
        // If 0 and end game counter == 0, set end game counter to 1

        // For each tile placed :
        //      Concatenate all values of each row and column into individual strings
        //      Split them by " "
        //      Check each word in provided dictionary
        //      If doesn't exist, exit loop
        //      Print invalid placement
        //      Return to current player's turn
        //
        // If all valid placements, get score of each word added
        // Add to current player's score
        // Make current player's hand invisible
        // Make waiting player's hand visible
    // }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScrabbleGUI::new);
    }
}

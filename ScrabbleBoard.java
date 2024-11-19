

import javax.swing.*;
import java.awt.*;

public class ScrabbleBoard extends JPanel {
    private static final int BOARD_SIZE = 15;
    private JButton[][] squares = new JButton[BOARD_SIZE][BOARD_SIZE];

    public ScrabbleBoard() {
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initializeBoard();
        setPreferredSize(new Dimension(600, 600));
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setOpaque(true);
                setDefaultSquareColor(row, col);
                add(squares[row][col]);
            }
        }
    }
    public  void setDefaultSquareColor(int row , int col) {
    	// Triple Word Score squares
    	if ((row == 0 || row == 7 || row == 14) && (col == 0  || col == 14)) {
    	    squares[row][col].setBackground(Color.RED); // Red
    	}
    	// Double Word Score squares
    	else if (!(row >= 5 && row <= 9)&&(row == col || row + col == 14 )||(row == 7 && col == 7)) {
    	    squares[row][col].setBackground(Color.PINK); // Pink
    	}
    	else if ((row == 5 || row ==9 )&&(row == col || row + col == 14 )) {
    	    squares[row][col].setBackground(Color.BLUE); // Pink
    	}
    	// Triple Letter Score squares
    	else if ((row == 1 || row == 13) && (col == 5 || col == 9) || 
    	         (row == 5 || row == 9) && (col == 1 || col == 13)) {
    	    squares[row][col].setBackground(Color.BLUE); // Blue
    	}
    	// Double Letter Score squares
    	else if ((row == 3 || row == 11 || row==7) && (col == 0 || col == 7 || col == 14 || col==3||col==11) || 
    	         (row == 6 || row == 8) && (col == 2 || col == 6 || col == 8 || col == 12) || 
    	         (row == 0 || row == 14) && (col == 3 || col == 11) || 
    	         (row == 2 || row == 12) && (col == 6 || col == 8)
    	         
    			) {
    	    squares[row][col].setBackground(Color.CYAN); // Light Blue
    	}
    	// Regular squares
    	else {
    	    squares[row][col].setBackground(Color.WHITE); // White
    	}

    	
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scrabble");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create main board
        ScrabbleBoard board = new ScrabbleBoard();
        frame.add(board, BorderLayout.CENTER);

        // Create the player rack panel
        JPanel rackPanel = new JPanel();
        rackPanel.setLayout(new GridLayout(1, 7)); // 7 tiles for each player
        for (int i = 0; i < 7; i++) {
            JButton tile = new JButton(" ");
            tile.setFont(new Font("Arial", Font.BOLD, 16));
            rackPanel.add(tile);
        }
        frame.add(rackPanel, BorderLayout.SOUTH);

        // Create the side panel for game controls and logs
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(200, 600)); // Set width of side panel

        // Control buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 1)); // 4 rows, 1 column, with gaps
        
        JButton submitButton = new JButton("Submit Word");
        JButton shuffleButton = new JButton("Shuffle Rack");
        JButton skipTurnButton = new JButton("Skip Turn");
        JButton endGameButton = new JButton("End Game");

        // Add padding to buttons
        submitButton.setMargin(new Insets(10, 10, 10, 10));
        shuffleButton.setMargin(new Insets(10, 10, 10, 10));
        skipTurnButton.setMargin(new Insets(10, 10, 10, 10));
        endGameButton.setMargin(new Insets(10, 10, 10, 10));

        controlPanel.add(submitButton);
        controlPanel.add(shuffleButton);
        controlPanel.add(skipTurnButton);
        controlPanel.add(endGameButton);

        // Add some padding around the control panel
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        sidePanel.add(controlPanel, BorderLayout.NORTH);

        // Add a game log area (optional)
        JTextArea gameLog = new JTextArea();
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
        gameLog.setWrapStyleWord(true);
        // I am thinking if i have to make it scrollable depends on the content that would 
        //be in it as of game status score updates and stuff.
        gameLog.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        
        sidePanel.add(gameLog, BorderLayout.CENTER);
        
        // Add score panel (optional)
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2, 1));
        scorePanel.add(new JLabel("Player 1 Score: "));
        scorePanel.add(new JLabel("Player 2 Score: "));
        sidePanel.add(scorePanel, BorderLayout.SOUTH);

        // Add side panel to frame
        frame.add(sidePanel, BorderLayout.EAST);

        // Pack and display
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}
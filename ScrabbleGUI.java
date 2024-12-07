package leetcode;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Collections;

  // Tracks if it's the first turn of the game

public class ScrabbleGUI extends JPanel {
	private static boolean isFirstTurn = true;
    private static final int BOARD_SIZE = 15;
    private static JButton[][] squares = new JButton[BOARD_SIZE][BOARD_SIZE];
    private static JButton[] rack = new JButton[7];
    private static JButton selectedTile = null;
    private static HashMap<Point, Character> justPlacedTiles = new HashMap<Point, Character>();
    private static Stack<Point> justPlacedPoints = new Stack<Point>();
    private static JPanel rackPanel;
    private static JTextArea gameLog;
    private static JPanel scorePanel;
    private static ArrayList<Tile> currentPlayerHand = new ArrayList<Tile>();
    private static boolean currentPlayer;
    

    public ScrabbleGUI() {
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
                squares[row][col].setEnabled(false);
                if (row == 7 && col == 7) {
                	squares[row][col].setEnabled(true);
                	squares[row][col].setBackground(Color.red);
                	
                }
                JButton temp = squares[row][col];
                Point newPoint = new Point(row, col);
                temp.addActionListener(e -> {
                	if (selectedTile != null) {
                		temp.setText(selectedTile.getText());
                    	setSurroundingTiles(squares, temp);
                    	justPlacedTiles.put(newPoint, selectedTile.getText().charAt(0));
                    	justPlacedPoints.add(newPoint);
                    	selectedTile.setEnabled(false);
                    	
                    	selectedTile = null;
                	}
                });
            }
        }
    }
    

    public void setDefaultSquareColor(int row, int col) {
        if ((row == 1 || row == 13) && (col == 5 || col == 9) || 
            (row == 5 || row == 9) && (col == 1 || col == 13)) {
            squares[row][col].setBackground(Color.BLUE);
        }
        else if ((row == 3 || row == 11 || row==7) && (col == 0 || col == 7 || col == 14 || col==3||col==11) || 
                 (row == 6 || row == 8) && (col == 2 || col == 6 || col == 8 || col == 12) || 
                 (row == 0 || row == 14) && (col == 3 || col == 11) || 
                 (row == 2 || row == 12) && (col == 6 || col == 8)) {
            squares[row][col].setBackground(Color.CYAN);
        }
        else {
            squares[row][col].setBackground(Color.WHITE);
        }
    }
    

    public static void updateGameLog(String message) {
        gameLog.append(message + "\n");
    }
    

    public static void updateScores(int player1Score, int player2Score) {
        Component[] components = scorePanel.getComponents();
        if (components[0] instanceof JLabel) {
            ((JLabel)components[0]).setText("Player 1 Score: " + player1Score);
        }
        if (components[1] instanceof JLabel) {
            ((JLabel)components[1]).setText("Player 2 Score: " + player2Score);
        }
    }
    
    
    private static void setSurroundingTiles(JButton[][] grid, JButton b ) {
    	b.setEnabled(false);
    	for (int i = 0; i < 15; i++) {
    		for (int j = 0; j < 15; j++) {
    			if (grid[i][j] == b) {
    				enableAdjacentSquares(grid, i, j);
    			}
    		}
    	}
    }
    
    
    private static void checkWord(HashMap<Point, Character> justPlacedTiles, Stack<Point> justPlacedPoints, JButton[][] squares, boolean tester) {
    	if (tester) {   // condition will be playerTurn(justPlacedTiles)
    		justPlacedTiles.clear();
    		justPlacedPoints.clear();
    		// Change to other player
    	}
    	else {
    		while (!justPlacedPoints.isEmpty()) {
    			Point p = justPlacedPoints.pop();
                int x = (int) p.getX();
                int y = (int) p.getY();
                resetSquare(squares, x, y);
    		}
    		for (int i = 0; i < 15; i++) {
    			for (int j = 0; j < 15; j++) {
    				if (!squares[i][j].getText().equals("")) {
                        enableAdjacentSquares(squares, i, j);
                    }
    			}
    		}
    	}
    }
    
    
 // Helper method to reset a square (clear text and enable it)
    private static void resetSquare(JButton[][] squares, int x, int y) {
        squares[x][y].setText("");
        squares[x][y].setEnabled(true);
        disableAdjacentSquares(squares, x, y);
    }
    

    // Helper method to disable adjacent squares when needed
    private static void disableAdjacentSquares(JButton[][] squares, int x, int y) {
        if (x != 0 && squares[x - 1][y].getText().equals("")) {
            squares[x - 1][y].setEnabled(false);
        }
        if (x != 14 && squares[x + 1][y].getText().equals("")) {
            squares[x + 1][y].setEnabled(false);
        }
        if (y != 0 && squares[x][y - 1].getText().equals("")) {
            squares[x][y - 1].setEnabled(false);
        }
        if (y != 14 && squares[x][y + 1].getText().equals("")) {
            squares[x][y + 1].setEnabled(false);
        }
    }
    

    // Helper method to re-enable adjacent squares when needed
    private static void enableAdjacentSquares(JButton[][] squares, int x, int y) {
        if (x != 0 && squares[x - 1][y].getText().equals("")) {
            squares[x - 1][y].setEnabled(true);
        }
        if (x != 14 && squares[x + 1][y].getText().equals("")) {
            squares[x + 1][y].setEnabled(true);
        }
        if (y != 0 && squares[x][y - 1].getText().equals("")) {
            squares[x][y - 1].setEnabled(true);
        }
        if (y != 14 && squares[x][y + 1].getText().equals("")) {
            squares[x][y + 1].setEnabled(true);
        }
    }
    
    
    private static void setHand() {
    	for (int i = 0; i < 7; i++) {
    		rack[i].setText(String.valueOf(currentPlayerHand.get(i).getLetter()));
    	}
    }
    

    public static void main(String[] args) {
    	ScrabbleModel model = new ScrabbleModel();
    	ScrabbleController controller = new ScrabbleController(model);
    	ScrabbleGUI gui = new ScrabbleGUI();
        JFrame frame = new JFrame("Scrabble");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gui, BorderLayout.CENTER);

        
        JPanel rackPanel = new JPanel();
        rackPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            JButton tile = new JButton(" ");
            tile.setFont(new Font("Arial", Font.BOLD, 16));
            rackPanel.add(tile);
            rack[i] = tile;
            tile.addActionListener(e -> {
            	selectedTile = tile;
            });
            
        }
        frame.add(rackPanel, BorderLayout.SOUTH);
        gui.rackPanel = rackPanel;
        currentPlayerHand = controller.getCurPlayerHand();
    	setHand();
    	

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(200, 600));
        

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 1));
        
        JButton submitButton = new JButton("Submit Word");
        submitButton.addActionListener(e -> {
        	checkWord(justPlacedTiles, justPlacedPoints, squares, controller.playerTurn(justPlacedTiles));
        	updateScores(controller.getPlayerOneScore(), controller.getPlayerTwoScore());
        	for (JButton b : rack) {
        		b.setEnabled(true);
        	}
        	controller.switchPlayers();
            currentPlayerHand = controller.getCurPlayerHand();
            setHand();
        });
  
        JButton shuffleButton = new JButton("Shuffle Rack");
        shuffleButton.addActionListener(e -> {
        	controller.shufflePlayerHand();
        	Collections.shuffle(currentPlayerHand);
        	setHand();
        });
        
        JButton skipTurnButton = new JButton("Skip Turn");
        skipTurnButton.addActionListener(e -> {
        	controller.switchPlayers();
        	currentPlayerHand = controller.getCurPlayerHand();
        	setHand();
        });
        
        JButton endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e -> {
        	int getWinner = controller.getWinner();
        	if (getWinner == 0) {
        		updateGameLog("The game has ended in a tie!");
        	}
        	if (getWinner == 1) {
        		updateGameLog("Player 1 Wins!");
        	}
        	if (getWinner == 2) {
        		updateGameLog("Player 2 Wins!");
        	}
        	for (int i = 0; i < 15; i++) {
        		for (int j = 0; j < 15; j++) {
        			squares[i][j].setEnabled(false);
        		}
        	}
        	for (int i = 0; i < 7; i++) {
        		rack[i].setEnabled(false);
        	}
        	shuffleButton.setEnabled(false);
        	submitButton.setEnabled(false);
        	skipTurnButton.setEnabled(false);
        	endGameButton.setEnabled(false);	
        });
        
        submitButton.setMargin(new Insets(10, 10, 10, 10));
        shuffleButton.setMargin(new Insets(10, 10, 10, 10));
        skipTurnButton.setMargin(new Insets(10, 10, 10, 10));
        endGameButton.setMargin(new Insets(10, 10, 10, 10));

        controlPanel.add(submitButton);
        controlPanel.add(shuffleButton);
        controlPanel.add(skipTurnButton);
        controlPanel.add(endGameButton);

        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidePanel.add(controlPanel, BorderLayout.NORTH);

        JTextArea gameLog = new JTextArea();
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
        gameLog.setWrapStyleWord(true);
        gameLog.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        sidePanel.add(gameLog, BorderLayout.CENTER);
        gui.gameLog = gameLog;
        
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2, 1));
        scorePanel.add(new JLabel("Player 1 Score: "));
        scorePanel.add(new JLabel("Player 2 Score: "));
        sidePanel.add(scorePanel, BorderLayout.SOUTH);
        gui.scorePanel = scorePanel;

        frame.add(sidePanel, BorderLayout.EAST);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

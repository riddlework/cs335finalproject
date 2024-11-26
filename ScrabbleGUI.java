package leetcode;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;


public class ScrabbleGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Scrabble Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
 
        
        
        
        
        List<Integer> tripleScore = new ArrayList<>();
        int[] t = {0, 7, 14, 20, 24, 76, 80, 84, 88, 105, 119, 136, 
        			140, 144, 148, 200, 204, 210, 217, 224};
        for (int i : t) {
        	tripleScore.add(i);
        }
        
        List<Integer> doubleScore = new ArrayList<>();
        int[] d = {3, 11, 16, 28, 32, 36, 38, 42, 45, 48, 52, 56, 59, 
        			64, 70, 92, 96, 98, 102, 108, 116, 122, 126, 128, 
        			132, 154, 160, 165, 168, 172, 176, 179, 182, 186,
        			188, 192, 196, 208, 213, 221};
        for (int i : d) {
        	doubleScore.add(i);
        }
        
        // Create the board panel (15x15 grid)
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(15, 15));
        JButton[][] squares = new JButton[15][15];
        for (int i = 0; i < 15 * 15; i++) {
            JButton square = new JButton();
            if (tripleScore.contains(i)) {
            	square.setBackground(Color.RED); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                square.setEnabled(false);
                boardPanel.add(square);
                squares[i / 15][i % 15] = square;
                square.addActionListener(e -> {
                	square.setText("C");
                	setSurroundingTiles(squares, square);
                });
            }
            else if (doubleScore.contains(i)) {
            	square.setBackground(Color.CYAN); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                square.setEnabled(false);
                boardPanel.add(square);
                squares[i / 15][i % 15] = square;
                square.addActionListener(e -> {
                	square.setText("C");
                	setSurroundingTiles(squares, square);
                });
            }
            else if (i == 112) {
            	square.setBackground(Color.MAGENTA); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                square.setEnabled(true);
                boardPanel.add(square);
                squares[i / 15][i % 15] = square;
                square.addActionListener(e -> {
                	square.setText("C");
                	setSurroundingTiles(squares, square);
                });
            }
            else {
            	square.setBackground(Color.WHITE); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                square.setEnabled(false);
                boardPanel.add(square);
                squares[i / 15][i % 15] = square;
                square.addActionListener(e -> {
                	square.setText("C");
                	setSurroundingTiles(squares, square);
                });
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);

        // Create the player rack panel
        
        JPanel rackPanel = new JPanel();
        rackPanel.setLayout(new GridLayout(1, 7)); // 7 tiles for each player
        for (int i = 0; i < 7; i++) {
            JButton tile = new JButton("C");
            tile.setFont(new Font("Arial", Font.BOLD, 16));
            rackPanel.add(tile);
            
        }
        frame.add(rackPanel, BorderLayout.SOUTH);

        // Create the side panel for game controls and logs
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());

        // Control buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 1, 5, 5));
        JButton submitButton = new JButton("Submit Word");
        JButton shuffleButton = new JButton("Shuffle Rack");
        JButton skipTurnButton = new JButton("Skip Turn");
        JButton endGameButton = new JButton("End Game");
        controlPanel.add(submitButton);
        controlPanel.add(shuffleButton);
        controlPanel.add(skipTurnButton);
        controlPanel.add(endGameButton);

        sidePanel.add(controlPanel, BorderLayout.NORTH);
        
        submitButton.addActionListener(e -> {
            // Example: Handling the submission of a word
            String word = JOptionPane.showInputDialog("Enter your word:");
            if (word == null || word.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No word entered!");
                return;
            }
            
            // Send word to the Controller for validation
            System.out.println("Submitting word: " + word); // Placeholder for integration
            // Example of connecting with teammates' code
            // controller.submitWord(word);
        });


        // Game log area
        JTextArea gameLog = new JTextArea(10, 20);
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
        gameLog.setWrapStyleWord(true);
        JScrollPane logScrollPane = new JScrollPane(gameLog);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("Game Log"));
        sidePanel.add(logScrollPane, BorderLayout.CENTER);

        frame.add(sidePanel, BorderLayout.EAST);

        // Show the frame
        frame.setVisible(true);
    }
    
    private static void setSurroundingTiles(JButton[][] grid, JButton b ) {
    	b.setEnabled(false);
    	for (int i = 0; i < 15; i++) {
    		for (int j = 0; j < 15; j++) {
    			if (grid[i][j] == b) {
    				if (i != 0) {
    					if (grid[i-1][j].getText().equals("")) {
    						grid[i-1][j].setEnabled(true);
    					}
    				}
    				if (i != 14) {
    					if (grid[i+1][j].getText().equals("")) {
    						grid[i+1][j].setEnabled(true);
    					}
    				}
    				if (j != 0) {
    					if (grid[i][j-1].getText().equals("")) {
    						grid[i][j-1].setEnabled(true);
    					}
    				}
    				if (j != 14) {
    					if (grid[i][j+1].getText().equals("")) {
    						grid[i][j+1].setEnabled(true);
    					}
    				}
    			}
    		}
    	}
    }
    
    
   

}

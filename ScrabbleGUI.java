package leetcode;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;


public class ScrabbleGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Scrabble Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        // Create the board panel (15x15 grid)
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(15, 15));
        for (int i = 0; i < 15 * 15; i++) {
            JButton square = new JButton();
            square.setBackground(Color.LIGHT_GRAY); // Default square color
            square.setFont(new Font("Arial", Font.BOLD, 12));
            boardPanel.add(square);
        }
        frame.add(boardPanel, BorderLayout.CENTER);

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
   

}

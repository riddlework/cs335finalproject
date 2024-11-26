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
 
        
        
        
        
        List<Integer> triple = new ArrayList<>();
        triple.add(0);
        triple.add(7);
        triple.add(14);
        triple.add(20);
        triple.add(24);
        triple.add(76);
        triple.add(80);
        triple.add(84);
        triple.add(88);
        triple.add(105);
        triple.add(119);
        triple.add(136);
        triple.add(140);
        triple.add(144);
        triple.add(148);
        triple.add(200);
        triple.add(204);
        triple.add(210);
        triple.add(217);
        triple.add(224);
        
        List<Integer> doubleScore = new ArrayList<>();
        doubleScore.add(3);
        doubleScore.add(11);
        doubleScore.add(16);
        doubleScore.add(28);
        doubleScore.add(32);
        doubleScore.add(36);
        doubleScore.add(38);
        doubleScore.add(42);
        doubleScore.add(45);
        doubleScore.add(48);
        doubleScore.add(52);
        doubleScore.add(56);
        doubleScore.add(59);
        doubleScore.add(64);
        doubleScore.add(70);
        doubleScore.add(92);
        doubleScore.add(96);
        doubleScore.add(98);
        doubleScore.add(102);
        doubleScore.add(108);
        doubleScore.add(116);
        doubleScore.add(122);
        doubleScore.add(126);
        doubleScore.add(128);
        doubleScore.add(132);
        doubleScore.add(154);
        doubleScore.add(160);
        doubleScore.add(165);
        doubleScore.add(168);
        doubleScore.add(172);
        doubleScore.add(176);
        doubleScore.add(179);
        doubleScore.add(182);
        doubleScore.add(186);
        doubleScore.add(188);
        doubleScore.add(192);
        doubleScore.add(196);
        doubleScore.add(208);
        doubleScore.add(213);
        doubleScore.add(221);
        
        
        // Create the board panel (15x15 grid)
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(15, 15));
        for (int i = 0; i < 15 * 15; i++) {
            JButton square = new JButton();
            if (triple.contains(i)) {
            	square.setBackground(Color.RED); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                boardPanel.add(square);
            }
            else if (doubleScore.contains(i)) {
            	square.setBackground(Color.CYAN); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                boardPanel.add(square);
            }
            else if (i == 112) {
            	square.setBackground(Color.MAGENTA); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                boardPanel.add(square);
            }
            else {
            	square.setBackground(Color.WHITE); // Default square color
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 12));
                boardPanel.add(square);
            }
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

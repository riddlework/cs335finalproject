### SCRABBLE
## CSC 335 FINAL PROJECT

# Authors
Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate

# Overview
In our Scrabble, two players take turns placing tiles bearing single
letters onto a game board divided into a 15x15 grid of squares.

Placed tiles must form words that read left to right or top to bottom.
When a new word is placed, it has to make a word with every touching tile.
If the user places an invalid word, their turn will be skipped and their
placement discarded. If a player wishes to swap their tiles they may do so
at the cost of their turn. A player must always have seven tiles in their
hand.

The players score is the sum of the words they have played, factoring in
any modifiers like double- or triple-letter scores.

The game ends when all letters have been drawn and one player uses
their last letter or all possible plays have been made.

# How to run our code:

Compile the Scrabble game by running `javac *.java`. Run the game
by running `java ScrabbleGUI.java`.

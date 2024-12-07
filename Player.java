/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: Player.java
 * Course: CSC335
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a player in the game of scrabble.
 * They have a hand of tiles, an integer score, and a name.
 */
public class Player {

	// instance variables
	private String name;
	private ArrayList<Tile> hand;
	private int score = 0;

	/**
	 * A constructor--create a new player object
	 * @param name A String--the player's name
	 * @param hand An ArrayList of Tile objects--the player's playable hand
	 */
	public Player(String name, ArrayList<Tile> hand) {
		this.hand = hand;
		this.name = name;
	}

	// getters and setters

	/**
	 * Return a copy of the player's hand
	 * @return An ArrayList of Tile objects--the player's hand
	 */
	public ArrayList<Tile> getHand() {
		ArrayList<Tile> copy = new ArrayList<>();
		for (Tile t : hand) copy.add(t);
		return copy;
	}

	/**
	 * Return the first tile in the player's hand with a letter matching the given parameter
	 * @param c A char--the letter to be matched
	 * @return A Tile object with a letter matching that which was passed
	 */
	public Tile getTileByLetter(char c) {
		for (Tile t : hand) {
			if (t.getLetter() == c) return t;
		}
		return null;
	}

	/**
	 * return the size of the player's hand
	 * @return An integer--the size of the player's hand
	 */
	public int getSize() { return hand.size(); }

	/**
	 * return the player's score
	 * @return an integer--the player's score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Set the player's score
	 * @param score An integer--the new score to be set
	 */
	public void setScore(int score) {
		this.score = score;
	}


	// other methods

	/**
	 * Adds a tile to the player's hand if they have less than 7 tiles
	 * @param tile The Tile object to be added to the player's hand
	 * @throws InvalidHandException Thrown if trying to add to the hand of a player with more than 7 tiles
	 * @throws InvalidDrawException Thrown if the drawpile is empty
	 */
	public void newTile(Tile tile) throws InvalidHandException {

		if (hand.size() >= 7) throw new InvalidHandException(name + "'s hand is already full so a Tile could not be added");

		else hand.add(tile);
	}

	/**
	 * Replace a tile in the player's hand with the tile that was just played
	 * @param c A char--the letter of the tile to be replaced
	 * @param newTile The Tile object which will replace the previous one
	 */
	public void replaceTileByLetter(char c, Tile newTile) {
		for (int i=0; i<hand.size(); i++) {
			if (hand.get(i).getLetter() == c) hand.add(i, newTile);
		}
	}

	/**
	 * Shuffle the tiles in the players hand
	 */
	public void shuffleHand() { Collections.shuffle(hand); }

	/**
	 * Prints a players hand to standard out
	 */
	public void printHand() {
		//iterate over the tiles in that hand and print out the info for each one
		for (int i=0; i<7; i++) {
			Tile cur = hand.get(i);
			System.out.println((i+1) + "Char: " + cur.getLetter() + "     Points: " + cur.getPoints());
		}
	}
}

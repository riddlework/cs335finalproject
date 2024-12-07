import java.util.ArrayList;
import java.util.Collections;


/*
 * TO ANY GROUP MEMBERS READING THIS CODE, I HAVE NOT FINISHED THIS CLASS OR TESTED THESE METHODS SO 
 * DO NOT MOVE FORWARD WITH THIS CODE. I INTEND TO MAKE MORE IMPROVEMENTS ASAP. I AM JUST PUSHING THESE 
 * SO THAT YOU CAN SEE WHAT I HAVE BEEN WORKIG ON AND YOU DONT WASTE YOUR TIME REPEATING WORK
 * 
 *  :)
 */



/*
 * Author(s): Ben Yurek
 * File: Player.java
 * Course: csc335
 * Description: player object class
 */


public class Player {

	// instance variables
	private ArrayList<Tile> hand;
	private int score = 0;
	private boolean isMyTurn;
	private String name;
	
	
	/*
	 * constructor
	 */
	public Player(String name, ArrayList<Tile> hand) {
		this.isMyTurn = false;
		this.hand = hand;
		this.name = name;
	}
	
	
	
	
	/*
	 * adds a tile to the players hand if the length of their hand is less than 7
	 */
	public void newTile(Tile tile) throws InvalidHandException, InvalidDrawException {

		if (hand.size() >= 7) throw new InvalidHandException(name + "'s hand is already full so a Tile could not be added");

		// still need to write the get tile method in the draw pile composite class
		else hand.add(tile);
	}
	
	
	/*
	 * returns the numbers of tiles in the players hand
	 */
	public int getSize() {
		return hand.size();
	}

	
	
	/*
	 * removes a tile from the players hand
	 * 
	 * delete????
	 *
	public Tile playTile(int i) throws InvalidHandException {
		
		// instantiate empty tile to prevent error
		Tile retTile = new Tile();
		
		if (hand.isEmpty()) throw new InvalidHandException("There are no Tiles left in this players hand");
		else {
			retTile = hand.remove(i);
		}
		
		
		return retTile;
	}
	*/
	
	
	
	/*
	 * prints players hand
	 */
	public void printHand() { 
		
		//iterate over the tiles in that hand and print out the info for each one
		for (int i=0; i<7; i++) {
			Tile cur = hand.get(i);
			System.out.println((i+1) + "Char: " + cur.getLetter() + "     Points: " + cur.getPoints());
		}
	}

	/*
	 * returns a copy of the players hand to the caller
	 */
	public ArrayList<Tile> getHand() { 
		
		ArrayList<Tile> copy = new ArrayList<>();
		for (Tile t : hand) copy.add(t);
		return copy; 
	}
	
	/*
	 * returns the first tile with a matching letter to the parameter that was passed
	 */
	public Tile getTileByLetter(char c) {
		for (Tile t : hand) {
			if (t.getLetter() == c) return t;
		}
		return null;
	}
	
	
	/*
	 * iterate through the players hand and replace get a new tile for the tile that was just played
	 */
	public void replaceTileByLetter(char c, Tile newTile) {
		for (int i=0; i<hand.size(); i++) {
			if (hand.get(i).getLetter() == c) hand.add(i, newTile);
		}
	}
	
	
	/*
	 * shuffles the tiles in the players hand 
	 */
	public void shuffleHand() {
		Collections.shuffle(hand);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}
	
	public void setHand(ArrayList<Tile> newHand) {
		hand = newHand;
	}
	
	
}

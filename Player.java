import java.util.ArrayList;


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
	private String name;
	private ArrayList<Tile> hand;
	private int score = 0;
	private boolean isMyTurn;
	
	
	/*
	 * constructor
	 */
	public Player(String n, ArrayList<Tile> hand) throws InvalidDrawException {
		this.name = n;
		this.isMyTurn = false;
		this.hand = hand;
	}
	
	
	
	
//	/*
//	 * adds a tile to the players hand if the length of their hand is less than 7
//	 */
//	public void newTile() throws InvalidHandException, InvalidDrawException {
//
//		if (hand.size() >= 7) throw new InvalidHandException(name + "'s hand is already full so a Tile could not be added");
//
//		// still need to write the get tile method in the draw pile composite class
//		else hand.add(dp.drawTile());
//
//	}
//
	
	
	/*
	 * removes a tile from the players hand
	 */
	public Tile playTile(int i) throws InvalidHandException {
		
		// instantiate empty tile to prevent error
		Tile retTile = new Tile();
		
		if (hand.isEmpty()) throw new InvalidHandException("There are no Tiles left in this players hand");
		else {
			retTile = hand.remove(i);
		}
		
		
		return retTile;
	}
	
	
	
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
	
	
}

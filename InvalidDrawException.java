
/*
 * TO ANY GROUP MEMBERS READING THIS CODE, I HAVE NOT FINISHED THIS CLASS OR TESTED THESE METHODS SO 
 * DO NOT MOVE FORWARD WITH THIS CODE. I INTEND TO MAKE MORE IMPROVEMENTS ASAP. I AM JUST PUSHING THESE 
 * SO THAT YOU CAN SEE WHAT I HAVE BEEN WORKIG ON AND YOU DONT WASTE YOUR TIME REPEATING WORK
 * 
 *  :)
 */


/*
 * Author(s): Ben Yurek ... add names here
 * File: InvalidDrawException.java
 * Course: csc335
 * Description: see comment below
 */


/**
 * An exception to be thrown when the client code tries to call a method
 * on the players hand at a time that the action cannot be performed
 */
class InvalidDrawException extends Exception {
	// constructor that accepts a message
	public InvalidDrawException (String message) {
		super(message);
	}
}
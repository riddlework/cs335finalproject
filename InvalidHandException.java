/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: InvalidHandException.
 * Course: CSC335
 */


/**
 * An exception to be thrown when the client code tries to call a method
 * on the players hand at a time that the action cannot be performed
 */
class InvalidHandException extends Exception {
	// constructor that accepts a message
	public InvalidHandException (String message) {
		super(message);
	}
}

/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: Player.java
 * Course: CSC335
 */

/**
 * An exception to be thrown when an invalid placement is attempted.
 */
public class InvalidPlacementException extends Exception {
    public InvalidPlacementException (String message) {
        super(message);
    }
}

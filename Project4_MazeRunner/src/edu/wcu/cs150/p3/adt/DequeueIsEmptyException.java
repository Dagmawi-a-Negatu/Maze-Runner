package edu.wcu.cs150.p3.adt;

/**

A custom exception class that is thrown when attempting to access an element from an empty Dequeue object.
@Author Dagmawi Negatu
@version May 6 2023
 */
public class DequeueIsEmptyException extends Exception {

	/**

The serial version UID for serialization and deserialization.
	 */
	private static final long serialVersionUID = 1L;
	/**

Constructs a new DequeueIsEmptyException with a default error message "Queue is empty".
	 */
	public DequeueIsEmptyException() {
		System.out.println("Queue is empty");
	}
	/**

Constructs a new DequeueIsEmptyException with the specified error message.
@param message the error message for this exception.
	 */
	public DequeueIsEmptyException(String message) {
		System.out.println(message);
	}
}

package edu.wcu.cs.cs150.p3.mazerunner;

/**
 * Simple exception to inform us when the things going wrong during initialization.
 * @author Dagmawi Negatu
 * @version May 2023
 */

public class InvalidInitException extends Exception {
	
	/**
	 * A requirement of serialization,
	 * something we do not need,
	 * but the Java compiler complains with a warning when  not present.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new Exception with a default message
	 */
	public InvalidInitException() {
		this("Malfunction excpetion during initializiation");
	}
	
	/**
	 * Create a new Exception.
	 * @param message - error method for the exception
	 */
	public InvalidInitException(String message) {
		super(message);
	}
	
}

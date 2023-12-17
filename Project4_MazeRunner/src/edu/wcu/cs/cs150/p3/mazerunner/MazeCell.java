package edu.wcu.cs.cs150.p3.mazerunner;

/**
 * Defines the characters needed for drawing a Maze. 
 * Each one of these enumerated Object types
 * defines a different character.
 * The wall character is █ which is a unicode and
 * allows Java draw a solid rectangle.
 * @author Dagmawi Negatu 
 * @May 6 2023
 */

public enum MazeCell {
	
	
	/**
	 * Symbol for an open space
	 */
	OPEN(" "),
	/**
	 * Exit symbol X
	 */
	EXIT("X"),
	/**
	 * Entrance symbol E
	 */
	ENTER("E"),
	/**
	 * Wall symbol W
	 */
	WALL("W"),
	/**
	 * Symbol for the path through the maze o
	 */
	PERSON("o"),
	/**
	 * Invalid symbol i to represent an
	 * invalid character in the input file.
	 */
	INVALID("i");
	/**
	 * Symbol to be displayed for the maze
	 */
	private String symbol;
	
	/**
	 * Returns an array containing the constants of this enum class,
	 * in the order they are declared.
	 * @param symbol
	 */
	private MazeCell(String symbol) {
		this.symbol = symbol;
	}
	
	
	
	
	/**
	 * an array containing the constants of this enum class,
	 * in the order they are declared
	 * @return an array containing the constants of this enum class,
	 * in the order they are declared
	 */
	public static MazeCell[] myvalues() {
		return (MazeCell.class.getEnumConstants());
		
	}
	
	/**
	 * Returns the enum constant of this class with the specified name.
	 * The string must match exactly an identifier used to declare an
	 * enum constant in this class. (Extraneous whitespace characters are not permitted.)
	 * @param name the name of the enumeration constant to be returned.
	 * @return the enum constant with the specified name
	 */
	
	@SuppressWarnings("unlikely-arg-type")
	public static MazeCell myValueOf(String name) {
		
		MazeCell mazecell = null;
		//Access each type of mazeCell object
		for (MazeCell object: values()) {
			//Map the passed name with each mazeCell object
			if(object.equals(name)) {
				//assign our found mazecell object
				mazecell = object;
				
			}
		//Return mapped mazeCell object
		}return (mazecell);
	}
	
	/**
	 * 
	 */
	public String toString() {
		
		//Return the string representation(symbol) of Mazecell
		return (this.symbol);
	}
	
	
}

package edu.wcu.cs.cs150.p3.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.io.FileNotFoundException;

import edu.wcu.cs150.p3.adt.LinkedDequeue;

/**
 * This class provides clients with the ability navigate a maze, if possible.
 * The attempt status variable is used to tell the status of the MazeRunner, 
 * this can be accessed via the status() accessory method.
 * The statuses are: "No maze run-through attempted yet".
 * Before an attempt a running the maze has been made.
 * "Maze has been attempted" As an attempt at running the maze begins.
 * "Successful Maze Attempt" When a maze running attempt has been successful.
 * "Unsuccessful Maze Attempt" When a maze running attempt was unsuccessful.
 * An unsuccessful attempt is when the maze exit was not found.
 * A successful attempt is when the maze exit has been found.
 * @author Dagmawi Negatu
 * @version may 2023
 *
 */

public class MazeRunner {
	
	
	/**
	 * String to determine if the maze has been attempted
	 */
	private static String attemptStatus;
	
	
	
	/**
	 * The Maze to run through
	 */
	private Maze maze;
	
	/**
	 * The found Path (solution)
	 * through the maze (if any)
	 */
	LinkedDequeue<Point> path;
	
	/**
	 * The locations in the maze already tried to walk through,
	 * where VALID is NOT walked though and INVALID is walked through.
	 * This array is the same size as the Maze object for the reason
	 * that it keeps track on where on the maze has been walked..
	 */
	private int[][] steps;
	
	/**
	 * Indicates a spot has NOT been walked through
	 */
	private static final int VALID = 1;
	
	/**
	 * Indicates a spot has been walked through
	 */
	private static final int INVALID = -1;
	
	/**
	 * Initializes a new MazeRunner based on an input file.
	 * @param filename - name of the maze input file.
	 * File format specified on class website.
	 * 
	 * 
	 */
	public MazeRunner(String filename) {
		
		//Generate maze passed text file name
		try {
			this.maze = new Maze(filename);
		} catch (InvalidInitException e) {// not maze text file
			// TODO Auto-generated catch block
			System.out.println("Fail to initilalize maze.");
			//Exit program
			System.exit(1);
		}
		
		//Not attempted 
		this.attemptStatus = "No maze-run through attempt yet.";
		//Construct valid, invalid, walls, space in maze design 
		this.initSteps();
		
	}
	
	/**
	 * Returns all valid steps (forward and back) that have not been tried.
	 * postcondition: all valid steps have been marked invalid.
	 * @param point current location in the maze
	 * @return valid steps a runner can take
	 */
	private List<Point> validMoves(Point point){
		
		//Variable to hold list of possible moves from a given location
		List<Point> listOfValidMoves = new ArrayList<Point>();
		
		//All four possible moves runner can take through a path
		Point firstPossibleMove = new Point(point.x -1, point.y);
		Point secondPossibleMove = new Point(point.x+1, point.y);
		Point thridPossibleMove = new Point(point.x, point.y -1);
		Point fourthPossibleMove = new Point(point.x, point.y+1);
		
		
		//One possible point is a valid path walk 
		if(checkSpot(point.x-1, point.y)) {
			//Add to the list of possible moves runner can take
		    listOfValidMoves.add(firstPossibleMove);
		 }
		
	    //Two possible point is a valid path walk
		if (checkSpot(point.x+1, point.y)) {
			//Add to the list of possible moves runner can take
		    listOfValidMoves.add(secondPossibleMove);
		}
		
		//Three possible point is a valid path walk
		if(checkSpot(point.x, point.y-1)) {
			//Add to the list of possible moves runner can take
			listOfValidMoves.add(thridPossibleMove);
		}
			
		//Four possible point is a valid path walk	    		
		if(checkSpot(point.x, point.y+1)) {
			//Add to the list of possible moves runner can take
		    listOfValidMoves.add(fourthPossibleMove);
		}   		
		
		//Return list of all possible possible runner can take 
		//from the passed current location
		return(listOfValidMoves);
		
	}
	
	/**
	 * Sets all the open locations in the map as valid for detecting a path. 
	 * Sets up the steps 2D array with all VALID values to show no part of
	 * the maze has been walked on.
	 */
	private final void initSteps() {
		
		//Number of rows and columns in 2D grid maze
		int numRows = maze.getNumRows();
		int numCols = maze.getNumCols();

		//Initialize all steps in 2D array corresponding file data
		this.steps = new int[numRows][numCols];

		//Traverse all possible points in X, y coordinate in maze
		for (int i = 0, j = 0, k = 0; k < numRows * numCols; k++) {
			if (maze.getCellValue(i, j).equals(MazeCell.OPEN) ||// Open space is valid
				maze.getCellValue(i, j).equals(MazeCell.EXIT)) {//Exit is also valid
				this.steps[i][j] = VALID;// Marker point location as valid
			} else {
				this.steps[i][j] = INVALID;// Not valid if not open or exit path
			}

			j++;
			if (j == numCols) {
				i++;
				j = 0;
			}
		}
		
	}
	
	/**
	 * Get the attempt status of the maze.
	 * @return A string containing the attempt status
	 */
	public String getStatus() {
		return (attemptStatus);
	}
	
	/**
	This method uses a stack and queue to discover a path from
	the starting location to the exit
	 */
	
	public void runMaze() {
		
		// Get the starting location and initialize the stack and queue
		Point currentLocation = maze.getStart();
		LinkedDequeue<Point> myStack = new LinkedDequeue<Point>();
		myStack.addFirst(currentLocation);
		LinkedDequeue<LinkedDequeue<Point>> myQueue =
				new LinkedDequeue<LinkedDequeue<Point>>();
		myQueue.addFirst(myStack);
		// Initialize the list of valid moves
		List<Point> validMoves;

		// While we have not reached the exit of the maze
		while(!maze.isExit(currentLocation) ) {
			// Remove the first stack from the queue and get the current location
			myStack = myQueue.removeFirst();
			currentLocation = myStack.peekLast();
			// Get the valid moves from the current location
			validMoves = validMoves(currentLocation);
			
			//Traverse all valid points runner can take from current location
			for(Point validPointObjects: validMoves) {
				// Clone the stack, add the valid move to the cloned stack,
				//and add the cloned stack to the queue
				LinkedDequeue<Point> stackCloner = myStack.stackCloner(myStack);
				stackCloner.addLast(validPointObjects);
				myQueue.addFirst(stackCloner);
			}

			//The queue is empty, we cannot find a path to the exit
			if(myQueue.isEmpty()) {
				this.attemptStatus = "Unsuccessful Maze attempt";
				break;
			}
		}

		// Found a path to the exit, set the path and update the attempt status
		if(!myQueue.isEmpty()) {
			maze.setPath(myStack);
			this.attemptStatus = "Successful Maze attempt";
		}

		
		//queue is empty, we cannot find a path to the exit
		System.out.println(this.attemptStatus 
				+ "\n" + maze.toString());
		

	}
	
	
	
	/**
	 * A helper method to determine if a location counts as valid.
	 * It checks if the row and column are within the bounds of the maze.
	 * It also checks if the location defines an open spot or an exit,
	 * and that the location has not been walked on previously.
	 * If all this is true, the position is marked with a -1 (INVALID).
	 * It is the steps object which is used to keep track of visited locations.
	 * @param tRow - a row index for a possible valid location.
	 * @param tCol - a column index for a possible valid location.
	 * @return true if the location exists within the maze and
	 * counts as valid as part of a potential path.
	 */
	private boolean checkSpot(int tRow, int tCol) {
		
		//location is unvisited
		boolean locationIsValid = false;
		
		//Row and column indices are non-negative
		if (tRow >= 0 && tCol >= 0 && maze.getNumRows()
				//indices are within maze bounds
				> tRow && maze.getNumCols() > tCol
				//cell has not been explored before
		    && (maze.getCellValue(tRow, tCol) == MazeCell.OPEN ||
		       //is either OPEN or an EXIT
		       maze.getCellValue(tRow, tCol) == MazeCell.EXIT)
		           && this.steps[tRow][tCol] == VALID) {
			//Mark cell as already visited  
		    this.steps[tRow][tCol] = INVALID;
		    //Flag to indicate cell is unvisited
		    locationIsValid = true;
		}
		
		//Return whether location is unexplored
		return (locationIsValid);
	}
	
	/**
	 * Generate current maze and attemped status fo
	 */
	public String toString() {
		String mazeRun =   this.attemptStatus + "\n" +
	maze.toString();
		return (mazeRun);
		
	}
	
	

	

}

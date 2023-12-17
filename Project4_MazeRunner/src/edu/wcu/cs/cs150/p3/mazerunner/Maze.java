package edu.wcu.cs.cs150.p3.mazerunner;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.wcu.cs150.p3.adt.LinkedDequeue;

/**
 * Models a simple maze by having a 2D array of MazeCells.

The class's constructor will receive a file name which corresponds
to a text file containing the maze data.
The maze data file contains on the first line
two integers separated by a space. These two values correspond to
the number or rows and columns (in characters) in the eventual maze. 
From the next line file contains characters that correspond 
with the mazes design. W for walls, spaces for walks,
E for entrance, and X for exit. There should be only one entrance.
 * @author Dagmawi Negatu 
 * @Version May 2021
 *
 */

public class Maze {
	
	/**
	 * The maze, made up of an enum
	 */
	private static MazeCell[][] grid;
	
	/**
	 * Column number of the starting location
	 */
	private int startCol;
	
	/**
	 * Row number of the starting location
	 */
	private int startRow;
	
	/**
	 * Create a new maze based upon the file defaultMaze.txt
	 * @throws InvalidInitException 
	 */
	public Maze() throws InvalidInitException {
		this("defaultMaze.txt");
		
	}
	
	/**
	 * Create a maze from an already existing collection of MazeCells.
	 * @param maze  - An already existing collection of MazeCells.
	 */
	public Maze(MazeCell[][] maze) {
	
		grid = maze;
	}
	
	/**
	 * Create a new maze based on a file by getting each character of each line,
	 * and storing it in a 2D array. The first two items in the named file are
	 * expected to contain two integers on a single line, and then on the next
	 * line the Maze data begins.
	 * If in scanning the file input, an non-integer is expected
	 * @param maze
	 * @throws InvalidInitException 
	 */
	public Maze(String fileName) throws InvalidInitException {
		
		//Initialize our fields for maze generation
		Scanner fileIn = null;
		int column;
		int row;
		String line = null;
		
		try {
			
			//Scanner object for text file
			fileIn = new Scanner( new File(fileName));
			//Number rows
			row = fileIn.nextInt();
			//Number columns
			column = fileIn.nextInt();
			
			//Initialize grid number rows, number columns
			grid = new MazeCell[row][column];
			
			//Step into to maze design
			line = fileIn.nextLine();
		
			//Iterate all characters in text file
			for (int i = 0; i < row; i++) {
				line = fileIn.nextLine();
				for(int j = 0; j < column ;j++) {
					//Access each character in text file
					char charAt = line.charAt(j);
					
					//Each character represented as MazeCell object
					MazeCell cell = getSymbol(charAt);
					
					//Switch case to initialize grid array
					//corresponding MazeCell object to text file
					switch(cell) {
					
					//Enter enumeration mapped to grid Mazecell object
					case ENTER:
						grid[i][j] = MazeCell.ENTER;
						this.startRow = i;
						this.startCol = j;
						break;
					//Open enumeration mapped to grid MazeCell object
					case OPEN:
						grid[i][j] = MazeCell.OPEN;
						break;
					//Exit enumeration mapped to grid MazeCell object
					case EXIT:
						grid[i][j] = MazeCell.EXIT;
						break;
					//Person enumeration mapped to grid MazeCell valid path
					case PERSON:
						grid[i][j] = MazeCell.PERSON;
						break;
					//Invalid enumeration mapped to grid MazeCell invalid path
					case INVALID:
						grid[i][j] = MazeCell.INVALID;
						break;
					//All characters not have path walks, map to maze wall
					default:
						grid[i][j] = MazeCell.WALL;
					}
				}
					
			}
			
		}
		catch(FileNotFoundException fnfe) {
			System.err.println("Error cannot find file: " + fileName);
		}
		
		//No maze design(number columns and rows), throw Invalid Integer Exception
		catch(InputMismatchException e) {
			throw new InvalidInitException();
		}
		
		
		
	}
	
	/**
	 * Return the starting location of the maze.
	 * @return The starting location in the maze.
	 */
	public Point getStart() {
		//Point object x, y in coordinate plane. 
		Point point = null;
		
		//Iterate accessing each element in maze.
		for(int i = 0; i < getNumRows(); i++) {
			for(int j = 0; j < getNumCols(); j++) {
				//Check that character is an entry point.
				if(grid[i][j] == MazeCell.ENTER) {
					//save the location of the entry point.
					point = new Point(i , j);
				}
			}
		}
		
		//return location point
		return (point);
	}
	
	/**
	 * Simple method that maps a character to a MazeCell enumerated value.
	 * A W is a wall WALL, a space is an open area OPEN, an X is an exit EXIT,
	 * and an E is an entry ENTER
	 */
	
	
	private MazeCell getSymbol(char ch) {
		MazeCell mazecell = null;
		//Access MazeCell object in our grid 2D array
		for (MazeCell object: MazeCell.myvalues()) {
			//Maping mazeCell object with character ch 
			if(object.toString().charAt(0) == ch) {
				//Save our MazeCell object representing character 
				mazecell = object;
			}
		}
		
		//Return mazeCellWall object
		return (mazecell);
	}
	
	

	/**
	 * Find the symbol at the specified location in the maze
	 * @param row in the maze we wish to check
	 * @param column in the maze we wish to check
	 * @return The symbol in that location, 
	 * or an invalid symbol if row or column was an invalid index into the maze.
	 */
	public MazeCell getCellValue(int row, int column) {
		
			//Return mazcell object in grid 2D array 
			return (grid[row][column]);
		
		
	}
	
	
	/**
	 * Sets a location in the maze to a specified value.
	 * @param row in the maze we wish to set.
	 * @param column in the maze we wish to set.
	 * @param value  symbol we wish to place at the cell
	 */
	
	public void setCellValue(int row, int column, MazeCell value) {
		
		//Change MazCell object in grid 2D array
		grid[row][column] = value;
	}
	
	/**
	 * Get the total number of rows in maze
	 * @return The number of rows in the maze.
	 */
	public int getNumRows() {
		return(grid.length);
	}
	
	/**
	 * Get the total number of columns in maze
	 * @return the number of rows in the maze
	 */
	public int getNumCols() {
		return (grid[0].length);
	}
	/**
	 * Set mark a discovered path within the maze.
	 * @param path A set of Points that indicate
	 * locations for a path from entry to exit.
	 */
	
	public void setPath(LinkedDequeue<Point> path) {
	

		//Access all set of points that indicate locations for a valid path to exit
		for (Point point : path) {
			//X cooridate of each point in the path
		    int x = (int) point.getX();
		    //Y coordinate of each point in the path
		    int y = (int) point.getY();
		    MazeCell cellValue = getCellValue(x, y);
		    //remove entry and exit location as set of path for maze generation simplicity 
		    if (cellValue != MazeCell.ENTER && cellValue != MazeCell.EXIT) {
		    	//the found walk points the person representation mazecell value
		        setCellValue(x, y, MazeCell.PERSON);
		    }
		}
		
	}
	
	/**
	 * Determine if the Point value provided maps to an exit location
	 * @param location The location to check to see if it is an exit.
	 * @return true if the Point maps to an exit location, false otherwise.
	 */
	public boolean isExit(Point location) {
		//X coordinate of the passed in location
		int xCordinate = (int) location.getX();
		//Y coordinate of the passed in location
		int yCordinate = (int) location.getY();

		//Check points location is at an exit
		return getCellValue(xCordinate, yCordinate) == MazeCell.EXIT;
	}
	
	
	/**
	 * Create a String representing the current state of the maze.
	 * The current state of the maze
	 */
	public String toString() {
			
	    //StringBuilder to hold maze generation 
		StringBuilder sb = new StringBuilder();
	    int numRows = this.getNumRows();
	    int numCols = this.getNumCols();
	    //Nested iteration to access each mazecell object within
	    //grid 2D Array
	    for (int i = 0; i < numRows; i++) {
	        for (int j = 0; j < numCols; j++) {
	            MazeCell c = grid[i][j];
	            //Map Wall object as white boxes in maze generation 
	            if (c == MazeCell.WALL) {
	                sb.append("\u2588\u2588\u2588");
	            }
	            //Map Person object(walk path) to white dots in maze generation
	            else if (c == MazeCell.PERSON) {
	                sb.append(" \u25CF ");
	            }
	            //default to already mapped mazecell object within
	            // maze gird 2D array
	            else {
	                sb.append(c).append(c).append(c);
	            }
	        }
	        //Output new line each number of rows in maze generation 
	        sb.append("\n");
	    }
	    
	    //Return the updated maze design 
	    return sb.toString();
	}
}

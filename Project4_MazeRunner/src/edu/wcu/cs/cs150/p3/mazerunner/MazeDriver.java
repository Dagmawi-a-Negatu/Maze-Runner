package edu.wcu.cs.cs150.p3.mazerunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver to determine a valid path (if one exists) through a maze.
 * @author Dagmawi Negatu
 * @version May 2023
 *
 */

public class MazeDriver {
	
	/**
	 * Entry point into our program.
	 * @param args - [o]  filename of a maze input file.
	 */
	public static void main(String[] args) throws FileNotFoundException,
	InvalidInitException {
		
		// Make sure an argument was passed in
        if (args.length == 0) {
            usageAndQuit();
        }
        
        //Get the file name from the command line argument
        String fileName = args[0];
		
		
	  MazeRunner maze = new MazeRunner(fileName);
	  System.out.println(maze.toString());
	  maze.runMaze();
		
	
		
	}
	
	/**
	 * print a usage message and quit the program
	 */
	private static void usageAndQuit() {
	
		System.out.println("Usage: javac adt/*. java*");
		System.out.println("Javac maze /*.java");
		System.out.println("Java MazeDriver <input.txt>");
		
		//Quit program
		System.exit(1);
	}

}

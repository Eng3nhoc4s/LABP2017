import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sudoku {

	/**
	 * Reads a text file and returns the matrix with sudoku grid
	 * 
	 * @param file
	 *            The path to the text file
	 * @return A matrix of int with size 9 x 9
	 */
	public static int[][] fileToMatrix(String file) {

		int[][] sudokuGrid = new int[9][9];

		try {

			// Reads the file
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int index = 0;

			// Iterates each line
			while ((line = br.readLine()) != null) {

				String[] lineProcessing = line.split(" ");

				// Parse each character to a digit
				for (int i = 0; i < lineProcessing.length; i++) {
					sudokuGrid[index][i] = Integer.parseInt(lineProcessing[i]);
				}
				index++;
			}

		} catch (FileNotFoundException e) {
			System.err.println("Ficheiro " + file + " não encontrado!");
		} catch (NumberFormatException e) {
			System.err.println("Os caracteres no ficheiro " + file + " têm de ser digitos!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sudokuGrid;
	}

	/**
	 * Given a matrix of ints with a sudoku puzzle, displays it propperly
	 * 
	 * @param matrix
	 *            A matrix of ints
	 */
	public static void printMatrix(int[][] matrix) {

		int lines = 0, cols;

		while (lines < matrix.length) {

			// If it's an horizontal div line print it and don't count as
			// printed line of nums
			if (lines % 3 == 0)
				System.out.println("+-----+-----+-----+");

			// Print the numbers columns
			for (cols = 0; cols < matrix[0].length; cols += 3) {
				System.out.print(
						"|" + matrix[lines][cols] + " " + matrix[lines][cols + 1] + " " + matrix[lines][cols + 2]);

			}

			// End the table and skip the line
			System.out.print("|\n");

			// Lets print the next line
			lines++;
		}

		// Close the table's bottom
		System.out.println("+-----+-----+-----+");
	}

	/**
	 * Checks if a matrix has any line with repetitions
	 * 
	 * @param matrix
	 *            An array of arrays of ints (int[][])
	 * @return True if it has a repetition on any line
	 */
	public static boolean hasRepeatedNumbersLine(int[][] matrix) {

		// Iterate the lines
		for (int line = 0; line < matrix.length; line++) {

			if (checkSingleLineRepetition(matrix[line]))
				return true;
		}
		return false;
	}

	/**
	 * Verifies if a line has repeated numbers
	 * 
	 * @param line
	 *            An int array
	 * @return True if a number repeats
	 */
	private static boolean checkSingleLineRepetition(int[] line) {

		// Iterate the pivot to search
		for (int i = 0; i < line.length; i++) {

			int srch = line[i];

			// The actual search process
			for (int j = i + 1; j < line.length; j++) {

				if (srch == line[j])
					return true;
			}
		}

		return false;
	}

	
	public static boolean hasRepeatedNumbersCols(int[][] matrix){
		//TODO
	}

	private static boolean checkSingleColRepetition(int[] line){
		//TODO
	}
}

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
			System.err.println("File " + file + " not found!");
			return null;
		} catch (NumberFormatException e) {
			System.err.println("The characters in file " + file
					+ " must be digits!");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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
				System.out.print("|" + matrix[lines][cols] + " "
						+ matrix[lines][cols + 1] + " "
						+ matrix[lines][cols + 2]);

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

			if (checkLineForRepetitions(matrix[line]))
				return true;
		}
		return false;
	}

	/**
	 * Checks if a matrix has repeated numbers in it's columns
	 * 
	 * @param matrix
	 *            The matrix to be checked
	 * @return True if has repeated numbers
	 */
	public static boolean hasRepeatedNumbersCols(int[][] matrix) {

		// To test the columns for repetitions without adding further
		// functionality we transpose
		int[][] transposedMatrix = transposeMatrix(matrix);

		return hasRepeatedNumbersLine(transposedMatrix);
	}

	/**
	 * Verifies if there is any repetition of number inside the 3x3 subgroups
	 * 
	 * @param matrix
	 *            The sudoku matrix
	 * @return True if there are repetitions
	 */
	public static boolean checkRepetitionInSubgroups(int[][] matrix) {

		// Runs the lines of subgroups
		for (int i = 1; i < 4; i++) {
			// runs the cols of subgroups
			for (int j = 1; j < 4; j++) {

				if (checkLineForRepetitions(getQuadrantAsLine(matrix, i, j))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Verifies if an int [] has repeated numbers
	 * 
	 * @param line
	 *            An int array
	 * @return True if a number repeats
	 */
	private static boolean checkLineForRepetitions(int[] line) {

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

	/**
	 * Transposes a matrix
	 * 
	 * @param matrix
	 *            The matrix to be transposed
	 * @return A transposed matrix (the lines become columns and vice versa)
	 */
	private static int[][] transposeMatrix(int[][] matrix) {

		int[][] transposed = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix.length; j++) {
				transposed[i][j] = matrix[j][i];
			}
		}
		return transposed;
	}

	/**
	 * Returns the specified subgroup of the sudoku grid
	 * 
	 * @param matrix
	 *            The sudoku matrix
	 * @param row
	 *            The subgroup row (from 1 to 3)
	 * @param col
	 *            The subgroup column (from 1 to 3)
	 * @return An int [] with the selected subgroup
	 */
	private static int[] getQuadrantAsLine(int[][] matrix, int row, int col) {

		int[] line = new int[matrix.length];
		int index = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				line[index++] = matrix[i][j];
			}
		}
		return line;
	}

}

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
			System.err.println("Ficheiro " + file + " n�o encontrado!");
		} catch (NumberFormatException e) {
			System.err.println("Os caracteres no ficheiro " + file + " têm de ser digitos!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sudokuGrid;
	}

	/**
	 * Given a matrix of ints with a sudoku puzzle, displays it propperly
	 * @param matrix A matrix of ints
	 */
	public static void printMatrix(int[][] matrix) {

		int lines = 0, cols;

		while (lines < matrix.length) {

			// If it's an horizontal div line print it and don't count as
			// printed line of nums
			if (lines % 3 == 0)
				System.out.println("+-----+-----+-----+");		

			// Print the numbers columns
			for (cols = 0; cols < matrix[0].length; cols +=3) {
				System.out.print("|" + matrix[lines][cols] + " " + matrix[lines][cols + 1] + " " + matrix[lines][cols + 2]);
					
			}
			
			//End the table and skip the line
			System.out.print("|\n");
			
			//Lets print the next line
			lines++;
		}
		
		//Close the table's bottom
		System.out.println("+-----+-----+-----+");
	}
}

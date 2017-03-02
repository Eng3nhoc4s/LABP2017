import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sudoku {


	/**
	 * Reads a text file and returns the matrix with sudoku grid
	 * 
	 * @param file
	 *            The path to the text file
	 * @return A matrix of int with size 
	 * 				9 x 9
	 */
	public static int[] fileToMatrix(String file) {

		int [][] sudokuGrid = new int[9][9];
		
		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int index = 0;

			while ((line = br.readLine()) != null) {

				
				for (int i = 0; i < 9; i++) {
					sudokuGrid [index] [i] = Integer.parseInt(Character.toString(line.charAt(i))); 
					System.out.print(sudokuGrid [index] [i]);
				}
				
				System.out.println();
				
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}

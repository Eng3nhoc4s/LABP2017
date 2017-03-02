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
	 * @return A matrix of int with size 
	 * 				9 x 9
	 */
	public static int[][] fileToMatrix(String file) {

		int [][] sudokuGrid = new int[9][9];
		
		try {

			//Reads the file
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int index = 0;

			//Iterates each line
			while ((line = br.readLine()) != null) {

				String [] lineProcessing = line.split(" ");
				
				//Parse each character to a digit
				for (int i = 0; i < lineProcessing.length; i++) {
					sudokuGrid [index] [i] = Integer.parseInt(lineProcessing[i]); 
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("Ficheiro " + file + " não encontrado!");
		} catch (NumberFormatException e) {
			System.err.println("Os caracteres no ficheiro " + file + " têm de ser digitos!");
		} catch (IOException e){
			e.printStackTrace();
		}
		
		printMatrix(sudokuGrid); //TODO REMOVER

		return sudokuGrid;
	}

	
	public static void printMatrix(int [][] matrix){
		int x = 0;

		for (int y = 0; y < 9; y++) {
			if (y % 3 == 0) {
				System.out.println("+-----+-----+-----+");
			}else{
				System.out.println();
			}
		}
		
	}
}

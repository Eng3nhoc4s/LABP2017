
public class RunSudoku {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if(args.length == 0){
			System.out.println("Insufficient parameters!\nUsage:\n\tRunSudoku.java <filename>");
		}else{
			int [][] myMatrix = Sudoku.fileToMatrix(args[0]);
			Sudoku.printMatrix(myMatrix);
			System.out.println("Has repeated lines: " + Sudoku.hasRepeatedNumbersLine(myMatrix));
			System.out.println("Has repeated columns: " + Sudoku.hasRepeatedNumbersCols(myMatrix));

			
		}
		
	}

}

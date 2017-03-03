public class RunSudoku {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 0) {

			System.out
					.println("Insufficient parameters!\nUsage:\n\tRunSudoku.java <filename>");

		} else {

			int[][] myMatrix = Sudoku.fileToMatrix(args[0]);

			if (myMatrix != null) {

				boolean t1 = (!Sudoku.hasRepeatedNumbersLine(myMatrix));
				System.out.println("Repeated numbers in line: " + !t1);
				boolean t2 = (!Sudoku.hasRepeatedNumbersCols(myMatrix));
				System.out.println("Repeated numbers in column: " + !t2);
				boolean t3 = (!Sudoku.checkRepetitionInSubgroups(myMatrix));
				System.out.println("Repeated numbers in subgroup: " + !t3);

				boolean isValid = t1 && t2 && t3;

				if (isValid)
					System.out.println("O jogo respeita as regras do sudoku");

				else {
					System.out.println("O puzzle seguinte está errado:");
					Sudoku.printMatrix(myMatrix);

				}
			}
		}
	}
}

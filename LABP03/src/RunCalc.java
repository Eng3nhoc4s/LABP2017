import java.util.Scanner;

public class RunCalc {

	public static void main(String[] args) {
		
		Calc calc = new Calc();

		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextLine()){
			System.out.println(calc.run(sc.nextLine()));
		}
	}

}

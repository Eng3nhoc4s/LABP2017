import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calc {

	/** Describes the empty stack to the user */
	private static final String EMPTY_STACK_ERROR = "Stack is empty! Cannot perform operation!\nCheck for the order of operators and missing operands!";
	
	/** Logs the errors to the user */
	private static final Logger Log = Logger.getLogger("Calc.class");

	/** Describes an error parsing the input */
	private static final String INVALID_NUMBER_ERROR = "Could not parse number! Check if every character is a digit or a valid operator.";

	/** Data structure that holds the values and results */
	private Stack<Double> stack;

	/** Emultaes the amount of decimal places to be shown */
	private String numberOfZeros;

	/** The number formatter */
	private NumberFormat nfFormat;

	/**
	 * Class constructor
	 */
	public Calc() {
		stack = new Stack<>();
		numberOfZeros = "0000";
		nfFormat = new DecimalFormat("#0." + numberOfZeros);
	}

	/**
	 * Receives a line of input, parses it and calculates it
	 * 
	 * @param input
	 *            A line of the calculator according to the Reverse Polish
	 *            Notation
	 * @return A string with the result of the calculation
	 */
	public String run(String input) {

		//Split the input into tokens
		String[] tokens = input.split(" ");
		
		//back up the stack in case of error
		Stack<Double> backup = (Stack<Double>) stack.clone();

		// Start parsing the tokens
		for (String currentToken : tokens) {

			if (currentToken.equals("+")) { // SUM
				try {
					stack.push(sum(stack.pop(), stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				} 

			} else if (currentToken.equals("-")) { // SUB
				try {
					stack.push(subtract(stack.pop(), stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("*")) { // MULT
				try {
					stack.push(multiply(stack.pop(), stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("/")) { // DIV
				try {
					stack.push(divide(stack.pop(), stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("tan")) { // TAN
				try {
					stack.push(tangent(stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("cotn")) { // COTAM
				try {
					stack.push(cotangent(stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("e")) { // EXP
				try {
					stack.push(exponential(stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("^")) { // POWER
				try {
					stack.push(power(stack.pop(), stack.pop()));
				} catch (EmptyStackException e) {
					
					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("#")) { // SYMETRIC
				try {
					stack.push(symetric(stack.pop()));
				} catch (EmptyStackException e) {

					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else if (currentToken.equals("dec")) { // CHANGE THE AMOUNT OF TRAILING 0's

				try {
					dec(stack.pop());
				} catch (EmptyStackException e) {
					Log.log(Level.SEVERE, EMPTY_STACK_ERROR);
					stack = backup;
					break;
				}

			} else { // ITS A NUMBER, STACK IT
				
				try {
					stack.push(Double.parseDouble(currentToken));
				} catch (NumberFormatException e) {
					Log.log(Level.SEVERE, INVALID_NUMBER_ERROR);
					stack = backup;
					break;
				}
			}
		}
		
		// RETURN STATEMENT
		if(!stack.isEmpty()){
			System.out.println(stack.toString());
			return nfFormat.format(stack.peek());
		}else{
			return "";
		}
	
	}

	/**
	 * Sum operation
	 * 
	 * @param v1
	 *            First Value
	 * @param v2
	 *            Second Value
	 * @return The sum value
	 */
	private double sum(double v1, double v2) {
		return v1 + v2;
	}

	/**
	 * Subtraction operation
	 * 
	 * @param v1
	 *            First Value
	 * @param v2
	 *            Second Value
	 * @return The subtraction value
	 */
	private double subtract(double v1, double v2) {
		return v1 - v2;
	}

	/**
	 * Multiply operation
	 * 
	 * @param v1
	 *            First Value
	 * @param v2
	 *            Second Value
	 * @return The multiplication value
	 */
	private double multiply(double v1, double v2) {
		return v1 * v2;
	}

	/**
	 * The division operation
	 * 
	 * @param v1
	 *            First Value
	 * @param v2
	 *            Second Value
	 * @return The result of the division
	 */
	private double divide(double v1, double v2) {
		return v1 / v2;
	}

	/**
	 * The tangent
	 * 
	 * @param v1
	 *            First Value
	 * @return The tangent
	 */
	private double tangent(double v1) {
		return Math.tan(v1);
	}

	/**
	 * The cotangent
	 * 
	 * @param v1
	 *            First Value
	 * @return The cotangent
	 */
	private double cotangent(double v1) {
		return 1 / tangent(v1);
	}

	/**
	 * The power with base base and exp exponent
	 * 
	 * @param base
	 *            The base
	 * @param exp
	 *            The exponent
	 * @return The power
	 */
	private double power(double base, double exp) {
		return Math.pow(base, exp);
	}

	/**
	 * The exponential
	 * 
	 * @param v1
	 *            First Value
	 * @return The exponential
	 */
	private double exponential(double v1) {
		return Math.exp(v1);
	}
	
	/**
	 * Defines the number of decimal places
	 * @param value The number of decimal places
	 */
	private void dec (double value){
		// Get the number od zeros
		double numberOfZs = Math.floor(value);

		StringBuilder sb = new StringBuilder();

		// number of zeros must be >= 0
		if (numberOfZs >= 0) {

			for (int i = 0; i < numberOfZs; i++) {
				sb.append("0");
			}

		} else {
			Log.log(Level.SEVERE, "Cannot display a negative amount of decimal places.");
		}

		// Update pattern
		numberOfZeros = sb.toString();

		// Update the formatter
		nfFormat = new DecimalFormat("#0." + numberOfZeros);
	}

	/**
	 * The symetric
	 * 
	 * @param v1
	 *            First Value
	 * @return The symetric
	 */
	private double symetric(double v1) {
		return v1 * (-1);
	}

}

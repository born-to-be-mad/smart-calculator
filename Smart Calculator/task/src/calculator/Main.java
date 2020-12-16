package calculator;

import java.util.List;
import java.util.Scanner;

import static calculator.Calculator.LETTERS_REGEX;
import static calculator.Calculator.NUMBERS_REGEX;

/**
 * A program that reads two numbers in a loop and calculates the result in the standard output.
 * If a user enters only a single number, the program should print the same number.
 * If a user enters an empty line, the program should ignore it.
 */
public class Main {

    private final static boolean DEBUG = false;

    private static final String COMMAND_REGEX = "/\\w+";
    private static final String ASSIGNMENT_DELIMITER_REGEX = "\\s*=\\s*";

    private static final String COMMAND_EXIT = "/exit";
    private static final String MESSAGE_BYE = "Bye!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        String input = scanner.nextLine().trim();
        while (!input.equals(COMMAND_EXIT)) {
            if (input.matches(COMMAND_REGEX)) {
                System.out.println(calculator.getCommandInfo(input).orElse("Unknown command"));
            } else if (isValidAssignment(input)) {
                calculator.assign(input);
            } else if (input.matches(LETTERS_REGEX)) {
                System.out.println(calculator.getVariableValue(input).orElse("Unknown variable"));
            } else if (!"".equals(input)) {
                try {
                    List<String> postfixExpression = calculator.infixToPostfix(input);
                    int sum = calculator.calculateInPostfixNotation(postfixExpression);
                    System.out.println(sum);
                } catch (Exception e) {
                    System.out.println("Invalid expression");
                }
            }
            input = scanner.nextLine().trim();
        }

        System.out.println(MESSAGE_BYE);
    }

    private static boolean isValidAssignment(String input) {
        if (!input.contains("=")) {
            return false;
        }
        String[] variables = input.trim().split(ASSIGNMENT_DELIMITER_REGEX);
        if (variables.length != 2) {
            System.out.println("Invalid assignment");
        }
        String name = variables[0];
        String nameOrValue = variables[1];
        if (!name.matches(LETTERS_REGEX)) {
            System.out.println("Invalid identifier");
        }
        if (nameOrValue.matches(NUMBERS_REGEX) || nameOrValue.matches(LETTERS_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid identifier");
        }

        return false;
    }

    private static void debug(Object obj) {
        if (DEBUG) {
            System.out.println(obj);
        }
    }
}

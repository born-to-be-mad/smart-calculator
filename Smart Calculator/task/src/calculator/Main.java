package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A program that reads two numbers in a loop and calculates the result in the standard output.
 * If a user enters only a single number, the program should print the same number.
 * If a user enters an empty line, the program should ignore it.
 */
public class Main {

    private final static Pattern IS_DIGIT = Pattern.compile("[+-]?\\d+");

    private static final Map<Character, Character> OPPOSITE_OPERATORS = Map.of('+', '+', '-', '+', '*', '*', '/', '/');

    private static final String COMMAND_EXIT = "/exit";
    private static final String MESSAGE_BYE = "Bye!";
    private static final String COMMAND_HELP = "/help";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(COMMAND_EXIT)) {
            input = input.trim();
            if (COMMAND_HELP.equalsIgnoreCase(input)) {
                System.out.println("The program calculates the sum of numbers");
            } else if (input.length() > 0) {
                processInputNumbers(input);
            }
            input = scanner.nextLine();
        }

        System.out.println(MESSAGE_BYE);
    }

    private static void processInputNumbers(String input) {
        List<String> inputNumbers = Arrays.stream(input.split("\\s+"))
                                          .collect(Collectors.toList());

        if (inputNumbers.size() == 1) {
            System.out.println(inputNumbers.get(0));
        } else {
            System.out.println(process(inputNumbers));
        }
    }

    private static int process(List<String> numbers) {
        //return numbers.stream().reduce(0, Integer::sum);
        int result = 0;
        char operator = '+';
        for (String number : numbers) {
            if (IS_DIGIT.matcher(number).matches()) {
                switch (operator) {
                case '+':
                    result += Integer.parseInt(number);
                    break;
                case '-':
                    result -= Integer.parseInt(number);
                    break;
                default:
                    break;
                }

            } else {
                operator = identifyOperator(number);
            }
        }
        return result;
    }

    private static char identifyOperator(String number) {
        char operator;
        String operators = number.trim();
        char initialOperator = operators.charAt(0);
        operator = operators.length() % 2 == 0
                   ? OPPOSITE_OPERATORS.get(initialOperator)
                   : initialOperator;
        return operator;
    }
}

package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A program that reads two numbers in a loop and calculates the result in the standard output.
 * If a user enters only a single number, the program should print the same number.
 * If a user enters an empty line, the program should ignore it.
 */
public class Main {

    private final static boolean DEBUG = false;
    private final static Pattern IS_DIGIT = Pattern.compile("[+-]?\\d+");
    private final static Pattern IS_OPERATOR = Pattern.compile("[+-]+");

    private static final Map<Character, Character> OPPOSITE_OPERATORS = Map.of('+', '+', '-', '+', '*', '*', '/', '/');

    private static final String COMMAND_EXIT = "/exit";
    private static final String MESSAGE_BYE = "Bye!";
    private static final String COMMAND_HELP = "/help";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(COMMAND_EXIT)) {
            input = input.trim();
            if (input.startsWith("/")) {
                if (COMMAND_HELP.equalsIgnoreCase(input)) {
                    System.out.println("The program calculates the sum of numbers");
                } else {
                    System.out.println("Unknown command");
                }
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

        process(inputNumbers).ifPresent(System.out::println);
    }

    private static Optional<Integer> process(List<String> strings) {
        debug(strings);
        int result = Integer.MIN_VALUE;
        char operator = '#';
        int current = 0;
        int next = 0;
        for (String string : strings) {
            debug("Processing string:" + string);
            if (IS_DIGIT.matcher(string).matches()) {
                current = Integer.parseInt(string);
                if (result == Integer.MIN_VALUE) {
                    result = current;
                } else {
                    switch (operator) {
                    case '+':
                        result += current;
                        break;
                    case '-':
                        result -= current;
                        break;
                    default:
                        System.out.println("Invalid expression");
                        break;
                    }
                }
            } else if (IS_OPERATOR.matcher(string).matches()) {
                operator = identifyOperator(string);
                debug("operator found:" + operator);
            } else {
                System.out.println("Invalid expression");
                return Optional.empty();
            }
        }
        return Optional.of(result);
    }

    private static void debug(Object obj) {
        if (DEBUG) {
            System.out.println(obj);
        }
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

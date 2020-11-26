package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A program that reads two numbers in a loop and prints the sum in the standard output. If a user enters only a
 * single number, the program should print the same number. If a user enters an empty line, the program should ignore
 * it.
 */
public class Main {

    private static final String COMMAND_EXIT = "/exit";
    private static final String MESSAGE_BYE = "Bye!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> inputNumbers;
        String input = scanner.nextLine();
        while (!input.equals(COMMAND_EXIT)) {
            input = input.trim();
            if (input.length() > 0) {
                processInputNumbers(input);
            }
            input = scanner.nextLine();
        }

        System.out.println(MESSAGE_BYE);
    }

    private static void processInputNumbers(String input) {
        List<Integer> inputNumbers;
        inputNumbers = Arrays.stream(input.split("\\s+"))
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());

        switch (inputNumbers.size()) {
        case 1:
            System.out.println(inputNumbers.get(0));
            break;
        case 2:
            System.out.println(process(inputNumbers.get(0), inputNumbers.get(1)));
            break;
        default:
            break;
        }
    }

    private static int process(Integer left, Integer right) {
        return left + right;
    }
}

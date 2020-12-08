package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Calculator logic.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Calculator {
    public static final String ASSIGNMENT_DELIMITER_REGEX = "\\s*=\\s*";
    public static final String LETTERS_REGEX = "[A-Za-z]+";
    public static final String NUMBERS_REGEX = "-?+[\\d]+";
    public static final String EXPRESSION_REGEX = "((-*)|(\\+*)|([a-zA-Z0-9])|(\\s*))+";

    private static final String COMMAND_HELP = "/help";

    private final Map<String, Integer> variables = new HashMap<>();

    public Map<String, Integer> getVariables() {
        return variables;
    }

    public void assign(String input) {
        String[] strings = input.trim().split(ASSIGNMENT_DELIMITER_REGEX);
        String name = strings[0].trim();
        String value = strings[1].trim();

        if (value.matches(NUMBERS_REGEX)) {
            variables.put(name, Integer.parseInt(value));
        } else if (value.matches(LETTERS_REGEX)) {
            if (variables.containsKey(value)) {
                variables.put(name, variables.get(value));
            } else {
                System.out.println("Unknown variable");
            }
        }
    }

    public int calculate(String input) {
        String[] numbers = input.trim().replaceAll(" {2,}", " ").split(" ");
        int sum = 0;
        int sign = 1;
        for (String token : numbers) {
            int value;
            if (token.matches(LETTERS_REGEX)) {
                value = resolveTheValue(token);
                sum += sign * value;
            } else if (token.matches(NUMBERS_REGEX)) {
                value = Integer.parseInt(token);
                sum += sign * value;
            } else {
                sign = determineSign(token);
            }
        }
        return sum;
    }

    public Optional<String> getVariableValue(String key) {
        return Optional.ofNullable(variables.get(key)).map(String::valueOf);
    }

    public Optional<String> getCommandInfo(String input) {
        if (COMMAND_HELP.equalsIgnoreCase(input)) {
            return Optional.of("The program calculates simple Math operations");
        }
        return Optional.empty();
    }

    private int resolveTheValue(String number) {
        return variables.get(number) != null ? Integer.parseInt(variables.get(number).toString()) : 0;
    }

    private int determineSign(String number) {
        int sign = 1;
        if (number.matches("-")) {
            sign = -1;
        } else if (number.matches("-+") && number.length() % 2 != 0) {
            sign = -1;
        }
        return sign;
    }


}

package calculator;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link Calculator}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void infixToPostfix() {
        Assert.assertEquals("1 2 +", infixToPostfixAsString(List.of("1","+","2")));
        Assert.assertEquals("1 2 *", infixToPostfixAsString(List.of("1", "*", "2")));
        Assert.assertEquals("1 2 * 3 +", infixToPostfixAsString(List.of("1", "*", "2", "+", "3")));
        Assert.assertEquals("1 2 * 3 +", infixToPostfixAsString(List.of("1", "*", "2", "+", "3")));
        Assert.assertEquals("1 2 * 3 4 * +", infixToPostfixAsString(List.of("1", "*", "2", "+", "3", "*", "4")));

        Assert.assertEquals("3 2 4 * +", infixToPostfixAsString("3 + 2 * 4"));
        Assert.assertEquals("2 3 4 + * 1 +", infixToPostfixAsString("2 * ( 3 + 4 ) + 1"));
    }

    private String infixToPostfixAsString(List<String> strings) {
        return String.join(" ", calculator.infixToPostfix(strings));
    }

    private String infixToPostfixAsString(String string) {
        return String.join(" ", calculator.infixToPostfix(Arrays.asList(string.split("\\s+"))));
    }
}

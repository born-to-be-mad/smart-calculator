package calculator;

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
    public void calculate() {
        Assert.assertEquals(8, calculator.calculate("5 + 3"));
        Assert.assertEquals(12, calculator.calculate("5 + 4 + 3"));
        Assert.assertEquals(14, calculator.calculate("5 + 4 + 3 + 2"));

        Assert.assertEquals(2, calculator.calculate("5 - 3"));
        Assert.assertEquals(1, calculator.calculate("5 - 3 - 1"));
        Assert.assertEquals(2, calculator.calculate("5 - 1 - 1 - 1"));

        Assert.assertEquals(12, calculator.calculate("4 * 3"));
        Assert.assertEquals(24, calculator.calculate("4 * 3 * 2"));

        Assert.assertEquals(6, calculator.calculate("5 + 1 -- 1 - 1"));
        Assert.assertEquals(4, calculator.calculate("5 + 1 --- 1 - 1"));
        Assert.assertEquals(8, calculator.calculate("5 + 1 ---- 1 -- 1"));
    }

    @Test
    public void calculateEdgeCases() {
        Assert.assertEquals(8, calculator.calculate("5 ++ 3"));
        Assert.assertEquals(8, calculator.calculate("5 -- 3"));
        Assert.assertEquals(2, calculator.calculate("5 --- 3"));
        Assert.assertEquals(8, calculator.calculate("5 ---- 3"));
        Assert.assertEquals(2, calculator.calculate("5 ----- 3"));
    }

    @Test
    public void calculateInPostfixNotation() {
        Assert.assertEquals(8, calculator.calculateInPostfixNotation("5 3 +"));
        Assert.assertEquals(12, calculator.calculateInPostfixNotation("5 4 3 + +"));

        Assert.assertEquals(15, calculator.calculateInPostfixNotation("5 3 *"));
        Assert.assertEquals(35, calculator.calculateInPostfixNotation("5 4 3 + *"));

        Assert.assertEquals(48, calculator.calculateInPostfixNotation("8 3 * 12 4 2 - * +"));
    }

    @Test
    public void infixToPostfix() {
        Assert.assertEquals("1 2 +", infixToPostfixAsString("1 + 2"));
        Assert.assertEquals("1 2 *", infixToPostfixAsString("1 * 2"));
        Assert.assertEquals("1 2 * 3 +", infixToPostfixAsString("1 * 2 + 3"));
        Assert.assertEquals("1 2 * 3 +", infixToPostfixAsString("1 * 2 + 3"));
        Assert.assertEquals("1 2 * 3 4 * +", infixToPostfixAsString("1 * 2 + 3 * 4"));
        Assert.assertEquals("3 2 4 * +", infixToPostfixAsString("3 + 2 * 4"));
    }

    @Test
    public void infixToPostfix_withExpressions() {
        Assert.assertEquals("a b +", infixToPostfixAsString("a + b"));
        Assert.assertEquals("a b + c +", infixToPostfixAsString("a + b + c"));
        Assert.assertEquals("a b c * +", infixToPostfixAsString("a + b * c"));
        Assert.assertEquals("a b * c d e - * +", infixToPostfixAsString("a * b + c * ( d - e )"));
    }

    @Test
    public void infixToPostfix_withBrackets() {
        Assert.assertEquals("2 3 4 + * 1 +", infixToPostfixAsString("2 * ( 3 + 4 ) + 1"));
        Assert.assertEquals("2 3 4 + * 1 +", infixToPostfixAsString("2 * (3 + 4) + 1"));
        Assert.assertEquals("2 3 4 + * 1 +", infixToPostfixAsString("2 * ( 3 + 4) + 1"));
        Assert.assertEquals("2 3 4 + * 1 +", infixToPostfixAsString("2 * (3 + 4 ) + 1"));

        Assert.assertEquals("8 3 * 12 4 2 - * +", infixToPostfixAsString("8 * 3 + 12 * ( 4 - 2 )"));
        Assert.assertEquals("8 3 * 12 4 2 - * +", infixToPostfixAsString("8 * 3 + 12 * (4 - 2)"));
        Assert.assertEquals("8 3 * 12 4 2 - * +", infixToPostfixAsString("8 * 3 + 12 * (4 - 2 )"));
        Assert.assertEquals("8 3 * 12 4 2 - * +", infixToPostfixAsString("8 * 3 + 12 * ( 4 - 2)"));

        Assert.assertEquals("2 3 4 * + 5 6 * +", infixToPostfixAsString(" 2 + ( 3 * 4 ) + ( 5 * 6)"));
    }

    private String infixToPostfixAsString(String string) {
        return String.join(" ", calculator.infixToPostfix(string));
    }
}

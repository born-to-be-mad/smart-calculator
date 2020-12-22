package calculator;

import java.math.BigInteger;

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
        Assert.assertTrue(BigInteger.valueOf(8).compareTo(calculator.calculate("5 + 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(12).compareTo(calculator.calculate("5 + 4 + 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(14).compareTo(calculator.calculate("5 + 4 + 3 + 2")) == 0);
        Assert.assertTrue(BigInteger.valueOf(2).compareTo(calculator.calculate("5 - 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(1).compareTo(calculator.calculate("5 - 3 - 1")) == 0);
        Assert.assertTrue(BigInteger.valueOf(2).compareTo(calculator.calculate("5 - 1 - 1 - 1")) == 0);

        Assert.assertTrue(BigInteger.valueOf(12).compareTo(calculator.calculate("4 * 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(24).compareTo(calculator.calculate("4 * 3 * 2")) == 0);
        Assert.assertTrue(BigInteger.valueOf(6).compareTo(calculator.calculate("5 + 1 -- 1 - 1")) == 0);
        Assert.assertTrue(BigInteger.valueOf(4).compareTo(calculator.calculate("5 + 1 --- 1 - 1")) == 0);
        Assert.assertTrue(BigInteger.valueOf(8).compareTo(calculator.calculate("5 + 1 ---- 1 -- 1")) == 0);
    }

    @Test
    public void calculateEdgeCases() {
        Assert.assertTrue(BigInteger.valueOf(8).compareTo(calculator.calculate("5 ++ 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(8).compareTo(calculator.calculate("5 -- 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(2).compareTo(calculator.calculate("5 --- 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(8).compareTo(calculator.calculate("5 ---- 3")) == 0);
        Assert.assertTrue(BigInteger.valueOf(2).compareTo(calculator.calculate("5 ----- 3")) == 0);
    }

    @Test
    public void calculateInPostfixNotation() {
        Assert.assertTrue(BigInteger.valueOf(8).compareTo(calculator.calculateInPostfixNotation("5 3 +")) == 0);
        Assert.assertTrue(BigInteger.valueOf(12).compareTo(calculator.calculateInPostfixNotation("5 4 3 + +")) == 0);

        Assert.assertTrue(BigInteger.valueOf(15).compareTo(calculator.calculateInPostfixNotation("5 3 *")) == 0);
        Assert.assertTrue(BigInteger.valueOf(35).compareTo(calculator.calculateInPostfixNotation("5 4 3 + *")) == 0);

        Assert.assertTrue(BigInteger.valueOf(48).compareTo(calculator.calculateInPostfixNotation("8 3 * 12 4 2 - * +")) == 0);
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
        Assert.assertEquals("7 3 4 3 + 7 * 1 + * 6 2 1 + / - +", infixToPostfixAsString("7 + 3 * ((4 + 3) * 7 + 1) - 6 / (2 + 1)"));
    }

    private String infixToPostfixAsString(String string) {
        return String.join(" ", calculator.infixToPostfix(string));
    }
}

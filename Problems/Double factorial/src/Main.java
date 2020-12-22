import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        if (n == 2) {
            return BigInteger.TWO;
        }

        if (n == 3) {
            return BigInteger.valueOf(3);
        }

        if (n == 4) {
            return BigInteger.valueOf(8);
        }

        return BigInteger.valueOf(n).multiply(calcDoubleFactorial(n - 2));
    }
}

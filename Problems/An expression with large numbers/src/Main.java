import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
/*        BigInteger sum = Arrays.stream(scanner.nextLine().split("\\s+"))
                               .map(BigInteger::new)
                               .reduce(BigInteger::add)
                               .orElse(BigInteger.ZERO);*/

        BigInteger a = scanner.nextBigInteger();
        BigInteger b = scanner.nextBigInteger();
        BigInteger c = scanner.nextBigInteger();
        BigInteger d = scanner.nextBigInteger();

        //(-a) * b + c - d
        System.out.println(
            a.negate().multiply(b).add(c).subtract(d)
        );
    }
}

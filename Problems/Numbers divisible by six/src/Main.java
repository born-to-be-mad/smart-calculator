import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
            IntStream.range(0, scanner.nextInt())
                     .map(value -> scanner.nextInt())
                     .filter(value -> value % 6 == 0)
                     .sum()
        );
    }
}

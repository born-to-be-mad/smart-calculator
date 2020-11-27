import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntStream.range(0, scanner.nextInt())
                 .map(value -> scanner.nextInt())
                 .filter(value -> value % 4 == 0)
                 .max()
                 .ifPresent(System.out::println);

    }
}

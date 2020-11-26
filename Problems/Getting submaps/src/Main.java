import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = scanner.nextInt();
        int max = scanner.nextInt();
        int size = scanner.nextInt();

        IntStream.range(0, size)
                 .mapToObj(value -> new AbstractMap.SimpleEntry<>(scanner.nextInt(), scanner.next()))
                 .filter(entry -> entry.getKey() >= min && entry.getKey() <= max)
                 .sorted(Map.Entry.comparingByKey())
                 .forEach(entry -> System.out.printf("%d %s%n", entry.getKey(), entry.getValue()));

    }
}

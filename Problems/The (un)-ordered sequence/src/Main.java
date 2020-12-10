import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
            isSorted(
                Arrays.stream(scanner.nextLine().split("\\s+"))
                      .map(Integer::parseInt)
                      .collect(Collectors.toList())));
    }

    private static boolean isSorted(int[] array) {
        boolean ascending = true;
        boolean descending = true;
        for (int i = 1; i < array.length && (ascending || descending); i++) {
            ascending = ascending && array[i] >= array[i - 1];
            descending = descending && array[i] <= array[i - 1];
        }
        return ascending && descending;
    }

    private static boolean isSorted(List<Integer> array) {
        boolean ascending = true;
        boolean descending = true;
        for (int i = 1; i < array.size() - 1 && (ascending || descending); i++) {
            ascending = ascending && array.get(i) >= array.get(i - 1);
            descending = descending && array.get(i) <= array.get(i - 1);
        }
        return ascending || descending;
    }

}

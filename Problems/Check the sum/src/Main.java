import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers =
            Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(toList());

        System.out.println(isSumOf2MembersPresent(numbers, 20));
    }

    private static boolean isSumOf2MembersPresent(List<Integer> numbers, int searchSum) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (i != j) {
                    if (numbers.get(i)+numbers.get(j) == searchSum) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

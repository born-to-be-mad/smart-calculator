import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = IntStream.range(0, scanner.nextInt())
                                         .mapToObj(index -> scanner.nextInt())
                                         .collect(toList());
        Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());
        max.ifPresent(maxNumber -> {
            for (int index = 0; index < numbers.size(); index++) {
                if (numbers.get(index).compareTo(maxNumber) == 0) {
                    System.out.println(index);
                    break;
                }
            }
        });
    }
}

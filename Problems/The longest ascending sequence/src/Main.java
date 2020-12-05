import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = IntStream.range(0, scanner.nextInt())
                                         .mapToObj(index -> scanner.nextInt())
                                         .collect(toList());
        int start = 0;
        int end = 0;
        int temp = 0;
        for (int index = 0; index < numbers.size(); index++) {
            if (index == 0 || numbers.get(index) < numbers.get(index - 1)) {
                temp = index;
            } else if (index - temp > end - start) {
                start = temp;
                end = index;
            }
        }

        System.out.println(end - start + 1);
        //System.out.println(numbers.subList(start, end + 1));
    }
}

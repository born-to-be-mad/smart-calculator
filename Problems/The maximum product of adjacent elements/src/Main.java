import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * The maximum product of adjacent elements.
 * Write a program that reads an array of ints and outputs the maximum product of two adjacent elements in the given
 * array of non-negative numbers.
 *
 * @author dzmitry.marudau
 */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = IntStream.range(0, scanner.nextInt())
                                         .map(value -> scanner.nextInt())
                                         .boxed()
                                         .collect(toList());
        System.out.println(findMaxAdjacentProduct(numbers));
    }

    private static int findMaxAdjacentProduct(List<Integer> numbers) {
        int product = numbers.get(0) * numbers.get(1);
        for (int i = 2; i < numbers.size(); i++) {
            int temporalProduct = numbers.get(i - 1) * numbers.get(i);
            if (temporalProduct > product) {
                product = temporalProduct;
            }
        }
        return product;
    }

    private static int findMaxProduct(List<Integer> numbers) {
        int product = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (i == j) {
                    continue;
                }
                int temporalProduct = numbers.get(i) * numbers.get(j);
                if (temporalProduct > product) {
                    product = temporalProduct;
                }
            }
        }
        return product;
    }
}
